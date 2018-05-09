package net.cubiness.datastructurestest;

public class SimulationResult {

    private final int length;
    private final double addTime;
    private final double searchTime;

    public SimulationResult(int length, double addTime, double searchTime) {
        this.length = length;
        this.addTime = addTime;
        this.searchTime = searchTime;
    }

    // pushed strait to file output
    public String getCSVFormat() {
        return length + "," + addTime + "," + searchTime;
    }

    // for testing only; logging and such
    @Override
    public String toString() {
        return "SimulationResult [addTime=" + addTime + ", searchTime=" + searchTime + "]";
    }

    public int getLength() {
        return length;
    }

}
