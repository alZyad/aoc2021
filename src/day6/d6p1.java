package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class d6p1 {

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> testInputAnswer = puzzleAnswer("./src/day6/testInput.txt");
        ArrayList<Integer> inputAnswer = puzzleAnswer("./src/day6/input.txt");

        System.out.println(testInputAnswer.size());
        System.out.println(inputAnswer.size());
    }

    private static ArrayList<Integer> readInput(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine = bufferedReader.readLine();

        ArrayList<Integer> lanternFishes = Arrays.stream(currentLine.split(","))
                                                    .map(fish -> Integer.parseInt(fish))
                                                    .collect(Collectors.toCollection(ArrayList::new));
        bufferedReader.close();
        return(lanternFishes);
    }

    private static ArrayList<Integer> puzzleAnswer(String input) throws IOException {

        ArrayList<Integer> lanternFishes = readInput(input);

        for (int day = 0; day < 80; day++) {
            int schoolSize = lanternFishes.size();
            for (int lanternFish = 0; lanternFish < schoolSize; lanternFish++) {
                Integer lanternFishTimer = lanternFishes.get(lanternFish);
                switch (lanternFishTimer) {
                    case 0:
                        lanternFishes.add(8);
                        lanternFishes.set(lanternFish, 6);
                        break;
                    default:
                        lanternFishes.set(lanternFish, lanternFishTimer - 1);
                }
            }
        }
        return lanternFishes;
    }

}