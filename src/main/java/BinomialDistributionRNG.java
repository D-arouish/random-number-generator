import org.apache.commons.math3.distribution.BinomialDistribution;

import java.util.*;

public class BinomialDistributionRNG {


    private int[] means;
    private double[] variances;
    private int[] dataSize;

    private  final Map<String, LinkedList<Double>> generatedData = new HashMap<>();

    public BinomialDistributionRNG(){}

    public BinomialDistributionRNG(int[] means, double[] variances, int[] dataSize){
        this.means = means;
        this.variances = variances;
        this.dataSize = dataSize;
        this.generateData(means, variances, dataSize);
    }

    public int[] getMeans() {
        return means;
    }

    public double[] getVariances() {
        return variances;
    }

    public int[] getDataSize() {
        return dataSize;
    }







    //private Map<Integer, TimeTable> timeTables = new HashMap<>();

    private static LinkedList<Double> generateBinomialData(int dataSize,double p, int n){

        LinkedList<Double> data = new LinkedList<>();
        BinomialDistribution binomial = new BinomialDistribution(n , p);
        for (int i = 0; i < dataSize; i++){
            data.add(binomial.sample() + Math.random());
        }
        return data;
    }

    private void generateData(int[] means, double[] variances, int[] dataSize){
        for (int mean : means){
            for (double variance : variances){
                if (variance >= mean) {
                    throw new IllegalArgumentException("Variance should be less than the mean.");
                }
                int n = (int) Math.round(Math.pow(mean,2) / (mean - variance));
                double p = (mean - variance) / mean;
                for (int nk : dataSize){
                    String key = getKey(mean, variance, nk);
                    LinkedList<Double> data =  generateBinomialData(nk, p, n) ;
                    generatedData.put(key, data);
                }
            }
        }
    }


    public static String getKey(int mean, double variance, int dataSize) {
        return "mean_" + mean + "_variance_" + variance + "_datasize_" + dataSize;
    }



    public  LinkedList<Double> getData(int mean, double variance, int dataSize) {
        String key = getKey(mean, variance, dataSize);
        LinkedList<Double> data = generatedData.get(key);
        if (data == null) {
            throw new NoSuchElementException("Data not found for key: " + key);
        }
        return data;
    }





    public static LinkedList<Double> generateData(int dataSize){
        return generateRandomData(dataSize);
    }

    private static LinkedList<Double> generateRandomData(int dataSize) {
        Random random = new Random();
        int n = random.nextInt(11) + 10;
        double p = Math.random();
        return generateBinomialData(dataSize, p, n);
    }









}
