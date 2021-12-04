package day2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class d2p1 {

    public static void main(String[] args) throws IOException {
        int testInputAnswer = countMovement("./src/day2/testInput.txt");
        int inputAnswer = countMovement("./src/day2/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
    }

    private static List<Integer> moveSubmarine(String input, Integer depth, Integer distance) {
        String[] command = input.split(" ");
        switch (command[0]) {
            case "forward":
                distance = distance + Integer.parseInt(command[1]);
                break;
            case "down":
                depth = depth + Integer.parseInt(command[1]);
                break;
            case "up":
                depth = depth - Integer.parseInt(command[1]);
                break;
        }

        return List.of(depth, distance);
    }

    private static Integer countMovement(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int depth = 0;
        int distance = 0;

        String currentLine = bufferedReader.readLine();
        List<Integer> results = moveSubmarine(currentLine, depth, distance);
        depth = results.get(0);
        distance = results.get(1);
        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }
            results = moveSubmarine(currentLine, depth, distance);
            depth = results.get(0);
            distance = results.get(1);
        }

        bufferedReader.close();
        return(depth*distance);
    }

}