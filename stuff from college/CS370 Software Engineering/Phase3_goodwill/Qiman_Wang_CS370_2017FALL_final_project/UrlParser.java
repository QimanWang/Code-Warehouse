import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {

    String preUrl = "https://www.shopgoodwill.com/Item/";
    public ArrayList<String> urkItemNameeList = new ArrayList<String>();
    String test = "12345678";
    String urlString;


    //search fields
    String searchText;
    String highPrice;
    String lowPrice;
    String SearchBuyNowOnly;
    String SearchPickupOnly;
    String SearchNoPickupOnly;
    String SearchClosedAuctions;

    //defult constructor
    public UrlParser(String urlItemName){
        urlString = "";
        urlItemName = urlString.replace("\\s","%20");
        this.searchText = urlItemName;
    }

    //advanced search constructor
    public UrlParser(String urlItemName ,String highPrice,String lowPrice, String SearchBuyNowOnly, String SearchPickupOnly,
                     String SearchNoPickupOnly, String SearchClosedAuctions) {
        urlString = "";
        this.searchText = urlItemName;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.SearchBuyNowOnly = SearchBuyNowOnly;
        this.SearchPickupOnly = SearchPickupOnly;
        this.SearchNoPickupOnly = SearchNoPickupOnly;
        this.SearchClosedAuctions = SearchClosedAuctions;

    }

    //connect to url an grab the html texts
    public void grabHtml() {

        try {
            java.net.URLConnection urlConnection = new URL(preUrl + test).openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String current;
            FileWriter writer = new FileWriter(new File("test.html"), false);
            while ((current = in.readLine()) != null) {
                urlString += current + "\n";

            }//read every html line

            writer.write(urlString);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //grab item numbers
    public void grabItemNumbers() throws IOException {

        //initialize the regex and pattern
        String regexFirstResultNumber = "a class=\"product\" href=\".*\">";
        Pattern p = Pattern.compile(regexFirstResultNumber, Pattern.DOTALL);
/*
        <input Name="st" id="st" name="SearchCriteria.SearchText" type="hidden" value="lego star war" />
<input Name="sg" id="sg" name="SearchCriteria.SelectedGroup" type="hidden" value="" />
<input Name="c" id="c" name="SearchCriteria.SelectedCategoryIds" type="hidden" value="" />
<input Name="s" id="s" name="SearchCriteria.SelectedSellerIds" type="hidden" value="" />
<input Name="hp" id="hp" name="SearchCriteria.HighPrice" type="hidden" value="999999" />
<input Name="lp" id="lp" name="SearchCriteria.LowPrice" type="hidden" value="0" />
<input Name="sbn" id="sbn" name="SearchCriteria.SearchBuyNowOnly" type="hidden" value="False" />
<input Name="spo" id="spo" name="SearchCriteria.SearchPickupOnly" type="hidden" value="False" />
@<input Name="snpo" id="snpo" name="SearchCriteria.SearchNoPickupOnly" type="hidden" value="False" /> //
@<input Name="sca" data-val="true" data-val-required="The SearchClosedAuctions field is required." id="sca" name="SearchCriteria.SearchClosedAuctions" type="hidden" value="False" />
<input Name="caed" data-val="true" data-val-date="The field ClosedAuctionEndingDate must be a date." data-val-required="The ClosedAuctionEndingDate field is required." id="caed" name="SearchCriteria.ClosedAuctionEndingDate" type="hidden" value="12/11/2017 12:00:00 AM" />
<input Name="cadb" data-val="true" data-val-number="The field ClosedAuctionDaysBack must be a number." data-val-required="The ClosedAuctionDaysBack field is required." id="cadb" name="SearchCriteria.ClosedAuctionDaysBack" type="hidden" value="7" />
<input Name="sd" data-val="true" data-val-required="The SearchDescriptions field is required." id="sd" name="SearchCriteria.SearchDescriptions" type="hidden" value="False" />
<input Name="socs" id="socs" name="SearchCriteria.SearchOneCentShippingOnly" type="hidden" value="False" />
<input Name="scs" data-val="true" data-val-required="The Canada field is required." id="scs" name="SearchCriteria.SearchCanadaShipping" type="hidden" value="False" />
<input Name="sis" data-val="true" data-val-required="The Outside Canada field is required." id="sis" name="SearchCriteria.SearchInternationalShippingOnly" type="hidden" value="False" />
<input Name="col" data-val="true" data-val-number="The field Order By: must be a number." data-val-required="The Order By: field is required." id="col" name="SearchCriteria.SortColumn" type="hidden" value="0" />
<input Name="p" data-val="true" data-val-number="The field Page must be a number." data-val-required="The Page field is required." id="p" name="SearchCriteria.Page" type="hidden" value="1" />
<input Name="ps" data-val="true" data-val-number="The field PageSize must be a number." data-val-required="The PageSize field is required." id="ps" name="SearchCriteria.PageSize" type="hidden" value="40" />
<input Name="desc" data-val="true" data-val-required="The SortDescending field is required." id="desc" name="SearchCriteria.SortDescending" type="hidden" value="False" />
<input Name="ss" data-val="true" data-val-number="The field SavedSearchId must be a number." data-val-required="The SavedSearchId field is required." id="ss" name="SearchCriteria.SavedSearchId" type="hidden" value="0" />
*/

                String advancedSearchString = "https://www.shopgoodwill.com/Listings?" +
                "st=" + searchText +"&sg=&c=&s=&lp=0&hp=999999&sbn=false&spo=false&" +
                "snpo=false&socs=false&sd=false&sca=false&caed=12/10/2017&" +
                "cadb=7&scs=false&" +
                "sis=false&col=0&p=1&ps=40&desc=false&ss=0&UseBuyerPrefs=true";
                /*https://www.shopgoodwill.com/Listings?st=earring&
        sg=&
        c=&
        s=&
        lp=0&
        hp=999999&
        sbn=false&
        spo=false&
        snpo=false&
        socs=false&
        sd=false&
        sca=false&
        caed=12/11/2017&
        cadb=7&
        scs=false&
        sis=false&
        col=0&
        p=1&
        ps=40&
        desc=false&
        ss=0&
        UseBuyerPrefs=true
        */
        java.net.URLConnection urlConnection = new URL(advancedSearchString).openConnection();
        //BufferedReader in = new BufferedReader(new FileReader("test.html"));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));

        String html = null;
        String line = null;
        int count =0;
        while ((line = in.readLine()) != null) {
            html += line + "\n";
            Matcher m = p.matcher(line);

            //title
            if (m.find()) {
                line = line.replaceAll("[^0-9]", "");
                System.out.println("line: " + line);
                urkItemNameeList.add(line);
                count ++;
                if(count ==5){
                    return;
                }
            }
        }//while more line on html

    }//grab the first 5 product id number

    //this function will parse the html text and find the fields
    public GoodWillItem parseHtml(String urkItemID) throws ParseException {

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
            java.net.URLConnection urlConnection = new URL(preUrl + urkItemID).openConnection();
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

        System.out.println("Item Name: " + itemName);
        System.out.println("ID: " + id);
        System.out.println("Num of Bids: " + numofBids);
        System.out.println("Price: " + price);
        System.out.println("Seller: " + seller);
        System.out.println("EndDate: " + endDate);
        System.out.println("ImageList Size:" + imageList.size());

        double dp = Double.parseDouble(price);

        GoodWillItem goodWillItem = new GoodWillItem(
                id, itemName, numofBids, dp, seller, endDate);

        //return the goodwill item per parse
        return goodWillItem;
    }

    //this saves the image associated with the item
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
