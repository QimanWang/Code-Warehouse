//package com.mike.URL;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;


public class urlProcessor {
        // final string of the USER_AGENT we are acting as
        public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2) Gecko/20100115 Firefox/3.6";
        // this writes the URL Info to file
        public static void printURLinfo(URLConnection uc, BufferedWriter writer) throws IOException {
            // Display the URL address, and information about it.
            writer.write(uc.getURL().toExternalForm() + ":");
            writer.newLine();
            writer.write("  Content Type: " + uc.getContentType());
            writer.newLine();
            writer.write("  Content Length: " + uc.getContentLength());
            writer.newLine();
            writer.write("  Last Modified: " + new Date(uc.getLastModified()));
            writer.newLine();
            writer.write("  Expiration: " + uc.getExpiration());
            writer.newLine();
            writer.write("  Content Encoding: " + uc.getContentEncoding());
            writer.newLine();
            writer.newLine();


        } // printURLinfo


    // gets the URLStream according to the USER_AGENT
    public static InputStream getURLInputStream(String sURL) throws Exception {
            URLConnection oConnection = (new URL(sURL)).openConnection();
            oConnection.setRequestProperty("User-Agent", USER_AGENT);
            return oConnection.getInputStream();
        } // getURLInputStream

    //reads in the data from the new bufferReader with User_Agent
    public static BufferedReader read(String url) throws Exception {
            InputStream content = (InputStream) getURLInputStream(url);
            return new BufferedReader(new InputStreamReader(content));
        } // read


    // if the content type is returned as null we use the other string
    public static String determineType(String x, String y){
        if (x==null)return y;
        else return x;
    }

    //saves the image from an input URL
    public static void saveImage(String imageUrl, String destinationFile) throws IOException {

        //used to keep the bytes in order and defined the destinationFile
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }


    public static void main(String[] args) throws Exception {
	// write your code here


        // Create a URL from the specified address, open a connection to it,
        // and then write  information about the URL to file.

        //pulls in the input file of URLS and the output file of the data
        String in_file = args[0];
        String out_file = args[1];

        //uses scanner to read in URLS and Buffered writer to write data
        Scanner in  = new Scanner(new File(in_file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(out_file),false));

        //used for counting # of images and files
        int image_count=1;
        int file_count=1;

        // while we have more URLS
        while(in.hasNext()) {

            //uses url string to make url object and opens connection to it
            String url_string = in.nextLine();
            URL url = new URL(url_string);
            URLConnection connection = url.openConnection();
            printURLinfo(connection,writer);
            connection.connect();
            // we use the determine type function to classify the content of the URL
            String contentType =determineType(connection.guessContentTypeFromName(url_string), connection.getContentType().split(";")[0]);

        //if the content type of the URL is either txt or html
        if(contentType.contains("text")|contentType.contains("html")){
            // open a reader to read in the URL content
            BufferedWriter file_writer = new BufferedWriter(new FileWriter(new File("file"+file_count+".html"),false));

            //read in URL content
            BufferedReader reader = read(url_string);
            String line = reader.readLine();
            int line_count =0;
            while (line != null) {
                //writing the content to file
                file_writer.write(line);
                line = reader.readLine();

                //incremeting line count
                line_count++;
            } // while
            //close file_Writer
            file_writer.close();

            //write to output_file
            writer.write("Number of lines:" +line_count);
            writer.newLine();
            writer.write("FileName saved as : " +"file"+file_count+".html");
            writer.newLine();
            writer.newLine();
            file_count++;

        }

        else if (contentType.contains("image")){
            // Create a URL from the specified address, open a connection to it,
        // and then display information about the URL.
            ///saves image to file
            saveImage(url_string,"image"+image_count+".jpg");

            //output file name to output file
            writer.write("Filename saved as : " +"image"+image_count+".jpg");
            writer.newLine();
            writer.newLine();
            image_count++;

        }

        }
        writer.close();
    }
}
