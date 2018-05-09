package net.cubiness.datastructurestest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ExperimentUtils {
    
    public static final Logger log = Logger.getLogger(ExperimentUtils.class.getName());

    public static SimulationResults testAllLengths(Collection<Account> testStruct, int testNumber, int minLength, int lengthDiff, int maxLength) {
        SimulationResults results = new SimulationResults(testStruct.getClass().getName());
        IntStream.iterate(minLength, i -> i + lengthDiff).limit((maxLength - minLength) / lengthDiff).forEach((numElements) -> {
            testStruct.clear();
            results.addResult(runTest(testStruct, numElements));
        });
        return results;
    }
    
    public static SimulationResult runTest(Collection<Account> testStruct, int length) {
        log.info("Testing " + testStruct.getClass() + " with length " + length);
        Random rand = new Random(0);
        ArrayList<Account> dataToAdd = new ArrayList<>();
        IntStream.range(0, length).forEach((i) -> {
            dataToAdd.add(new Account(UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()));
        });
        Collections.shuffle(dataToAdd, rand);
        double addTime = 0d;
        for (Account value : dataToAdd) {
            long startTime = System.nanoTime();
            testStruct.add(value);
            long stopTime = System.nanoTime();
            addTime += (double) (stopTime - startTime);
        }
        addTime /= (double) length;
        double searchTime = 0d;
        for (int i = 0; i < 100; i++) {
            Account value = dataToAdd.get(rand.nextInt(length));
            searchTime += testSearch(testStruct, value);
        }
        searchTime /= 100;
        
        return new SimulationResult(length, addTime, searchTime);
    }
    
    public static double testSearch(Collection<Account> testStruct, Account value) {
        long startTime = System.nanoTime();
        testStruct.contains(value);
        long stopTime = System.nanoTime();
        return (double) (stopTime - startTime);
    }

}
