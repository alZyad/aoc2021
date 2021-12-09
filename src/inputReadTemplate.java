import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class inputReadTemplate {

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

        ArrayList<Integer> readInput = Arrays.stream(currentLine.split(","))
                .map(fish -> Integer.parseInt(fish))
                .collect(Collectors.toCollection(ArrayList::new));
        bufferedReader.close();
        return(readInput);
    }

    private static ArrayList<Integer> puzzleAnswer(String input) throws IOException {

        ArrayList<Integer> answer = readInput(input);

        return answer;
    }

}