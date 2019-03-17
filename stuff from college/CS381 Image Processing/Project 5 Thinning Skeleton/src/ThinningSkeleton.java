import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
public class ThinningSkeleton {
    static int numRow, numCol, minVal, maxVal, cycleCount = 0;
    boolean changeFlag;
    static int[][] firstAry, secondAry;

    //cc
    static int newLabel, maxLabel;
    static int[] EQAry, neighborAry, frequency;
    static int[][] pass1(int[][] img) {
        newLabel = 0;
        neighborAry = new int[4];
        int size = (int) Math.ceil((3 * 3) / 2);
        EQAry = new int[size];
        for (int i = 0; i < EQAry.length; i++) {
            EQAry[i] = i;
        }//set EQAry entries to index +1
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
                        for (int z = 0; z < 4; z++) {
                            if (neighborAry[z] > 0) {
                                EQAry[neighborAry[z]] = img[i][j];
                            }//if element is not 0
                        }//fori update EQ table
                    }// case 3 if two or more distinct labels, assign  min to entrie
                }//if entrie is 1 , it's a component part
            }//forj
        }//fori loop through each entrie that is >0
        maxLabel = newLabel + 1;
        maxVal = 0;
        for (int y = 0; y <= maxLabel; y++) {
            if (EQAry[y] > maxVal) {
                maxVal = EQAry[y] + 1;
            }
        }

        return img;
    }//pass one`
    static int pass2(int[][] img) {
        neighborAry = new int[5];
        //loop trough image to process each pixel
        for (int i = 4; i > 1; i--) {
            for (int j = 4; j > 1; j--) {
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
                        for (int z = 0; z < 5; z++) {
                            if (neighborAry[z] > 0) {
                                EQAry[neighborAry[z]] = img[i][j];
                            }//if element is not 0
                        }//fori update EQ table
                    }// case 3 if two or more distinct labels, assign  min to entrie
                }//if entrie is 1 , it's a component part
            }//forj
        }//fori loop through each entrie that is >0
        int numCC =manageEQAry(EQAry);
        return numCC;
    }//pass two
    static int manageEQAry(int[] arr) {
        int trueLabel = 0;
        for (int i = 1; i < maxLabel; i++) {
            if (arr[i] == i) {
                trueLabel++;
                arr[i] = trueLabel;
            } else {
                arr[i] = arr[arr[i]];
            }
        }
        return trueLabel;
    }//manageEQAry
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
    //cc


    ThinningSkeleton(String inFile) {
        try {
            Scanner inputFile = new Scanner(new File(inFile));
            //read in first 4 header values
            numRow = inputFile.nextInt();
            numCol = inputFile.nextInt();
            minVal = inputFile.nextInt();
            maxVal = inputFile.nextInt();
            firstAry= createZeroFramedAry(firstAry, numRow, numCol);
            //~
            System.out.println("created zero frame ary first");
            for(int i=0; i <firstAry.length; i++){
                for(int j=0; j < firstAry[0].length; j++){
                    System.out.print(firstAry[i][j]);
                }
                System.out.println();
            }

            secondAry= createZeroFramedAry(secondAry, numRow, numCol);
            //loadimage
            for (int i = 1; i < firstAry.length - 1; i++) {
                for (int j = 1; j < firstAry[0].length - 1; j++) {
                    firstAry[i][j] = inputFile.nextInt();
                }//fori
            }//fori
            //~
            System.out.println("after firstAry is loaded");
            for(int i=0; i <firstAry.length; i++){
                for(int j=0; j < firstAry[0].length; j++){
                    System.out.print(firstAry[i][j]);
                }
                System.out.println();
            }
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
        int[] nneighborAry = {
                imgAry[i-1][j-1], imgAry[i-1][j], imgAry[i-1][j+1],
                imgAry[i][j-1],imgAry[i][j],imgAry[j][j+1],
                imgAry[i+1][j-1],imgAry[i+1][j],imgAry[i+1][j+1]
        }; // load all 9 9 neighbors
        //~
        System.out.printf("nneighborAry before sort");
        for(int z =0; z< nneighborAry.length;z++){
            System.out.print(nneighborAry[z]+" ");
        }
        System.out.println();

        Arrays.sort(nneighborAry);
        //~
        System.out.printf("nneighbor after Sort");
        for(int z =0; z< nneighborAry.length;z++){
            System.out.print(nneighborAry[z]+" ");
        }
        System.out.println();

      if(nneighborAry[6]>0){
          return true;
      }
      return false;
    }//count number of zero

    static int connectedComonents(int[][] imgAry,int i, int j){
        int[][] neighborImgAry = {
                {imgAry[i-1][j-1], imgAry[i-1][j], imgAry[i-1][j+1]},
                {imgAry[i][j-1],0,imgAry[j][j+1]},
                {imgAry[i+1][j-1],imgAry[i+1][j],imgAry[i+1][j+1]}
        }; // load all 9 9 neighbors
        int[][] zeroFramedNeighborImgAry= createZeroFramedAry(neighborImgAry,3,3);
        pass1(zeroFramedNeighborImgAry);
        int numCC =pass2(zeroFramedNeighborImgAry);

        return numCC;
    }//connected component methods
    //pass1

    static  void doThinning(){
        //this.cycleCount =0;
        //this.changeFlag = false;
        //while(changeFlag==false){
        System.out.println("start north thinning");
        northThinning(firstAry);


    }//thinning

    public static void northThinning(int[][] imgAry){
        for(int i=1; i < numRow+1;i++){
            for(int j = 1; j< numCol+1; j++){
               if(imgAry[i][j]>0 && imgAry[i-1][j]==0){
                   System.out.println("i:" +i +"j:"+j);
                   if(threeOrMoreNonZeros(imgAry,i,j)){
                       System.out.println("3 or more zeros");
                      /*
                       //cc on 3x3
                       if(connectedComonents(imgAry,i,j)<2){
                            imgAry[i][j] =0;
                       }//if will not create two or more component
                    */
                   }//2nd test - if there is 3 or more non zero

               }//1st test - if pixel > 0 and north neighbor is 0, do north
            }//forj
        }//fori
    }//north thinning




    public static void main(String[] args) {
        ThinningSkeleton thinSkeleton = new ThinningSkeleton(args[0]);
        thinSkeleton.doThinning();


    }//main




}//thinning class
/*
        System.out.println("firstary output");
        for(int i=0; i < firstAry.length; i ++){
            for (int j =0; j <firstAry.length; j++){
                System.out.print(firstAry[i][j]);
            }
            System.out.println();
        }
 */
