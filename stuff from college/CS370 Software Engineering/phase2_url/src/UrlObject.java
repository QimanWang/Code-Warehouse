import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class UrlObject {
    private String urlName;
    private String contentType;
    private int contentLength;
    private Date lastModified;
    private long expiration;
    private String contentEncoding;
    public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2) Gecko/20100115 Firefox/3.6";

//    private boolean AllowUserInteraction;
//             private boolean ConnectTimeout;
//             private privateDate;
//             private DefaultUseCaches()
//             private boolean DoInput()
//             private boolean DoOutput()
//             private PrePermission()
//             u.getReadTimeout()
//             u.getUseCaches()

    UrlObject(URLConnection u) throws IOException {
        this.urlName = u.getURL().toExternalForm();
        this.contentType = u.getContentType();
        this.contentLength = u.getContentLength();
        this.lastModified = new Date(u.getLastModified());
        this.expiration = u.getExpiration();
        this.contentEncoding = u.getContentEncoding();
//        u.getAllowUserInteraction()
//                u.getConnectTimeout()
//    u.getDate()
//            u.getDefaultUseCaches()
//                    u.getDoInput()
//                            u.getDoOutput()
//                                    u.getPermission()
//                                            u.getReadTimeout()
//                                                    u.getUseCaches()
    }

    public String getUrlName() {
        return urlName;
    }

    public String getContentType() {
        return contentType;
    }

    public int getContentLength() {
        return contentLength;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public long getExpiration() {
        return expiration;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    static void GetURLInfo(URLConnection u) throws IOException {

        // Display the URL address, and information about it.
        String urlName = u.getURL().toExternalForm();
        System.out.println(urlName);
        System.out.println("  Content Type: " + u.getContentType());
        System.out.println("  Content Length: " + u.getContentLength());
        System.out.println("  Last Modified: " + new Date(u.getLastModified()));
        System.out.println("  Expiration: " + u.getExpiration());
        System.out.println("  Content Encoding: " + u.getContentEncoding());
    } // getURLinfo


    public static InputStream getURLInputStream(String sURL) throws Exception {
        URLConnection oConnection = (new URL(sURL)).openConnection();
        oConnection.setRequestProperty("User-Agent", USER_AGENT);
        return oConnection.getInputStream();
    }

    public static BufferedReader read(String url) throws Exception {
        //InputStream content = (InputStream)uc.getInputStream();
//        BufferedReader in = new BufferedReader (new InputStreamReader
//    (content));
        InputStream content = (InputStream) getURLInputStream(url);
        return new BufferedReader(new InputStreamReader(content));
    } // read

    public static BufferedReader read2(String url) throws Exception {
        return new BufferedReader(
                new InputStreamReader(
                        new URL(url).openStream()));
    } // read

    void saveUrlImg() throws IOException {

        BufferedImage image = null;
        String[] imgNames = urlName.split("/");
        String imgName = imgNames[imgNames.length - 1];
        String imgExtension = imgName.substring(imgName.length() - 3);
        File outputFile = new File(imgName);
        URL url = new URL(urlName);
        System.out.println(imgName + "ZZZZZ" + imgExtension + "ZZZZZ" + outputFile);

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
        } // catch

        ImageIO.write(image, imgExtension, outputFile);


    } // GetURLImage

    void saveUrlText() throws Exception {
        String dir = System.getProperty("user.dir");

//        //String[] outFileName = urlName.split("");
//        urlName = urlName.replace("\", "\\*");
        String[] outFileName = urlName.split("\\W");
        for (String b : outFileName){
            System.out.println(b);
        }

        String filePath = dir + "\\" + outFileName[outFileName.length-2]+"."+ outFileName[outFileName.length-1];

        try{
        //System.out.println("file path : " + filePath);
        BufferedWriter bwHtml = new BufferedWriter(new FileWriter(filePath));

        //prep webpage reader
        BufferedReader reader = read(urlName);
        String line = reader.readLine();

        while (line != null) {
            line = reader.readLine();
            //System.out.println(line);
            if(line != null) {
                bwHtml.write(line);
            }
            bwHtml.newLine();
        } // while

            bwHtml.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//ex

        System.out.println("finished writing " + filePath);
    }

    void saveUrlPdf() throws IOException {
        // TEST

        URL url = new URL(urlName);
        InputStream in = url.openStream();

        String dir = System.getProperty("user.dir");

//        //String[] outFileName = urlName.split("");
//        urlName = urlName.replace("\", "\\*");
        String[] outFileName = urlName.split("\\W");
        for (String b : outFileName){
            System.out.println(b);
        }

        String fileName = outFileName[outFileName.length-2]+"."+ outFileName[outFileName.length-1];

        FileOutputStream fos = new FileOutputStream(new File(fileName));

        int length = -1;
        byte[] buffer = new byte[1024];// buffer for portion of data from connection
        while ((length = in.read(buffer)) > -1) {
            fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();
        System.out.println("pdf File downloaded");
    }


    /*
        // Read and print out the first five lines of the URL.
        System.out.println("First five lines:");
        DataInputStream in = new DataInputStream(u.getInputStream());
        for(int i = 0; i < 5; i++) {
            String line = in.readLine();
            if (line == null) break;
            System.out.println("  " + line);
        } // for
*/
}//class urlobject


