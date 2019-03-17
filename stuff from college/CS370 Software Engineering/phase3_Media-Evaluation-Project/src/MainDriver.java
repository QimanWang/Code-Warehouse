import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class MainDriver {

    public static void main(String[] args) throws Exception{




        String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        String filePath = dir + "\\"+ "log.txt";
        System.out.println("file loaded");
        System.out.println(filePath);

        BufferedWriter bwLog = null;
        FileWriter fw = null;


        try {

            fw = new FileWriter(filePath,true);
            bwLog = new BufferedWriter(fw);

//        //jdbc test code
//        try {
//            //my code
//            //1. get connection to database
//            Connection myconn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=true", "root", "root");
//            System.out.println("Database connected!");
//            //2.create a statement
//            Statement mystmt = myconn.createStatement();
//
//            //3.Execute Sql query
//            ResultSet myRs = mystmt.executeQuery("SELECT * FROM city LIMIT 3");
//
//            //4. process the result set
//            while (myRs.next()) {
//                System.out.println(myRs.getString("District") + ", " +
//                        myRs.getString("Population"));
//            }//while
//
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }//exception

        //parse user input
        ParseCmdLine parseCmdLine = new ParseCmdLine();
        parseCmdLine.Pmain(args);
//        System.out.println("inputfile parameter:"+parseCmdLine.inputfile);
            bwLog.write(new java.util.Date() +" Successfully Parsed command line arguments");
            bwLog.newLine();
        //read input and file chooser
//        ParseFile parseFile = new ParseFile();
//        parseFile.parseInputFile(parseCmdLine.inputfile);
//        System.out.println("success: read input file");


        HashtableDatabase HT = new HashtableDatabase();
        Hashtable<String,String[]> HTDB =  HT.loadPrevDB(parseCmdLine.inputfile);
            bwLog.write(new java.util.Date() +" Successfully Loaded Hashtable from input");
            bwLog.newLine();

        Set<String> keys = HTDB.keySet();
        for(String key: keys){
            System.out.println("Value of "+key+" is: "+HTDB.get(key)[1]);
        }

        JTableRow jt = new JTableRow();
            bwLog.write(new java.util.Date() +" Successfully opened GUI");
            bwLog.newLine();
            jt.userGui(HTDB,parseCmdLine.outputfile,bwLog);


            //bw.write(content);

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bwLog != null)
                    bwLog.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }//finally

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


    }//main
}
