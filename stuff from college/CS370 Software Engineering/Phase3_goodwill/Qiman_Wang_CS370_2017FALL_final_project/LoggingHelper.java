import java.io.*;
import java.sql.Timestamp;

public class LoggingHelper {
    public static File logFile = new File("LOGS.txt");
    public static BufferedWriter bw = null;
    public static FileWriter fw = null;
    public static PrintWriter out = null;
    public static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    //constructor
    public LoggingHelper() throws IOException {

        //if log file doesn't exist, create it
        if (!(logFile.exists() && !logFile.isDirectory())) {
            PrintWriter writer = new PrintWriter(logFile, "UTF-8");
            writer.write("Logs************************************************\n");
            writer.write(System.getProperty(System.lineSeparator()));
            writer.close();
        }//create log file

        //create print writer
        fw = new FileWriter(logFile, true);
        bw = new BufferedWriter(fw);
        out = new PrintWriter(bw);
    }//constructor

    //the main function called to write to log
    public void writeToLogFile(String msg) {

        //need to get the current time stamp and append it to every log line
        timestamp = new Timestamp(System.currentTimeMillis());
        out.println(timestamp + ": " + msg);
    }

    //closes the writers objects
    public void closeLogger() throws IOException {
        bw.close();
        fw.close();
        out.close();
    }

}
