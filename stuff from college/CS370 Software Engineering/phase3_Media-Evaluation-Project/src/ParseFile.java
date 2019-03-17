import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ParseFile {


    public void parseInputFile(String infileName) {
        try {
            String filePath = new File(infileName).getAbsolutePath();
            File inputData = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(inputData));
            System.out.println("file loaded");

            String words[] = new String[6];
            String line = null;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println("line" + line);
                words = line.split("\\|");

                for (int idx = 0; idx < words.length; idx++) {
                    words[idx] = words[idx].replaceAll("\\s", "");
                    System.out.println("index " + idx + " :" + words[idx]);
                }

                String inventoryNumber = words[0];
                String ItemDescribtion = words[1];
                int quantity = Integer.parseInt(words[2]);
                double price = Double.parseDouble(words[3]);

                Date datePurchased, timestamp;
                //1-4-2017
                SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
                datePurchased = ft.parse(words[4]);
                timestamp = ft.parse(words[5]);

            }
            br.close();
        } catch (Exception e) {
//            System.err.println("Error: Target File Cannot Be Read");
            e.printStackTrace();
        }//ex
    }//parsefile method

    public void parseOutputFile(){

    }


}
