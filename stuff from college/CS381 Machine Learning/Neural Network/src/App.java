import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class App {

    public static double XOR_INPUT[][] = new double[209][7];
    public static double XOR_IDEAL[] = new double[209];
    public static double ONE_FOLD_INPUT[][] = new double[168][7];
    public static double ONE_FOLD_IDEAL[] = new double[168];
    public static double TWO_FOLD_INPUT[][] = new double[168][7];
    public static double TWO_FOLD_IDEAL[] = new double[168];
    public static double THREE_FOLD_INPUT[][] = new double[168][7];
    public static double THREE_FOLD_IDEAL[] = new double[168];
    public static double FOUR_FOLD_INPUT[][] = new double[168][7];
    public static double FOUR_FOLD_IDEAL[] = new double[168];
    public static double FIVE_FOLD_INPUT[][] = new double[168][7];
    public static double FIVE_FOLD_IDEAL[] = new double[168];

    //  public static double NORMALIZE_CONSTANT[] = {1500,32000,64000,256,52,176,1150,1238 };
    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(new FileReader("Machinez.txt"));
        //read in main file with all data points
        while (sc.hasNext()) {
            for (int i = 0; i < 209; i++) {
                for (int j = 0; j < 7; j++) {
                    XOR_INPUT[i][j] = sc.nextDouble();
                }//j
                XOR_IDEAL[i]  =sc.nextDouble();
            }//i
        }//while
      // 111111 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            Scanner sc1 = new Scanner(new FileReader("1FOLD.txt"));
            while (sc1.hasNext()) {
                for (int i = 0; i < 167; i++) {
                    for (int j = 0; j < 7; j++) {
                        ONE_FOLD_INPUT[i][j] = sc1.nextDouble();
                    }//j
                    ONE_FOLD_IDEAL[i]  =sc1.nextDouble();
                }//i
            }//while
        //create neural network
        NeuralNetwork network1 = new NeuralNetwork(7, 3, 1);
        int epoch1 = 0;
        System.out.println("Testing DATA: 1-42, Training Data:43-209 ");
        System.out.println("Start MSE is: " + network1.calculateNetworkError(ONE_FOLD_INPUT, ONE_FOLD_IDEAL));
        do {
            ++epoch1;
            for (int i = 0; i < ONE_FOLD_INPUT.length; ++i) {
                double[] inputs = ONE_FOLD_INPUT[i];
                double target = ONE_FOLD_IDEAL[i];
                network1.feedforward(inputs);
                network1.backpropagation(target);

            }System.out.println("Iteration: "+ epoch1 +" Error MSE is: " + network1.calculateNetworkError(ONE_FOLD_INPUT, ONE_FOLD_IDEAL));

        } while (network1.calculateNetworkError(ONE_FOLD_INPUT, ONE_FOLD_IDEAL) >Constants.ERROR_THRESHOLD );
        for(int i = 0; i <42; i++){
            System.out.print("Data " +(i+1)+ " Actual: "+(XOR_IDEAL[i])+" predicted ");
            network1.testNetwork(XOR_INPUT[i]);
        }
        // 222222222 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            Scanner sc2 = new Scanner(new FileReader("2FOLD.txt"));

            while (sc2.hasNext()) {
                for (int i = 0; i < 167; i++) {
                    for (int j = 0; j < 7; j++) {
                        TWO_FOLD_INPUT[i][j] = sc2.nextDouble();
                    }//j
                    TWO_FOLD_IDEAL[i]  =sc2.nextDouble();
                }//i
            }//while
        //create neural network
        NeuralNetwork network2 = new NeuralNetwork(7, 3, 1);
        int epoch2 = 0;
        System.out.println("Testing DATA: 43-84, Training Data:1-42,85-209 ");
        System.out.println("Start MSE is: " + network2.calculateNetworkError(TWO_FOLD_INPUT, TWO_FOLD_IDEAL));
        do {
            ++epoch2;
            for (int i = 0; i < TWO_FOLD_INPUT.length; ++i) {
                double[] inputs = TWO_FOLD_INPUT[i];
                double target = TWO_FOLD_IDEAL[i];
                network2.feedforward(inputs);
                network2.backpropagation(target);

                if (epoch2 % 1000 == 0) {
                    System.out.println("Iteration: "+ epoch2 +"Error MSE is: " + network2.calculateNetworkError(TWO_FOLD_INPUT, TWO_FOLD_IDEAL));
                }
            }
        } while (network2.calculateNetworkError(TWO_FOLD_INPUT, TWO_FOLD_IDEAL) > Constants.ERROR_THRESHOLD);
        for(int i = 42; i <84; i++){
            System.out.print("Data " +(i+1)+ "Actual: "+(XOR_IDEAL[i])+" predicted ");
            network2.testNetwork(XOR_INPUT[i]);
        }
        //  3333~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            Scanner sc3 = new Scanner(new FileReader("3FOLD.txt"));

            while (sc3.hasNext()) {
                for (int i = 0; i < 167; i++) {
                    for (int j = 0; j < 7; j++) {
                        THREE_FOLD_INPUT[i][j] = sc3.nextDouble();
                    }//j
                    THREE_FOLD_IDEAL[i]  =sc3.nextDouble();
                }//i
            }//while
//create neural network
        NeuralNetwork network3 = new NeuralNetwork(7, 3, 1);
        int epoch3 = 0;
        System.out.println("Testing DATA: 85-126, Training Data:1-84,127-209 ");
        System.out.println("Start MSE is: " + network3.calculateNetworkError(THREE_FOLD_INPUT, THREE_FOLD_IDEAL));
        do {
            ++epoch3;
            for (int i = 0; i < THREE_FOLD_IDEAL.length; ++i) {
                double[] inputs = THREE_FOLD_INPUT[i];
                double target = THREE_FOLD_IDEAL[i];
                network3.feedforward(inputs);
                network3.backpropagation(target);

                if (epoch3 % 1000 == 0) {
                    System.out.println("Iteration: "+ epoch3 +"Error MSE is: " + network3.calculateNetworkError(THREE_FOLD_INPUT, THREE_FOLD_IDEAL));
                }
            }
        } while (network3.calculateNetworkError(THREE_FOLD_INPUT, THREE_FOLD_IDEAL) > Constants.ERROR_THRESHOLD);
        for(int i = 84; i <126; i++){
            System.out.print((i+1)+ "Actual: "+(XOR_IDEAL[i])+" predicted ");
            network3.testNetwork(XOR_INPUT[i]);
        }
        //  44444444~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            Scanner sc4 = new Scanner(new FileReader("4FOLD.txt"));

            while (sc4.hasNext()) {
                for (int i = 0; i < 167; i++) {
                    for (int j = 0; j < 7; j++) {
                        FOUR_FOLD_INPUT[i][j] = sc4.nextDouble();
                    }//j
                    FOUR_FOLD_IDEAL[i]  =sc4.nextDouble();
                }//i
            }//while
//create neural network
        NeuralNetwork network4 = new NeuralNetwork(7, 3, 1);
        int epoch4 = 0;
        System.out.println("Testing DATA: 127-84, Training Data:1-126,-209 ");
        System.out.println("Start MSE is: " + network4.calculateNetworkError(FOUR_FOLD_INPUT, FOUR_FOLD_IDEAL));
        do {
            ++epoch4;
            for (int i = 0; i < FOUR_FOLD_IDEAL.length; ++i) {
                double[] inputs = FOUR_FOLD_INPUT[i];
                double target = FOUR_FOLD_IDEAL[i];
                network4.feedforward(inputs);
                network4.backpropagation(target);

                if (epoch4 % 1000 == 0) {
                    System.out.println("Iteration: "+ epoch2 +"Error MSE is: " + network2.calculateNetworkError(FOUR_FOLD_INPUT, FOUR_FOLD_IDEAL));
                }
            }
        } while (network4.calculateNetworkError(FOUR_FOLD_INPUT, FOUR_FOLD_IDEAL) > Constants.ERROR_THRESHOLD);
        for(int i = 126; i <168; i++){
            System.out.print((i+1)+ "Actual: "+(XOR_IDEAL[i])+" predicted ");
            network4.testNetwork(XOR_INPUT[i]);
        }
        // 55555555555555 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            Scanner sc5 = new Scanner(new FileReader("5FOLD.txt"));

            while (sc5.hasNext()) {
                for (int i = 0; i < 168; i++) {
                    for (int j = 0; j < 7; j++) {
                        FIVE_FOLD_INPUT[i][j] = sc5.nextDouble();
                    }//j
                    FIVE_FOLD_IDEAL[i]  =sc5.nextDouble();
                }//i
            }//while
//create neural network
        NeuralNetwork network5 = new NeuralNetwork(7, 3, 1);
        int epoch5 = 0;
        System.out.println("Testing DATA: 169-209, Training Data:1-168");
        System.out.println("Start MSE is: " + network5.calculateNetworkError(FIVE_FOLD_INPUT, FIVE_FOLD_IDEAL));
        do {
            ++epoch5;
            for (int i = 0; i < FIVE_FOLD_INPUT.length; ++i) {
                double[] inputs = FIVE_FOLD_INPUT[i];
                double target = FIVE_FOLD_IDEAL[i];
                network5.feedforward(inputs);
                network5.backpropagation(target);

                if (epoch5 % 1000 == 0) {
                    System.out.println("Iteration: "+ epoch5 +"Error MSE is: " + network5.calculateNetworkError(FOUR_FOLD_INPUT, FOUR_FOLD_IDEAL));
                }
            }
        } while (network5.calculateNetworkError(FIVE_FOLD_INPUT, FIVE_FOLD_IDEAL) > Constants.ERROR_THRESHOLD);
        for(int i = 168; i <209; i++){
            System.out.print((i+1)+ "Actual: "+(XOR_IDEAL[i])+" predicted ");
            network5.testNetwork(XOR_INPUT[i]);
        }
        /*
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //declare neural network
            NeuralNetwork network = new NeuralNetwork(7, 3, 1);
            int epoch = 0;
            System.out.println("Start MSE is: " + network.calculateNetworkError(XOR_INPUT, XOR_IDEAL));
            do {
                ++epoch;
                for (int i = 0; i < XOR_INPUT.length; ++i) {
                    double[] inputs = XOR_INPUT[i];
                    double target = XOR_IDEAL[i];
                    network.feedforward(inputs);
                    network.backpropagation(target);

                    if (epoch % 1000 == 0) {
                        System.out.println("Error MSE is: " + network.calculateNetworkError(XOR_INPUT, XOR_IDEAL));
                    }
                }
            } while (network.calculateNetworkError(XOR_INPUT, XOR_IDEAL) > 1e-4);
            for(int i = 0; i <XOR_INPUT.length; i++){
                System.out.print("Actual: "+(XOR_IDEAL[i])+" predicted ");
                network.testNetwork(XOR_INPUT[i]);
            }
*/
    }//main
}//class