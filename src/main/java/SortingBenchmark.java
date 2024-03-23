import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SortingBenchmark {

    public static final int WARMUP_ROUNDS = 15;

    public static Map<Integer, TimeTable> sortingBenchmark(BinomialDistributionRNG binomialDistributionRNG){
        Map<Integer, TimeTable> timeTables = new HashMap<>();
        int sortingRounds = binomialDistributionRNG.getMeans().length * binomialDistributionRNG.getVariances().length;
        for (int nk : binomialDistributionRNG.getDataSize()){
            warmupJVM(nk);
            long bestTime = Long.MAX_VALUE;
            long worstTime = Long.MIN_VALUE;
            long totalTime = 0;
            for (int m : binomialDistributionRNG.getMeans()){
                for (double v : binomialDistributionRNG.getVariances()){
                    LinkedList<Double> data = binomialDistributionRNG.getData(m,v,nk);
                    long elapsedTime = getElapsedTime(data);

                    bestTime = Math.min(bestTime, elapsedTime);
                    worstTime = Math.max(worstTime, elapsedTime);

                    totalTime += elapsedTime;
                }
            }
            long averageTime = totalTime / sortingRounds;
            // Store the times in a TimeTable object
            TimeTable table = new TimeTable(averageTime, bestTime, worstTime);

            // Store the table in the map with the dataSize as key
            timeTables.put(nk, table);
        }
        return timeTables;
    }

    private static long getElapsedTime(LinkedList<Double> data) {
        long startTime = System.nanoTime();
        InsertionSort.sort(data);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }


    private static void warmupJVM(int dataSize) {
        for (int i = 0; i< WARMUP_ROUNDS; i++){
            LinkedList<Double> data = BinomialDistributionRNG.generateData(dataSize);
            InsertionSort.sort(data);
        }
    }




}
