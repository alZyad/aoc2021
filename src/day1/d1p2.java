package day1;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;

public class d1p2 {

    public static void main(String[] args) throws IOException {
        int testInputIncreases = countIncreases("./src/day1/testInput.txt");
        int inputIncreases = countIncreases("./src/day1/input.txt");

        System.out.println(testInputIncreases);
        System.out.println(inputIncreases);
    }

    private static Integer countIncreases(String input) throws IOException {

        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Queue<Integer> trio = new LinkedList<Integer>();
        String currentLine = bufferedReader.readLine();
        int numberOfIncreases = 0;

        int newTotal = 0;
        int oldTotal = 0;

        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }

            trio.add(Integer.parseInt(currentLine));
            if(trio.size() > 3){
                trio.remove();
            }
            if(trio.size() == 3) {
                newTotal = trio.stream().reduce(0, (total, measurement) -> (total + measurement));
                numberOfIncreases = (newTotal > oldTotal) ? numberOfIncreases + 1 : numberOfIncreases;
            }
        
            oldTotal = newTotal;
        };

        bufferedReader.close();
        return(numberOfIncreases);
    }
    
  }