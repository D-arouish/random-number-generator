import java.util.LinkedList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int[] means = {5, 8, 10, 12, 14, 17}; // Example means
        double[] variances = {0.65, 1.4, 2.5}; // Example variances
        int[] dataSize = {300, 700, 1000};
        int[] test = {4, 6, 8};
        BinomialDistributionRNG distributionRNG = new BinomialDistributionRNG(means,variances,dataSize);

//        //distributionRNG.generateData(means, variances,test);
//        for (int nk : test){
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            for(int m : means){
//                for (double v: variances){
//                    LinkedList<Double> data = distributionRNG.getData(m,v,nk);
//                    for (Double d : data){
//                        System.out.print(d + "       ");
//                    }
//                    System.out.println();
//                    System.out.println();
//                }
//                System.out.println();
//                System.out.println();
//                System.out.println();
//            }
//        }

        Map<Integer,TimeTable> result =  SortingBenchmark.sortingBenchmark(distributionRNG);

        System.out.println(result);

        CSVGenerator.generateCSV(distributionRNG);

        ChartGenerator.displayChart(result);

    }





}
