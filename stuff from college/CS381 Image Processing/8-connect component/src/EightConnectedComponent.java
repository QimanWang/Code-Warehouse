import java.io.*;
import java.util.*;

public class EightConnectedComponent {

    //global variable
    static int newLabel;
    static int maxLabel;
    static int lit;
    static int numRow, numCol, minVal, maxVal;
    static int[] EQAry;
    static int[] neighborAry;

    static int minValueInArrayGreaterThanZero(int[] lit) {
        int minVal = lit[0];
        for (int i = 0; i < lit.length; i++) {
            if (lit[i] > 0) {
                minVal = lit[i];
            }
            if (minVal > 0) {
                break;
            }
        }//for i
        return minVal;
    }//return min value in array

    static int distinctElementGreaterThanZero(int[] lit, int pass) {
        int s = 3+pass-1;
        int numOfDistinctElement = 0;
        if (lit[s] > 0) {
            numOfDistinctElement++;
        }
        int current = lit[s];
        for (int i = s; i >= 0; i--) {
            if (lit[i] > 0 && lit[i] < current) {
                numOfDistinctElement++;
                current = lit[i];
            }
        } // fori go through each element
        return numOfDistinctElement;
    }//return number of distinct element

    static int[][] pass1(int[][] img, String outFile) {
        newLabel = 0;
        neighborAry = new int[4];
        int size = (int) Math.ceil((numRow * numCol) / 2);
        EQAry = new int[size];
        for (int i = 0; i < EQAry.length; i++) {
            EQAry[i] = i;
        }//set EQAry entries to index +1

        //loop trough image to process each pixel
        for (int i = 1; i < numRow + 1; i++) {
            for (int j = 1; j < numCol + 1; j++) {

                //test case entries that are not 0
                if (img[i][j] > 0) {

                    //load the four neighbors
                    neighborAry[0] = img[i - 1][j - 1];
                    neighborAry[1] = img[i - 1][j];
                    neighborAry[2] = img[i - 1][j + 1];
                    neighborAry[3] = img[i][j - 1];
                    Arrays.sort(neighborAry);

                    if (neighborAry[3] == 0) {
                        newLabel++;
                        img[i][j] = newLabel;

                    }//case1 all entries are 0
                    if (distinctElementGreaterThanZero(neighborAry,1) == 1) {
                        img[i][j] = neighborAry[3];

                    }//case 2 if they're all same label, assign label to entrie
                    if (distinctElementGreaterThanZero(neighborAry, 1) >= 2) {
                        img[i][j] = minValueInArrayGreaterThanZero(neighborAry);
                        for (int z = 0; z < 4; z++) {
                            if (neighborAry[z] > 0) {
                                EQAry[neighborAry[z]] = img[i][j];
                            }//if element is not 0
                        }//fori update EQ table

                    }// case 3 if two or more distinct labels, assign  min to entrie
                }//if entrie is 1 , it's a component part
            }//forj
        }//fori loop through each entrie that is >0
        maxLabel = newLabel;
        maxVal = 0;
        for(int y = 0; y <=maxLabel; y ++) {
            if (EQAry[y] > maxVal) {
                maxVal = EQAry[y]+1;
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile + "_pass1.txt"));
            writer.write(numRow+" "+numCol+" "+ minVal+" "+maxVal);
            writer.write(System.getProperty("line.separator"));
            for (int i = 1; i <= img.length -1; i++) {
               for(int j = 1; j < img[0].length-1; j++ ) {
                    writer.write(img[i][j]+" ");
               }//forj
                writer.write(System.getProperty("line.separator"));
            }//fori
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//catch write
        return img;
    }//pass one

