import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;

public class HashtableDatabase {

    //load form output.txt
    public static Hashtable<String,String[]> loadPrevDB(String DBtxtFile){
        Hashtable<String,String[]> HTDB = new Hashtable<String,String[]>();
        try {
            String filePath = new File(DBtxtFile).getAbsolutePath();
            File inputData = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(inputData));
            System.out.println("file loaded");

            String words[] = new String[6];
            String line = null;
            line = br.readLine();
            System.out.println("line" + line);
            words = line.split("\\|");
            HTDB.put("colNames",words);

            while ((line = br.readLine()) != null) {
                words = line.split("\\|");
                HTDB.put(words[0],words);
            }


            br.close();
        } catch (Exception e) {
//            System.err.println("Error: Target File Cannot Be Read");
            e.printStackTrace();
        }//ex
        return HTDB;
    }//loadprevdb
}
