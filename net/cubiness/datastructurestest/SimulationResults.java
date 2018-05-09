package net.cubiness.datastructurestest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.TreeMap;
import java.util.logging.Logger;

public class SimulationResults {
    
    public static final Logger log = Logger.getLogger(SimulationResults.class.getName());
    private TreeMap<Integer, SimulationResult> results = new TreeMap<>();
    private final String testStructure;
    
    public SimulationResults(String testStructure) {
        this.testStructure = testStructure;
    }
    
    public void addResult(SimulationResult result) {
        results.put(result.getLength(), result);
    }
    
    public String getCSV() {
        StringBuilder file = new StringBuilder();
        for(SimulationResult result : results.values()) {
            file.append(result.getCSVFormat());
            file.append("\n");
        }
        return file.toString();
    }
    public void saveToFile(String fileName) throws FileNotFoundException {
        log.info("Saving to file " + fileName);
        final PrintStream fileOutput = new PrintStream(new File(fileName));
        fileOutput.print(getCSV());
        fileOutput.close();
    }
    
    public void saveToFile() throws FileNotFoundException {
        log.info("Finding file to save to");
        File checkFile;
        int i = 0;
        do {
            checkFile = new File(generateFileName(i));
            i++;
        } while (checkFile.exists());
        saveToFile(generateFileName(i - 1));
    }
    
    private String generateFileName(int i) {
        return "data/" + testStructure + "_" + i + ".csv";
    }

}
