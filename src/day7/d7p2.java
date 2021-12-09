package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class d7p2 {

    public static void main(String[] args) throws IOException {
        int testInputAnswer = puzzleAnswer("./src/day7/testInput.txt");
        int inputAnswer = puzzleAnswer("./src/day7/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
    }

    private static ArrayList<Integer> readInput(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine = bufferedReader.readLine();

        ArrayList<Integer> crabs = Arrays.stream(currentLine.split(","))
                .map(crab -> Integer.parseInt(crab))
                .collect(Collectors.toCollection(ArrayList::new));
        bufferedReader.close();
        return(crabs);
    }

    private static Integer sumBetween(int start, int end) {
        int n = Math.abs(end - start);
        return (n*(n+1)/2);
    }

    private static Integer getFuelConsumption(Map<Integer, Integer> crabDisposition, int optimalPosition) {
        int fuelConsumption = 0;
        for ( int position: crabDisposition.keySet()) {
            Integer numberOfCrabs = crabDisposition.get(position);
            Integer fuelPerCrab = sumBetween(position, optimalPosition);
            fuelConsumption += numberOfCrabs * fuelPerCrab;
        }
        return fuelConsumption;
    }

    private static int puzzleAnswer(String input) throws IOException {

        ArrayList<Integer> crabs = readInput(input);
        Map<Integer, Integer> crabDisposition = new HashMap<>();
        for (Integer crab: crabs) {
            if(crabDisposition.containsKey(crab)) {
                crabDisposition.replace(crab, crabDisposition.get(crab) + 1);
            } else {
                crabDisposition.put(crab, 1);
            }
        }
        double averagePosition = crabs.stream().mapToDouble(crab -> crab).average().getAsDouble();
        int startingPosition = (int) Math.round(averagePosition);
        int optimalPosition = startingPosition;
        int minFuelConsumption = getFuelConsumption(crabDisposition, optimalPosition);
        int fuelConsumption;
        for (int position = startingPosition + 1; position < crabs.size() ; position++) {
            fuelConsumption =  getFuelConsumption(crabDisposition, position);
            if(fuelConsumption < minFuelConsumption) {
                minFuelConsumption = fuelConsumption;
                optimalPosition = position;
            }
        }
        for (int position = startingPosition; position >= 0 ; position--) {
            fuelConsumption =  getFuelConsumption(crabDisposition, position);
            if(fuelConsumption < minFuelConsumption) {
                minFuelConsumption = fuelConsumption;
                optimalPosition = position;
            }
        }
        return minFuelConsumption;
    }

}