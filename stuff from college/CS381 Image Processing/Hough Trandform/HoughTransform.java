import java.io.*;
import java.util.*;

public class HoughTransform {
    private class XYCoord {
        public int x;
        public int y;
    }//xycoord class
    private XYCoord point;
    private ImageProcessing image;
    private int angleInDegrees;
    private double angleInRadians;
    private int numRows;
    private int numCols;
    private int calculatedField;
    private int minVal;
    private int maxVal;
    private int[][] HoughAry;
    final static double pi = 3.14159265359;

    public HoughTransform(Scanner infile) {
        image = new ImageProcessing(infile);
        point = new XYCoord();
        point.x = 0;
        point.y = 0;
        angleInDegrees = 0;
        angleInRadians = .0;
        numRows = 180;
        numCols = 2 * (int) Math.sqrt(Math.pow(image.getNumRows(), 2) + Math.pow(image.getNumCols(), 2));
        minVal = 2 * (int) Math.sqrt(Math.pow(image.getNumRows(), 2) + Math.pow(image.getNumCols(), 2));
        maxVal = -1999999999;
        HoughAry = new int[numRows][];
        for (int r = 0; r < numRows; r++) {
            HoughAry[r] = new int[numCols];
            for (int c = 0; c < numCols; c++) HoughAry[r][c] = 0;
        }
        calculatedField = (int) Math.sqrt(Math.pow(image.getNumRows(), 2) + Math.pow(image.getNumCols(), 2));
    }//houghTransform constructor

    public int computeDistance() {
        angleInRadians = angleInDegrees * (pi / 180);
        double t = angleInRadians - Math.atan(point.y / point.x) - (pi / 2);
        return (int) (Math.sqrt(Math.pow(point.x, 2) + Math.pow(point.y, 2)) * Math.cos(t));
    }//compute distance

    public void determineHeader() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (HoughAry[r][c] > maxVal) maxVal = HoughAry[r][c];
                if (HoughAry[r][c] < minVal) minVal = HoughAry[r][c];
            }
        }
    }//determine header

    public void runAlgorithm(PrintWriter outfile) {
        for (int r = 0; r < image.getNumRows(); r++) {
            for (int c = 0; c < image.getNumCols(); c++) {
                if (image.getImgAry()[r][c] > 0) {
                    point.x = r;
                    point.y = c;
                    angleInDegrees = 0;
                    angleInRadians = 0.0;
                    while (angleInDegrees < 180) {
                        int d = computeDistance();
                        HoughAry[angleInDegrees][d + calculatedField]++;
                        angleInDegrees++;
                    }
                }
            }
        }
        determineHeader();
        outfile.print(numRows + " " + numCols + " " + minVal + " " + maxVal);
        for (int r = 0; r < numRows; r++) {
            outfile.println();
            for (int c = 0; c < numCols; c++) {
                outfile.print(HoughAry[r][c] + " ");
            }
        }
    }//run algorithm

    public class ImageProcessing {
        private int numRows;
        private int numCols;
        private int minVal;
        private int maxVal;
        private int[][] imgAry; 

        public ImageProcessing(Scanner infile) {
            numRows = infile.nextInt();
            numCols = infile.nextInt();
            minVal = infile.nextInt();
            maxVal = infile.nextInt();
            imgAry = new int[numRows][];
            for (int i = 0; i < numRows; i++) {
                imgAry[i] = new int[numCols];
                for (int j = 0; j < numCols; j++) imgAry[i][j] = 0;
            }
            loadImage(infile);
        }//constructor
        public void loadImage(Scanner infile) {
            int row = 0, col = 0;
            while (infile.hasNextInt()) {
                imgAry[row][col] = infile.nextInt();
                col++;
                if (col >= numCols) {
                    col = 0;
                    row++;
                }
            }
        }//load image
        public int getNumRows() {
            return numRows;
        }
        public void setNumRows(int numRows) {
            this.numRows = numRows;
        }
        public int getNumCols() {
            return numCols;
        }
        public void setNumCols(int numCols) {
            this.numCols = numCols;
        }
        public int[][] getImgAry() {
            return imgAry;
        }
        public void setImgAry(int[][] imgAry) {
            this.imgAry = imgAry;
        }
    }//img class

    public static void main(String[] args) throws FileNotFoundException {

        Scanner infile = new Scanner(new FileReader(args[0]));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]))));

        HoughTransform houghTransform = new HoughTransform(infile);
        houghTransform.runAlgorithm(outfile);

        infile.close();
        outfile.close();
    }//main class
}//hough class
