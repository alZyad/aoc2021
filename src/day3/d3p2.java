package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class d3p2 {

    public static void main(String[] args) throws IOException {
        int testInputAnswer = getRatings("./src/day3/testInput.txt");
        int inputAnswer = getRatings("./src/day3/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
    }

    private static int getRatings(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> startingWithZero = new ArrayList<>();
        ArrayList<String> startingWithOne = new ArrayList<>();

        String currentLine = bufferedReader.readLine();

        int bitLength = currentLine.length();
        if(currentLine.charAt(0) == '0') {
            startingWithZero.add(currentLine);
        } else {
            startingWithOne.add(currentLine);
        }

        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }
            if(currentLine.charAt(0) == '0') {
                startingWithZero.add(currentLine);
            } else {
                startingWithOne.add(currentLine);
            }
        }
        bufferedReader.close();

        int zeroLength = startingWithZero.size();
        int oneLength = startingWithOne.size();

        ArrayList<String> oxygenList;
        ArrayList<String> carbonList;

        if (oneLength>=zeroLength) {
            oxygenList = startingWithOne;
            carbonList = startingWithZero;
        } else {
            oxygenList = startingWithZero;
            carbonList = startingWithOne;
        }

        int bit = 1;
        while(oxygenList.size()!=1 || carbonList.size()!=1) {
            oxygenList = oxygenList.size() > 1 ? getRatingList(oxygenList, bit, "O") : oxygenList;
            carbonList = carbonList.size() > 1 ? getRatingList(carbonList, bit, "C") :  carbonList;
            bit += 1;
        }


        return(Integer.parseInt(oxygenList.get(0), 2) * Integer.parseInt(carbonList.get(0), 2));
    }

    private static ArrayList<String> getRatingList(ArrayList<String> ratingList, Integer bit, String rating) {
        int numberOfZeros = 0;
        for(String number: ratingList) {
            numberOfZeros = number.charAt(bit) == '0' ? numberOfZeros + 1 : numberOfZeros;
        }
        if (numberOfZeros == ratingList.size() | numberOfZeros == 0) {
            return ratingList;
        }

        char winnerBit = (numberOfZeros > ratingList.size()/2) ^ (rating == "C") ? '0' : '1';
        ratingList = ratingList.stream().filter(number -> number.charAt(bit)==winnerBit).collect(Collectors.toCollection(ArrayList::new));
        return ratingList;

    }

}
