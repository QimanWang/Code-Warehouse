import java.io.*;
import java.util.Hashtable;

//the storage class will hold all the data
public class DataStorage implements Serializable {

    public Hashtable<String, GoodWillItem> ht = new Hashtable<String, GoodWillItem>();
    public String outfile;

    public DataStorage() {

    }

    //load the previoud database if the user had a a database before
    public void loadPreviousDB(String un, String pw) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(un + "_" + pw + ".txt"));
            System.out.println("file loaded");

            String words[] = new String[6];
            String line = null;
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                System.out.println("prevDB: " + line);
                words = line.split("\\|");

                //make new goodWillItem
                GoodWillItem gwi = new GoodWillItem();
                System.out.println("~~~~~~~~~~~~~~~~~~~~");
                gwi.toString();
                gwi.setNumber(words[0]);
                gwi.setName(words[1]);
                gwi.setDesc(words[2]);
                gwi.setPrice(Double.parseDouble(words[3]));
                gwi.setSeller(words[4]);
                gwi.setDate(words[5]);

                ht.put(words[0], gwi);

            }// while to read every line
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }//exception
//
        System.out.println("prev db in system");
//        System.out.println(ht);
        StartProgram.logger.writeToLogFile("Successfully loaded previous database");
    }//parsefile method

    //constructor for output file
    public DataStorage(String outfile) {
        this.outfile = outfile;
        try {
            new FileWriter(new File(outfile), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//constructor


}//class
