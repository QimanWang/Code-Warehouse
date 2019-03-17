

public class ActivationFunction {

    public static double sigmoidActivationFunction(double x){
        return 1 / (1+Math.exp(-x));
    }

    // y = sigmoidActivationFunction(x)
    public static double dSigmoidActivationFunction(double y){
        return y*(1-y);
    }

    public static double tanhActivationFunction(double x){
        return Math.tanh(x);

    }

    // y = tanhActivationFunction(x)
    public static double dTanhActivationFunction(double y){
        return 1-Math.pow(y, 2);
    }
}
