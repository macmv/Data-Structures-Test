package net.cubiness.datastructurestest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        Random rand = new Random(0);
        final PrintStream fileOutput = new PrintStream(new File("/home/macmv/Desktop/Data Structures Test/data/tree set.csv"));
        IntStream.iterate(1000, i -> i + 1000).limit(1000).forEach((numElements) -> {
            ArrayList<Integer> dataToAdd = new ArrayList<>();
            IntStream.range(0, numElements).forEach((i) -> {
                dataToAdd.add(i);
            });
            Collections.shuffle(dataToAdd, rand);
            
            Collection<Integer> testData = new TreeSet<>();
            long startTime = System.nanoTime();
            dataToAdd.forEach((value) -> {
                testData.add(value);
            });
            long stopTime = System.nanoTime();
            fileOutput.print("" + numElements + "," + ((double) (stopTime - startTime) / (double) numElements) + ",");
//            System.out.println("Add: Test with " + numElements + " elements. Time: " + ((double) (stopTime - startTime) / (double) numElements));
            
            ArrayList<Integer> randomNumbers = new ArrayList<>();
            IntStream.range(0, 10).forEach((i) -> {
                dataToAdd.add(rand.nextInt(numElements));
            });
            startTime = System.nanoTime();
            randomNumbers.forEach((value) -> {
                testData.contains(value);
            });
            stopTime = System.nanoTime();
//            System.out.println("Contains: Test with "
//                                + numElements
//                                + " elements. Time: "
//                                + ((double) (stopTime - startTime) / 10d));
            fileOutput.println("" + ((double) (stopTime - startTime) / 10d));
        });
        fileOutput.close();
    }

}
