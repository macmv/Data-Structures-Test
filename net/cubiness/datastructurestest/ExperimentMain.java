package net.cubiness.datastructurestest;

import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;

public class ExperimentMain {
    
    public static void main(String[] args) throws IOException {
        ExperimentUtils.testAllLengths(new HashSet<>(4000000), 1, 1000, 1000, 1000000).saveToFile(); 
//        ExperimentUtils.testAllLengths(new HashSet<>(), 1, 1000, 1000, 1000000).saveToFile();
        ExperimentUtils.testAllLengths(new TreeSet<>(), 1, 1000, 1000, 1000000).saveToFile();
//        ExperimentUtils.testAllLengths(new LinkedList<>(), 1, 1000, 1000, 1000000).saveToFile();
//        ExperimentUtils.testAllLengths(new HashSet<>(), 1, 1000, 1000, 2000).saveToFile();
    }

}
