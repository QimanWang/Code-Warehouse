import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {

    String preUrl = "https://www.shopgoodwill.com/Item/";
    String suffixUrl = "";
    String test = "12345678";
    String urlString;
    //    HashTable storage =new HashTable();
    private Date Date;

    public UrlParser(String suffixUrl) {
        this.suffixUrl = suffixUrl;
        urlString = "";
    }

    public void grabHtml() {

        try {
            java.net.URLConnection urlConnection = new URL(preUrl + test).openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));

            String current;
            FileWriter writer = new FileWriter(new File("test.html"), false);
            while ((current = in.readLine()) != null) {
                urlString += current + "\n";

            }
            writer.write(urlString);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GoodWillItem parseHtml() throws ParseException {

        String regexTitle = "<title>.*</title>";
        String regexProductData = "<ul class=\"product-data\">.*?</ul>";
        Pattern p = Pattern.compile(regexTitle, Pattern.DOTALL);
        Pattern p1 = Pattern.compile(regexProductData, Pattern.DOTALL);
        Pattern p2 = Pattern.compile("<div class=\"col-xs-12 col-sm-6\">.*?" + "<br>", Pattern.DOTALL);

        String itemName = null;
        String id = null;
        String price = null;
        String endDate = null;
        String seller = null;
        List<BufferedImage> imageList = new ArrayList<>();
        String numofBids = null;
        String line;
        String html = null;
        String[] tokens = new String[0];
        String foundString = null;

        try {
            java.net.URLConnection urlConnection = new URL(preUrl + suffixUrl).openConnection();
            //BufferedReader in = new BufferedReader(new FileReader("test.html"));
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            in.readLine();
            in.readLine();

            while ((line = in.readLine()) != null) {
                html += line + "\n";
                Matcher m = p.matcher(line);
                Matcher m1 = p1.matcher(html);
                Matcher m2 = p2.matcher(html);
                //title
                if (m.find()) {
                    itemName = line.substring(m.start(), m.end()).split("shopgoodwill.com")[0];
                    itemName = itemName.substring(7, itemName.length() - 2);

                }
                //images
                if (m2.find()) {
                    String s = html.substring(m2.start(), m2.end());
                    s = s.replaceAll("<(.?div.*|.?a.*?|b.*|.?ol.*?|.?li.*?)*>", "");
                    s = s.replaceAll("\\s", "");
                    s.trim();
                    String[] t = s.split("\">");
                    for (String a : t) {
                        String u = a.substring(9);
                        String filename = a.substring(a.lastIndexOf("/") + 1);
                        filename = "images/" + filename;
                        //saves and adds to list
                        try {
                            saveImage(u, filename);
                            BufferedImage image = ImageIO.read(new File(filename));
                            imageList.add(image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    html = "";
                }
                //product data
                if (m1.find()) {
                    foundString = html.substring(m1.start(), m1.end());
                    tokens = foundString.split("</li>");
                    for (String s : tokens) {
                        s = s.replaceAll("\\<[^\\>]*\\>", "").trim();
                        s = s.replaceAll("\\s", " ").trim();
                        if (s.contains("Product ID")) {
                            id = s.split(":")[1].trim();
                        } else if (s.contains("Seller")) {
                            seller = s.split(":")[1].trim();
                        } else if (s.contains("Current Price")) {
                            price = s.split(":")[1].trim().substring(1);
                        } else if (s.contains("Ends On")) {
                            endDate = s.substring(8).trim();
                        } else if (s.contains("Number of Bids")) {
                            numofBids = s.split("s:")[1];
                            numofBids = numofBids.replaceAll("[^0-9]", "");
                        }
                    }
                    html = "";
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String [] tokens = new String[0];
//        Matcher m =p.matcher(urlString);
//        while(m.find()){
//            itemName = urlString.substring(m.start(),m.end()).split("shopgoodwill.com")[0];
//            itemName=itemName.substring(7,itemName.length()-2);
//
//        }
//        //GRAB id,price,name,
//        m = p1.matcher(urlString);
//        if(m.find()){
//            tokens = urlString.substring(m.start(),m.end()).split("</li>");
//            //System.out.println(tokens.length);
//
//            for (String s : tokens){
//                s= s.replaceAll("\\<[^\\>]*\\>","").trim();
//                s=s.trim().replaceAll("\\s"," ");
//                System.out.println(s);
//            if(s.contains("Product ID")){
//                id = s.split(":")[1].trim();
//            }
//            else if(s.contains("Seller")){
//                seller = s.split(":")[1].trim();
//            }
//            else if(s.contains("Current Price")){
//                price = s.split(":")[1].trim().substring(1);
//            }
//            else if(s.contains("Ends On")){
//                endDate= s.substring(8).trim();
//            }
//            else if(s.contains("Number of Bids")){
//                numofBids=s.split("s:")[1];
//            }
//
//            }
//        }
//        ///GRABS IMAGES
//        Pattern p2 = Pattern.compile("<div class=\"col-xs-12 col-sm-6\">.*?" + "<br>",Pattern.DOTALL);
//        Matcher m2 = p2.matcher(urlString);
//        while(m2.find()){
//            String s = urlString.substring(m2.start(),m2.end());
//            s= s.replaceAll("<(.?div.*|.?a.*?|b.*|.?ol.*?|.?li.*?)*>","");
//            s=s.replaceAll("\\s","");
//            s.trim();
//            String[] t = s.split("\">");
//            for(String a: t){
//                String u= a.substring(9);
//                String filename=a.substring(a.lastIndexOf("/")+1);
//                try {
//                    saveImage(u,filename);
//                    BufferedImage image = ImageIO.read(new File("images/"+filename));
//                    imageList.add(image);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
        System.out.println("Item Name: " + itemName);
        System.out.println("ID: " + id);
        System.out.println("Num of Bids: " + numofBids);
        System.out.println("Price: " + price);
        System.out.println("Seller: " + seller);
        System.out.println("EndDate: " + endDate);
        System.out.println("ImageList Size:" + imageList.size());

//        Listing item = new Listing(numofBids,itemName,seller,Double.parseDouble(price),id,endDate,imageList);
//        storage.insert(item);
        double dp = Double.parseDouble(price);

        GoodWillItem goodWillItem = new GoodWillItem(
                id, itemName, numofBids, dp, seller, endDate);

        return goodWillItem;
    }

    /*
      String number;
    String name;
    String desc;
    double price;
    String seller;
    Date date;
     */
    public void saveImage(String u, String filename) {
        URL url = null;
        BufferedImage img = null;
        try {
            url = new URL(u);
            img = ImageIO.read(url);
            File file = new File(filename);
            if (!file.exists()) {
                file.mkdir();
            }
            ImageIO.write(img, "jpg", file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}