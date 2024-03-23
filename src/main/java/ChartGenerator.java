import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class ChartGenerator {

    public static void displayChart(Map<Integer, TimeTable> data) {
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<Integer, TimeTable> entry : data.entrySet()) {
            Integer dataSize = entry.getKey();
            TimeTable times = entry.getValue();

            // Convert nanoseconds to milliseconds or seconds
            long bestTime = times.getBestTime();
            long averageTime = times.getAverageTime();
            long worstTime = times.getWorstTime();

            // Convert nanoseconds to milliseconds (divide by 1 million) or seconds (divide by 1 billion)
            double bestTimeMs = bestTime / 1_000_000.0; // nanoseconds to milliseconds
            double averageTimeMs = averageTime / 1_000_000.0; // nanoseconds to milliseconds
            double worstTimeMs = worstTime / 1_000_000.0; // nanoseconds to milliseconds

            // Add the data to the dataset
            dataset.addValue(bestTimeMs, "Best Time", dataSize);
            dataset.addValue(averageTimeMs, "Average Time", dataSize);
            dataset.addValue(worstTimeMs, "Worst Time", dataSize);
        }

        // Create the chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Sorting Execution Times", // Chart title
                "Data Size",               // Domain axis label
                "Time (ms)",               // Range axis label (changed to milliseconds)
                dataset,                   // Dataset
                PlotOrientation.VERTICAL,  // Orientation
                true,                      // Include legend
                true,                      // Tooltips
                false);                    // URLs

        // Adjust the range axis to display milliseconds or seconds
        NumberAxis rangeAxis = (NumberAxis) barChart.getCategoryPlot().getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false); // Ensure zero is not included in the range
        rangeAxis.setNumberFormatOverride(new java.text.DecimalFormat("#.##")); // Format axis labels
        // You can adjust axis settings further as needed

        // Display the chart
        JFrame frame = new JFrame();
        frame.setContentPane(new ChartPanel(barChart));
        frame.setTitle("Execution Time Benchmark");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}