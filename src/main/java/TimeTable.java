public class TimeTable {
        private final long averageTime;
        private final long bestTime;
        private final long worstTime;

        public TimeTable(long averageTime, long bestTime, long worstTime) {
            this.averageTime = averageTime;
            this.bestTime = bestTime;
            this.worstTime = worstTime;
        }
        // Getters for the times
        public long getAverageTime() {
            return averageTime;
        }

        public long getBestTime() {
            return bestTime;
        }

        public long getWorstTime() {
            return worstTime;
        }

    @Override
    public String toString() {
        return "TimeTable{" +
                "averageTime=" + averageTime +
                ", bestTime=" + bestTime +
                ", worstTime=" + worstTime +
                '}';
    }
}
