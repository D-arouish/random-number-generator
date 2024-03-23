import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class CSVGenerator {

    public static void generateCSV(BinomialDistributionRNG binomialDistributionRNG){

        for (int m : binomialDistributionRNG.getMeans()){
            for (double v : binomialDistributionRNG.getVariances()){
                for (int nk : binomialDistributionRNG.getDataSize()){
                    String fileName = "results_" + BinomialDistributionRNG.getKey(m,v,nk) + ".csv";
                    try (FileWriter writer = new FileWriter(fileName)){
                        writer.append("Algorithm: Insertion Sort\n");
                        writer.append("Distribution: Binomial Distribution\n");
                        writer.append("Parameters: Mean=" + m + "\t Variance=" + v + "\t DataSize=" + nk + "\n");
                        writer.append("SortingRounds: " + binomialDistributionRNG.getMeans().length * binomialDistributionRNG.getVariances().length + "\n");
                        writer.append("JVM Warmup Rounds: " + SortingBenchmark.WARMUP_ROUNDS + "\n");
                        writer.append("\n\n\n");

                        LinkedList<Double> data = binomialDistributionRNG.getData(m,v,nk);

                        for (double d: data){
                            writer.append(d + "\n");
                        }

                        System.out.println("CSV file generated successfully.");
                    } catch (IOException e) {
                        System.err.println("Error writing to CSV file: " + e.getMessage());
                    }



                }
            }
        }


    }
}
