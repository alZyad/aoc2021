package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class d6p2 {

    public static void main(String[] args) throws IOException {
        long testInputAnswer = puzzleAnswer("./src/day6/testInput.txt");
        long inputAnswer = puzzleAnswer("./src/day6/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
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
    private static Long countChildren(Integer fishTimer, Integer days) {
        int remainingDays = days - fishTimer - 1;
        if (remainingDays < 0) {
            return Long.valueOf(0);
        }
        int children = (int) (Math.floor(remainingDays) / 7) + 1;
        long total = Long.valueOf(children);
        for (var child=0; child < children; child++) {
            total = total + countChildren(8, remainingDays);
            remainingDays = remainingDays - 7;
        }
        return total;
    }
    private static long puzzleAnswer(String input) throws IOException {
        final int totalDays = 256;
        ArrayList<Integer> lanternFishes = readInput(input);
        long schoolSize = lanternFishes.size();
        List<List<Long>> childrenMemo = new ArrayList<>();
        
        for (Integer lanternFishTimer: lanternFishes) {
            long children;
            if(childrenMemo.stream().anyMatch(fish -> (fish.get(0) == Long.valueOf(lanternFishTimer)))) {
                children = childrenMemo.stream().filter(fish -> (fish.get(0) == Long.valueOf(lanternFishTimer))).findAny().get().get(1);
                schoolSize += children;
            } else {
                children = countChildren(lanternFishTimer, totalDays);
                childrenMemo.add(Arrays.asList(Long.valueOf(lanternFishTimer), children));
                schoolSize += children;
            }
        }


        return schoolSize;
    }

}