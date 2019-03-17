import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

//this code takes the idea from
//sun Microsystems 1995-1997
public class CommandLineParser {

    //i to keep track of every argument index
    int i = 0;

    String arg;
    public String inputfile = "";
    public String outputfile = "";
    boolean saveToDB = false;
    boolean flaglog = false;
    String un, pw;
    ArrayList<String> itemsToParseList = new ArrayList<String>();
    ArrayList<GoodWillItem> gwiList = new ArrayList<GoodWillItem>();

    //this runs through the input argument list and
    //find the commands that are inputted.
    public void ExtractFLags(String[] args) {

        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];

            //this block find the corresponding flag and store the args
            if (arg.equals("-i")) {

                inputfile = args[i++];
                if (inputfile.endsWith(".txt")) {
                    System.out.println("input file: " + inputfile);
                } else {

                    //open file chooser if no file is inputted
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.showSaveDialog(null);
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    String filename = chooser.getSelectedFile().getName();
                    System.out.println("user selected:" + filename);
                    inputfile = filename;
                }
            }//inputfile

            //detect output file
            else if (arg.equals("-o")) {
                outputfile = args[i++];
            }//output filee

            //detect log flag
            else if (arg.equals("-l")) {
                flaglog = true;
                i++;
            }
            //detect save flag
            else if (args.equals("-s")) {
                saveToDB = true;
                un = args[i++];
                pw = args[i++];
            }//save to db

        }//while to go through all args
    }//extract flags

    //this will parse the input file and extract the search objects
    public void parseInputFile() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(inputfile));
        System.out.println("Input file loaded");

        String line = null;
        line = br.readLine();
        System.out.println("line" + line);
        itemsToParseList.add(line);

        while ((line = br.readLine()) != null) {
            System.out.println("line" + line);
            itemsToParseList.add(line);
        }
        System.out.println("finished parsing input file");
    }//parse inputfile

    //search the items in the input list and save to a list og goodwill objects
    public void searchGoodWill() throws IOException, ParseException {
        GoodWillItem newGoodWillItem = null;

        for (String itemlink : itemsToParseList) {


            newGoodWillItem = null;
            UrlParser u = new UrlParser(itemlink);
            u.grabItemNumbers();
            for (String itemId : u.urkItemNameeList) {

                newGoodWillItem = u.parseHtml(itemId);

                gwiList.add(newGoodWillItem);

            }
        }//go through every item link

    }

    //saves the goodwill items to the output file
    public void saveToOutfile() {

        BufferedWriter bw = null;
        FileWriter fw = null;
        System.out.println("output file will be write to " + outputfile);
        try {
            fw = new FileWriter(outputfile);
            bw = new BufferedWriter(fw);
            bw.write("Inventory Number|Item Name|Number of Bids|Price|Seller|Bid End Date|");
            bw.newLine();

            for (GoodWillItem gwi : gwiList) {
                String content = "";
                content += gwi.getNumber() + "|" + gwi.getName() + "|" + gwi.getDesc() + "|" + gwi.getPrice() + "|" + gwi.getSeller() + "|" + gwi.getDate() + "|";
                bw.write(content);
                bw.newLine();
                System.out.println(content);

            }//go through every gwi item and print it out

            System.out.println("Done writing to output file");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }// write to output file
}//class
