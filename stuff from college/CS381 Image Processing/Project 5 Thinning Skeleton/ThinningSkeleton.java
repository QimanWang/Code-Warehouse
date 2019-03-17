import java.io.*;
import java.util.Scanner;
import java.util.*;

public class ThinningSkeleton {
    static int numRow, numCol, minVal, maxVal, cycleCount = 0;
    static boolean changeFlag;
    static int[][] firstAry, secondAry;

    static int newLabel;
    static int[] neighborAry;
    static int[][] pass1(int[][] img) {
        newLabel = 0;
        neighborAry = new int[4];
        //loop trough image to process each pixel
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
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
                    if (distinctElementGreaterThanZero(neighborAry, 1) == 1) {
                        img[i][j] = neighborAry[3];
                    }//case 2 if they're all same label, assign label to entrie
                    if (distinctElementGreaterThanZero(neighborAry, 1) >= 2) {
                        img[i][j] = minValueInArrayGreaterThanZero(neighborAry);
                    }// case 3 if two or more distinct labels, assign  min to entrie
                }//if entrie is 1 , it's a component part
            }//forj
        }//fori loop through each entrie that is >0
        return img;
    }//pass one
    static int pass2(int[][] img) {
        neighborAry = new int[5];
        //loop trough image to process each pixel
        for (int i = 3; i > 0; i--) {
            for (int j = 3; j > 0; j--) {
                //test case entries that are not 0
                if (img[i][j] > 0) {
                    neighborAry[0] = img[i][j];
                    neighborAry[1] = img[i][j + 1];
                    neighborAry[2] = img[i + 1][j - 1];
                    neighborAry[3] = img[i + 1][j];
                    neighborAry[4] = img[i + 1][j + 1];

                    Arrays.sort(neighborAry);
                    if (neighborAry[3] == 0) {
                        //do nothing
                    }//case1 all entries are 0
                    if (distinctElementGreaterThanZero(neighborAry, 2) == 1) {
                        //do nothing
                    }//case 2 if they're all same label, assign label to entrie
                    if (distinctElementGreaterThanZero(neighborAry, 2) >= 2) {
                        img[i][j] = minValueInArrayGreaterThanZero(neighborAry);
                    }// case 3 if two or more distinct labels, assign  min to entrie
                }//if entrie is 1 , it's a component part
            }//forj
        }//fori loop through each entrie that is >0
        int numCC=0;
        for(int k=0; k<img.length; k++){
            for(int l=0; l<img[0].length;l++){
                if(img[k][l]>numCC){
                    numCC=img[k][l];
                }
            }//forl
        }//for k: return max number in connected component
        return numCC;
    }//pass two
    static int distinctElementGreaterThanZero(int[] lit, int pass) {
        int s = 3 + pass - 1;
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

    ThinningSkeleton(String inFile) {
        try {
            Scanner inputFile = new Scanner(new File(inFile));
            //read in first 4 header values
            numRow = inputFile.nextInt();
            numCol = inputFile.nextInt();
            minVal = inputFile.nextInt();
            maxVal = inputFile.nextInt();
            firstAry= createZeroFramedAry(firstAry, numRow, numCol);
            secondAry= createZeroFramedAry(secondAry, numRow, numCol);
            //loadimage
            for (int i = 1; i < firstAry.length - 1; i++) {
                for (int j = 1; j < firstAry[0].length - 1; j++) {
                    firstAry[i][j] = inputFile.nextInt();
                }//fori
            }//fori
            inputFile.close();
        } catch (FileNotFoundException fe) {
            System.out.println("File not found!");
        }//load image
    }//construcor

    static int[][] createZeroFramedAry(int[][] arr, int numRow, int numCol) {
        arr = new int[numRow + 2][numCol + 2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = 0;
            }//forj
        }//fori
        return arr;
    }//create zero framed array

    static boolean threeOrMoreNonZeros(int[][] imgAry, int i, int j){

        //~ load test array items
        int[] nneighborAry= new int[9];
        nneighborAry[0] =imgAry[i-1][j-1];
        nneighborAry[1] =imgAry[i-1][j];
        nneighborAry[2] =imgAry[i-1][j+1];
        nneighborAry[3] =imgAry[i][j-1];
        nneighborAry[4] =imgAry[i][j];
        nneighborAry[5] =imgAry[i][j+1];
        nneighborAry[6] =imgAry[i+1][j-1];
        nneighborAry[7] =imgAry[i+1][j];
        nneighborAry[8] =imgAry[i+1][j+1];
        Arrays.sort(nneighborAry);
        if(nneighborAry[4]>0){
            return true;
        }
        return false;
    }//check if there is 3 ore more neighbor >0

    static int connectedComonents(int[][] imgAry,int i, int j){
        int[][] neighborImgAry={
                {imgAry[i-1][j-1], imgAry[i-1][j], imgAry[i-1][j+1]},
                {imgAry[i][j-1], 0, imgAry[i][j+1]}, //change it to 0
                {imgAry[i+1][j-1], imgAry[i+1][j], imgAry[i+1][j+1]}};

        int[][] zeroFramedNeighborImgAry= createZeroFramedAry(neighborImgAry,3,3);
        for(int k=1; k<zeroFramedNeighborImgAry.length-1;k++){
            for(int l=1; l<zeroFramedNeighborImgAry[0].length-1; l++){
                zeroFramedNeighborImgAry[k][l] =neighborImgAry[k-1][l-1];
            }
        }//zero framed the array to prepare for connected component operation
        pass1(zeroFramedNeighborImgAry);
        int numCC =pass2(zeroFramedNeighborImgAry);
        return numCC;
    }//connected component methods

    static  void doThinning(int[][] imgAry, int i, int j){
        if(threeOrMoreNonZeros(imgAry,i,j)){
            //cc test
            if(connectedComonents(imgAry,i,j)<2){
                secondAry[i][j]=0;
                changeFlag = true;
            }//if will not create two or more component
        }// test - if there is 3 or more non zero
    }//thinning

    static void northThinning(int[][] imgAry){
        for(int i=1; i < numRow+1;i++){
            for(int j = 1; j< numCol+1; j++){
                if(imgAry[i][j]>0 && imgAry[i-1][j]==0){
                    doThinning(imgAry, i, j);
                }//1st test - if pixel > 0 and north neighbor is 0, do north
            }//forj
        }//fori
    }//north thinning
    static void southThinning(int[][] imgAry){
        for(int i=1; i < numRow+1;i++){
            for(int j = 1; j< numCol+1; j++){
                if(imgAry[i][j]>0 && imgAry[i+1][j]==0){
                    doThinning(imgAry, i, j);
                }//1st test - if pixel > 0 and south neighbor is 0, do north
            }//forj
        }//fori
    }//north thinning
    static void eastThinning(int[][] imgAry){
        for(int i=1; i < numRow+1;i++){
            for(int j = 1; j< numCol+1; j++){
                if(imgAry[i][j]>0 && imgAry[i][j+1]==0){
                    doThinning(imgAry, i, j);
                }//1st test - if pixel > 0 and north neighbor is 0, do north
            }//forj
        }//fori
    }//north thinning
    static void westThinning(int[][] imgAry){
        for(int i=1; i < numRow+1;i++){
            for(int j = 1; j< numCol+1; j++){
                if(imgAry[i][j]>0 && imgAry[i][j-1]==0){
                    doThinning(imgAry, i, j);
                }//1st test - if pixel > 0 and west neighbor is 0, do north
            }//forj
        }//fori
    }//north thinning
    static void copyAry(int[][] from, int[][] to){
        for(int i=0; i < from.length; i++){
            for(int j=0; j <from[0].length; j++){
                to[i][j]= from[i][j];
            }
        }
    }//copy items from array:"from" to array:"to"
    static void createImageWithHeader(int[][] imgAry, String outFileName) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));
            writer.write(numRow+" "+numCol+" "+minVal+" "+maxVal);
            writer.write(System.getProperty("line.separator"));
            for (int i = 1; i < numRow + 1; i++) {
                for (int j = 1; j < numCol + 1; j++) {
                    writer.write(imgAry[i][j]+" ");
                }//forj
                writer.write(System.getProperty("line.separator"));
            }//fori
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }//catch write
    }//createImageWithHeader , the file to be future processed.

    public static void main(String[] args) {
        ThinningSkeleton thinSkeleton = new ThinningSkeleton(args[0]);
        copyAry(firstAry,secondAry);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[3]));

        cycleCount =0;
        changeFlag =true;
        while (changeFlag){
            changeFlag=false;
            northThinning(firstAry);
            copyAry(secondAry, firstAry);
            southThinning(firstAry);
            copyAry(secondAry, firstAry);
            eastThinning(firstAry);
            copyAry(secondAry, firstAry);
            westThinning(firstAry);
            copyAry(secondAry, firstAry);
            //cycle count
            if(cycleCount==0 || cycleCount==3 || cycleCount==5 ||!changeFlag ){
                writer.write("cycle# "+cycleCount);
                for(int i = 1; i < numRow + 1; i++) {
                    for (int j = 1; j < numCol + 1; j++) {
                        if(firstAry[i][j]>0) {
                            writer.write(firstAry[i][j] + " ");
                        }//if >1
                        else{
                            writer.write("  ");
                        }//else blank
                    }//forj
                    writer.write(System.getProperty("line.separator"));
                }//fori
            }
            cycleCount++;
        }//while changeFlag
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }//catch write
        createImageWithHeader(firstAry,args[1]);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[2]));
            writer.write("Final Skeleton Result:");
            writer.write(System.getProperty("line.separator"));
            for(int i = 1; i < numRow + 1; i++) {
                for (int j = 1; j < numCol + 1; j++) {
                    if(firstAry[i][j]>0) {
                        writer.write(firstAry[i][j] + " ");
                    }//if >1
                    else{
                        writer.write("  ");
                    }//else blank
                    }//forj
                writer.write(System.getProperty("line.separator"));
                }//forj
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }//catch write
    }//main
}//thinning class