    static int[][] pass2(int[][] img,String outFile ) {
        neighborAry = new int[5];
        //loop trough image to process each pixel
        for (int i = numRow +1; i > 1; i--) {
            for (int j = numCol +1; j > 1; j--) {

                //test case entries that are not 0
                if (img[i][j] > 0) {
                    //~
                    System.out.println("p2: i="+i+" j="+j );
                    //load the four neighbors
                    neighborAry[0] = img[i][j];
                    neighborAry[1] = img[i][j+1];
                    neighborAry[2] = img[i+1][j-1];
                    neighborAry[3] = img[i+1][j];
                    neighborAry[4] = img[i+1][j+1];
                    //~
                    System.out.println("before sort");
                    for(int t = 0; t<5; t++){
                        System.out.print(neighborAry[t]+" ");
                    }
                    Arrays.sort(neighborAry);
                    //~
                    System.out.println("after Sort");
                    for(int t = 0; t<5; t++){
                        System.out.print(neighborAry[t]+" ");
                    }

                    if (neighborAry[3] == 0) {
                        System.out.println("case 1");
                        //do nothing
                    }//case1 all entries are 0
                    if (distinctElementGreaterThanZero(neighborAry,2) == 1) {
                        //do nothing
                        System.out.println("case 2 ");
                    }//case 2 if they're all same label, assign label to entrie
                    if (distinctElementGreaterThanZero(neighborAry,2) >= 2) {
                        System.out.println("case 3");
                        img[i][j] = minValueInArrayGreaterThanZero(neighborAry);
                        for(int z = 0; z< 5; z++){
                            if(neighborAry[z] >0 ){
                                EQAry[neighborAry[z]] =img[i][j];
                            }//if element is not 0
                        }//fori update EQ table

                    }// case 3 if two or more distinct labels, assign  min to entrie
                }//if entrie is 1 , it's a component part
            }//forj
        }//fori loop through each entrie that is >0

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile + "_pass2.txt"));
            writer.write(numRow+" "+numCol+" "+ minVal+" "+maxVal);
            writer.write(System.getProperty("line.separator"));
            for (int i = 1; i <= img.length -1; i++) {
                for(int j = 1; j < img[0].length-1; j++ ) {
                    writer.write(img[i][j]+" ");
                }//forj
                writer.write(System.getProperty("line.separator"));
            }//fori
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }//catch write


        return img;
    }//pass two

    static int[] manageEQAry(int[] arr ){

    }//manageEQAry

    public static void main(String[] args) {
        try {
            Scanner inputFile = new Scanner(new File(args[0]));
            //read in first 4 header values
            numRow = inputFile.nextInt();
            numCol = inputFile.nextInt();
            minVal = inputFile.nextInt();
            maxVal = inputFile.nextInt();
            int[][] zeroFramedAry = new int[numRow + 2][numCol + 2];
            System.out.println("after creating 2d size");
            System.out.println(zeroFramedAry.length + " " + zeroFramedAry[0].length);
            //load the image
            for (int i = 1; i < zeroFramedAry.length - 1; i++) {
                for (int j = 1; j < zeroFramedAry[0].length - 1; j++) {
                    if (inputFile.hasNextInt()) {
                        zeroFramedAry[i][j] = inputFile.nextInt();
                    }
                }//fori
            }//fori
            pass1(zeroFramedAry, args[1]); //pass1
            System.out.println("after pass1");
            for (int i = 0; i < numRow + 2; i++) {
                for (int j = 0; j < numCol + 2; j++) {
                    System.out.print(zeroFramedAry[i][j] + " ");
                }
                System.out.println();
            }//for j print pass 1 matrix
            //~print EQ
            for (int i = 0; i <= maxLabel; i++) {
                System.out.print(EQAry[i] + " ");
            }//print the EQtable

            //~~~~~~~~~~~~~~~~~~~~
            pass2(zeroFramedAry, "pass2222222222"); //pass1
            System.out.println("after pass2");
            for (int i = 0; i < numRow + 2; i++) {
                for (int j = 0; j < numCol + 2; j++) {
                    System.out.print(zeroFramedAry[i][j] + " ");
                }
                System.out.println();
            }//for j print pass 1 matrix
            //~print EQ
            for (int i = 0; i <= maxLabel; i++) {
                System.out.print(EQAry[i] + " ");
            }//print the EQtable
            //~~~~~~~~~~~~~~~




            inputFile.close();
        } catch (FileNotFoundException fe) {
            System.out.println("File not found!");
        }//catch scan
    }//main
}//class
