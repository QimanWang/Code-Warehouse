import java.io.IOException;
import java.text.ParseException;

public class StartProgram {

    //initialize the logger object to write logs
    public static LoggingHelper logger;

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        //make the logger
        logger = new LoggingHelper();


        //check if any flags, if not, pop up gui
        if (args.length == 0) {

            //create login gui
            logger.writeToLogFile("No command line argument, opening login screen");
            LoginView lv = new LoginView();

            //thread to check every second if the user had logged in
            Thread t = new Thread();
            while (!lv.loggedIn) {
                try {
                    t.sleep(1000);
//                    System.out.println(lv.loggedIn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }//while not logged in, stuck here

            //write to system "welcome <user>"
            System.out.println("welcome " + lv.un);


            /*Create the data storage object
            loads the previous database of that user into data storage*/
            DataStorage htdb = new DataStorage();
            System.out.println("loading storage");
            htdb.loadPreviousDB(lv.un, lv.pw);

            /*initialize the GUI and
            load data storage into the GUI view */

            JTableRow jt = new JTableRow();
            jt.userGui(htdb.ht);
            logger.writeToLogFile("Opening GUI");

        }//no args, pop up GUI
        //else we run with no GUI
        else {
            logger.writeToLogFile("Command line arguments detected");

            //create command line parser object
            CommandLineParser clp = new CommandLineParser();

            //extract the arguments
            clp.ExtractFLags(args);
            System.out.println("Starting command line program");

            //read input file.txt which contains the items to search
            clp.parseInputFile();
            //search goodwill and save the result
            clp.searchGoodWill();
            //write the result to output file.txt
            clp.saveToOutfile();

            logger.writeToLogFile("Successfully processed command line program");


        }//cmdline access

        //end of program
        System.out.println("good bye!");
        logger.writeToLogFile(LoginView.un + "_" + LoginView.pw + " has logged off");
    }//main
}//start program
