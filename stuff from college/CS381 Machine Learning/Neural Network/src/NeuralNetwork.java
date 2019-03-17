
import java.util.Random;

public class NeuralNetwork {

    private Random random;
    private int numInputNeurons;
    private int numHiddenNeurons;
    private int numOutputNeurons;
    private double[] inputActivations;
    private double[] hiddenActivations;
    private double[] outputActivations;
    private double[][] inputWeights;
    private double[][] outputWeights;
    private double[][] momentumInputChange;
    private double[][] momentumOutputChange;

    public NeuralNetwork(int numInputNeurons, int numHiddenNeurons, int numOutputNeurons){
        initializeVariables(numInputNeurons, numHiddenNeurons,numOutputNeurons);
        initializeWeights();
        initializeMomentumChange();
    }

    private void initializeMomentumChange() {
        this.momentumInputChange = Matrix.createMatrix(this.numInputNeurons, this.numHiddenNeurons);
        this.momentumOutputChange = Matrix.createMatrix(this.numHiddenNeurons, this.numOutputNeurons);
    }

    private void initializeWeights() {



        this.inputWeights = Matrix.createMatrix(this.numInputNeurons, this.numHiddenNeurons);
        this.outputWeights = Matrix.createMatrix(this.numHiddenNeurons, this.numOutputNeurons);

        // first row -> what are the edge weights pointing to the next layer given nodes from first neuron
        // second row -> what are the edge weights pointing to the next layer given nodes from second neuron
        for(int i=0;i<this.numInputNeurons;++i){
            for(int j=0;j<this.numHiddenNeurons;++j){
                this.inputWeights[i][j] = rand(-0.5, 0.5);
            }
        }

        for(int i=0;i<this.numHiddenNeurons;++i){
            for(int j=0;j<this.numOutputNeurons;++j){
                this.outputWeights[i][j] = rand(-0.5,0.5);
            }
        }
    }

    private void initializeVariables(int numInput,int numHidden, int numOutputNeurons) {
        this.random = new Random();
        this.numInputNeurons = numInput+1; // +1 because of the bias node
        this.numHiddenNeurons = numHidden+1; // +1 bias node
        this.numOutputNeurons = numOutputNeurons;
        this.inputActivations = new double[this.numInputNeurons];
        this.hiddenActivations = new double[this.numHiddenNeurons];
        this.outputActivations = new double[this.numOutputNeurons];
    }

    public double[] feedforward(double[] inputs) { // for example [0 0], [0,1] etc 1D arrays

        this.inputActivations[this.numInputNeurons-1] = 1; // set bias node activation to be 1
        this.hiddenActivations[this.numHiddenNeurons-1] = 1;  // set bias node activation to be 1

        for(int i=0;i<this.numInputNeurons-1;++i){ // -1 because the bias node does not get an input
            this.inputActivations[i] = inputs[i];
        }

        for(int i=0;i<this.numHiddenNeurons;++i){

            double sum = 0.0;

            for(int j=0;j<this.numInputNeurons;++j){
                sum = sum + this.inputActivations[j] * this.inputWeights[j][i];
                this.hiddenActivations[i] = ActivationFunction.sigmoidActivationFunction(sum);
            }
        }

        for(int i=0;i<this.numOutputNeurons;++i){
            double sum = 0.0;

            for(int j=0;j<this.numHiddenNeurons;++j){
                sum = sum + this.hiddenActivations[j] * this.outputWeights[j][i];
                this.outputActivations[i] = ActivationFunction.sigmoidActivationFunction(sum);
            }
        }

        return this.outputActivations;
    }

    public void backpropagation(double target) {

        double[] outputDeltas = new double[this.numOutputNeurons];
        double[] hiddenDeltas = new double[this.numHiddenNeurons];
        double error = 0.0;

        for(int i=0;i<this.numOutputNeurons;++i){
            error = target - this.outputActivations[i];
            outputDeltas[i] = ActivationFunction.dSigmoidActivationFunction(this.outputActivations[i]) * error;
        }

        for(int i=0;i<this.numHiddenNeurons;++i){
            error = 0.0;

            for(int j=0;j<this.numOutputNeurons;++j){
                error = error + outputDeltas[j] * this.outputWeights[i][j];
                hiddenDeltas[i] = ActivationFunction.dSigmoidActivationFunction( this.hiddenActivations[i] ) * error;
            }
        }

        // update the wights hidden
        for(int i=0;i<this.numHiddenNeurons;++i){
            for(int j=0;j<this.numOutputNeurons;++j){
                double weightChange = outputDeltas[j] * this.hiddenActivations[i];
                this.outputWeights[i][j] = this.outputWeights[i][j] + Constants.LEARING_RATE*weightChange + Constants.MOMENTUM * momentumOutputChange[i][j];
                this.momentumOutputChange[i][j] = weightChange;
            }
        }

        // update input weights
        for(int i=0;i<this.numInputNeurons;++i){
            for(int j=0;j<this.numHiddenNeurons;++j){
                double weightChange = hiddenDeltas[j]*this.inputActivations[i];
                this.inputWeights[i][j] = this.inputWeights[i][j] + Constants.LEARING_RATE*weightChange + Constants.MOMENTUM*this.momentumInputChange[i][j];
                this.momentumInputChange[i][j] = weightChange;
            }
        }
    }

    public double calculateNetworkError(double[][] ideal, double[] idealTargets){

        double error = 0.0;

        for(int i=0;i<ideal.length;++i){
            double actual = feedforward(ideal[i])[0];
            double ideal2 = idealTargets[i];

            error = error + Math.pow( ( ideal2 - actual ),2 );
        }

        double MSE = error / ideal.length;

        return MSE;
    }

    public void testNetwork(double[] pattern){
        System.out.println("Result is: " + this.feedforward(pattern)[0] );
    }

    public double rand(double lowerBound, double upperBound){
        return lowerBound + (upperBound-lowerBound)*random.nextDouble();
    }
}
