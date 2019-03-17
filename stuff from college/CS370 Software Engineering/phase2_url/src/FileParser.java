import java.io.*;
import java.util.ArrayList;

public class FileParser {
    private String inFile;
    private String outFile;
    private ArrayList<String> urlList = new ArrayList<String>();
    private BufferedWriter bw = null;


    //constructor
    FileParser(String inFile, String outFile) throws IOException {
        this.inFile = inFile;
        this.outFile = outFile;
        parseInputFile();
        String dir = System.getProperty("user.dir");
        String filePath = dir + "\\" + outFile;
        System.out.println("file path : " + filePath);
        bw = new BufferedWriter(new FileWriter(filePath));
    }

    public ArrayList<String> getUrlList() {
        return urlList;
    }

    public BufferedWriter getBW() {
        return bw;
    }

    public void parseInputFile() {
        try {
//            String filePath = new File(infileName).getAbsolutePath();
            File inputData = new File(new File(inFile).getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(inputData));

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (line.contains("http")) {
                    this.urlList.add(line);
                }//if a url
            }//while more urlline

            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
            e.printStackTrace();
        }//ex
    }//parsefile method

    public void parseOutputFile(UrlObject urlObject) {
        try {


            bw.write("URL link: " + urlObject.getUrlName());
            bw.newLine();
            bw.write("Content Type: " + urlObject.getContentType());
            bw.newLine();
            bw.write("Content Length: " + urlObject.getContentLength());
            bw.newLine();
            bw.write("Last Modified: " + urlObject.getLastModified());
            bw.newLine();
            bw.write("Expiration: " + urlObject.getExpiration());
            bw.newLine();
            bw.write("Content Encoding: " + urlObject.getContentEncoding());
            bw.newLine();
            bw.newLine();

            System.out.println("Done writing outfile");
        } catch (IOException e) {
            e.printStackTrace();
        }//ex

    }//parseoutput
}//class