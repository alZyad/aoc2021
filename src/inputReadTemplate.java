import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class inputReadTemplate {

    public static void main(String[] args) throws IOException {
        int testInputAnswer = puzzleAnswer("./src/day/testInput.txt");
        int inputAnswer = puzzleAnswer("./src/day/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
    }

    private static Integer puzzleAnswer(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine = bufferedReader.readLine();

        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }
        };

        bufferedReader.close();
        return(null);
    }

}