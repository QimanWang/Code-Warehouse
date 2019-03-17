
import java.io.*;
import java.util.*;
public class ChainCode {

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }//point constructor
        Point(){
            this.x=1;
            this.y=1;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }

    }//point class

    static Point neighborCoord[] = new Point[8];
    static Point currentP;
        static Point nextP = new Point();
        static int lastQ;
        static int nextDirTable[] = {6,0,0,0,2,4,4,4};
        static int nextDir;
        static int Pchain;
        static int iRow, jCol;
        static int nextQ ;
        static int label;
        static int chainDir;

    public static void loadNeighborCoord(Point coordinate) {
        //initialize the neighborCoordinate array
        neighborCoord[0] = new Point(coordinate.x, coordinate.y + 1);
        neighborCoord[1] = new Point(coordinate.x - 1, coordinate.y + 1);
        neighborCoord[2] = new Point(coordinate.x - 1, coordinate.y);
        neighborCoord[3] = new Point(coordinate.x - 1, coordinate.y - 1);
        neighborCoord[4] = new Point(coordinate.x, coordinate.y - 1);
        neighborCoord[5] = new Point(coordinate.x + 1, coordinate.y - 1);
        neighborCoord[6] = new Point(coordinate.x + 1, coordinate.y);
        neighborCoord[7] = new Point(coordinate.x + 1, coordinate.y + 1);
        System.out.println(" loading neighbors");
        for(int z =0; z<neighborCoord.length; z++){
            System.out.print(ChainCode.neighborCoord[z].x+":"+ChainCode.neighborCoord[z].y+" ");
        }
        //currentP is the coordinate parameter


    }// chain code constructor contain load neighborCoord

    //methods start here
    public static int findNextP(Point currentP, int nextQ, Point nextP ) {
        loadNeighborCoord(currentP);

        //~
        for(int i=0; i < 8; i++) {
            System.out.println("loop "+ (i+1) +" looking at dirQ "+ ((nextQ +i)%8));
            System.out.println("x: "+neighborCoord[(nextQ + i) % 8].getX());
            System.out.println("y: "+neighborCoord[(nextQ + i) % 8].getY());

            System.out.println("value at i: "+Image.zeroFramedAry[neighborCoord[(nextQ + i) % 8].getX()]
                    [neighborCoord[(nextQ + i) % 8].getY()]);
            if (Image.zeroFramedAry[neighborCoord[(nextQ + i) % 8].getX()]
                    [neighborCoord[(nextQ + i) % 8].getY()] == label) {
                chainDir = (nextQ + i) % 8;
                System.out.println("chainDir/nextdir: " + chainDir);
                //!!
               /*
                for(int p=0; p < neighborCoord.length; p++){
                    System.out.println(neighborCoord[p].getX()+","+neighborCoord[p].getY());
                }
                */
                nextP.x = neighborCoord[chainDir].x;
                nextP.y = neighborCoord[chainDir].y;
                System.out.println("nextP: " +nextP.getX()+","+nextP.getY());

                break;
            }

        }
        return chainDir;
    }//find NextP

//~~~~~~~~~~~~IMAGE CLASS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      public static class Image {
          static int numRow, numCol, minVal, maxVal = 0;
          static int[][] zeroFramedAry;
          static int[][] createZeroFramedAry(int[][] arr, int numRow, int numCol, int frameSize) {
              arr = new int[numRow + frameSize][numCol + frameSize];
              for (int i = 0; i < arr.length; i++) {
                  for (int j = 0; j < arr[0].length; j++) {
                      arr[i][j] = 0;
                  }//forj
              }//fori
              return arr;
          }//create zero framed array

          Image(String inFile) {
              try {
                  Scanner inputFile = new Scanner(new File(inFile));
                  //read in first 4 header values
                  numRow = inputFile.nextInt();
                  numCol = inputFile.nextInt();
                  minVal = inputFile.nextInt();
                  maxVal = inputFile.nextInt();
                  zeroFramedAry = createZeroFramedAry(zeroFramedAry, numRow, numCol, 2);
                  boolean findFirst=true;
                  //loadimage
                  for (int i = 1; i < zeroFramedAry.length - 1; i++) {
                      for (int j = 1; j < zeroFramedAry[0].length - 1; j++) {
                          zeroFramedAry[i][j] = inputFile.nextInt();
                          if(findFirst) {
                              if (zeroFramedAry[i][j]>0){
                                  iRow = i;
                                  jCol=j;
                                  label = zeroFramedAry[i][j];
                                  findFirst=false;
                              }
                          }
                      }//fori
                  }//fori
                  inputFile.close();
              } catch (FileNotFoundException fe) {
                  System.out.println("File not found!");
              }//load image
          }//construcor

      }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {
        try {
            BufferedWriter writer1 = new BufferedWriter(new FileWriter( args[1]));
            BufferedWriter writer2 = new BufferedWriter(new FileWriter( args[2]));

            Image InputImage = new Image(args[0]);
        //~
        System.out.println("zero framed loaded ");
        for(int i=0;i< InputImage.zeroFramedAry.length; i++){
            System.out.print(i+" :");
            for(int j=0; j< InputImage.zeroFramedAry[0].length; j++){
                System.out.print(InputImage.zeroFramedAry[i][j]+" ");
            }//fori
            System.out.println();

        }//forj

        Point startP = new Point(iRow,jCol);
        System.out.println("start (" +startP.x +","+startP.y+")");
        currentP = new Point(iRow,jCol);
        System.out.println("start (" +currentP.x +","+currentP.y+")");
        lastQ =4;
        System.out.println("last Q: " +lastQ);


            writer1.write(Image.numRow + " " + Image.numCol + " " + Image.minVal + " " + Image.maxVal);
            writer1.write(System.getProperty("line.separator"));
            writer2.write(Image.numRow + " " + Image.numCol + " " + Image.minVal + " " + Image.maxVal);
            writer2.write(System.getProperty("line.separator"));
            writer2.write("Start row: "+ iRow +" Start Col: "+ jCol);

            int token =0;
        do {
           // System.out.println("current point: "+ currentP);
            //System.out.println("startpoint" +startP);
        //for(int t = 0; t < 100; t++) {
        //    int t=1;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            nextQ = (lastQ+1)%8;
            System.out.println("next Q: " +nextQ);
            System.out.println(nextQ);
            System.out.println("parameters: " + currentP.getX() +":"+currentP.getY() + ", " + nextQ + ", " + nextP.getX()+","+nextP.getY());
            Pchain = findNextP(currentP, nextQ, nextP);
            System.out.println("DIRECTION: " +Pchain);

            lastQ = nextDirTable[Pchain];


            currentP.setX(nextP.getX());
            currentP.setY(nextP.getY());
            token++;

            if(token%20 ==0){
                writer2.write(System.getProperty("line.separator"));
            }
            writer1.write(Pchain+",");
            writer2.write(Pchain+",");

            //    t++;
        }while(currentP.getX()!= startP.getX() || currentP.getY() != startP.getY());
        //}// while until currentP == startP

            writer1.close();
            writer2.close();
    } catch (IOException e) {
        e.printStackTrace();
    }//catch write
        System.out.println(iRow+":"+jCol);
    }//main
}//chain code outer class
