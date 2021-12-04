package day3;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class d3p1 {

    public static void main(String[] args) throws IOException {
        int testInputAnswer = getPower("./src/day3/testInput.txt");
        int inputAnswer = getPower("./src/day3/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
    }

    private static int getPower(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine = bufferedReader.readLine();


        String gammaRate = "";
        String epsilonRate = "";

        int reportLength = 1;
        int bitLength = currentLine.length();
        Integer[] zeroOccurrences = new Integer[bitLength];
        Arrays.fill(zeroOccurrences, 0);

        for (int bit = 0; bit < bitLength; bit++) {
            zeroOccurrences[bit] = (currentLine.charAt(bit) == '0') ? zeroOccurrences[bit] + 1 : zeroOccurrences[bit];
        }

        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }
            for (int bit = 0; bit < bitLength; bit++) {
                zeroOccurrences[bit] = (currentLine.charAt(bit) == '0') ? zeroOccurrences[bit] + 1 : zeroOccurrences[bit];
            }
            reportLength = reportLength + 1;
        }

        bufferedReader.close();

        for (int bit = 0; bit < bitLength; bit++) {
            if(zeroOccurrences[bit] > reportLength/2 ) {
                gammaRate =  gammaRate.concat("0");
                epsilonRate = epsilonRate.concat("1");
            } else {
                gammaRate = gammaRate.concat("1");
                epsilonRate = epsilonRate.concat("0");
            }
        }

        return(Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2));
    }

}
