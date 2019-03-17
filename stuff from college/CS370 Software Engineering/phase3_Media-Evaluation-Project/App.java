import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<UrlObject> urlObjectsList = new ArrayList<UrlObject>();

        //automatically read in file
        FileParser fileParser = new FileParser(args[0],args[1]);

        //prepare an new url object per url
        for (String u:fileParser.getUrlList()) {
            URL url = new URL(u);
            URLConnection connection = url.openConnection();
            UrlObject urlObject = new UrlObject(connection);

            //store every url object
            urlObjectsList.add(urlObject);
            UrlObject.GetURLInfo(connection);

            //write urlinfo to output.txt
            fileParser.parseOutputFile(urlObject);
            System.out.println(urlObject.getUrlName());
        }//for to get info of every site


        //now we have info about a site
        for(UrlObject uo : urlObjectsList){
            if (uo.getContentType().contains("image")){
                uo.saveUrlImg();
            }
            else if (uo.getContentType().contains("html")){
                uo.saveUrlText();
            }
            else if(uo.getContentType().contains("pdf")){
                uo.saveUrlPdf();
            }

        }

        fileParser.getBW().close();


    } // main
}
