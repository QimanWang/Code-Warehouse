import java.util.Scanner;
import java.io.*;


public class Histogram {

    public static void computeHistogram(String inFile ,String outFile){
        try {
            Scanner inputFile = new Scanner(new File(inFile));

            //read in first 4 header values
            int number =0;
            number = inputFile.nextInt();
            int numRow = number;
            number = inputFile.nextInt();
            int numColumn = number;
            number = inputFile.nextInt();
            int minValue = number;
            number = inputFile.nextInt();
            int maxValue = number;

            int[] histogram = new int[maxValue+1];
            for(int i = 0; i < numColumn; i++){
                for(int j = 0; j <numRow; j++){
                    number = inputFile.nextInt();
                    histogram[number]++;
                }//forj
            }//scan through file and stores it.

            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(outFile+"_hist.txt"));

                for(int i = minValue; i <= maxValue; i++) {
                    if (i / 10 < 1) {
                        writer.write("( " + i + "):"+histogram[i]+" ");
                    }//single digit gets " "
                    if (i / 10 >= 1){
                        writer.write("(" + i + "):"+histogram[i]+" ");
                    }//two digit gets ""
                    if (histogram[i] > 60) {
                        for(int j =0; j <60; j++){
                            writer.write("+");
                        }//for to print 60
                    }
                    else{
                        for(int j =0; j <histogram[i]; j++){
                            writer.write("+");
                        }//for to print amount
                    }
                    writer.write(System.getProperty("line.separator"));
                }//for through every histogram value
                inputFile.close();
                writer.close();
            } catch(IOException e){
                e.printStackTrace();
            }//catch write
        } catch (FileNotFoundException fe) {
            System.out.println("File not found!");
        }//catch scan
    }
    public static void main(String[] args) {

        computeHistogram(args[0],args[1]);
    }//main

}//class

