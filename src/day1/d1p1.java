package day1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class d1p1 {

    public static void main(String[] args) throws IOException {
        int testInputIncreases = countIncreases("./src/day1/testInput.txt");
        int inputIncreases = countIncreases("./src/day1/input.txt");

        System.out.println(testInputIncreases);
        System.out.println(inputIncreases);
    }

    private static Integer countIncreases(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine = bufferedReader.readLine();
        int lastMeasurement = Integer.parseInt(currentLine);
        int numberOfIncreases = 0;
        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }
            numberOfIncreases = (Integer.parseInt(currentLine) > lastMeasurement) ? numberOfIncreases + 1 : numberOfIncreases;
            lastMeasurement = Integer.parseInt(currentLine);
        };

        bufferedReader.close();
        return(numberOfIncreases);
    }
    
  }