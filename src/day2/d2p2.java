package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class d2p2 {

    public static void main(String[] args) throws IOException {
        int testInputAnswer = countMovement("./src/day2/testInput.txt");
        int inputAnswer = countMovement("./src/day2/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
    }

    private static List<Integer> moveSubmarine(String input, Integer depth, Integer distance, Integer aim) {
        String[] command = input.split(" ");
        switch (command[0]) {
            case "forward":
                distance = distance + Integer.parseInt(command[1]);
                depth = depth + aim * Integer.parseInt(command[1]);
                break;
            case "down":
                aim = aim + Integer.parseInt(command[1]);
                break;
            case "up":
                aim = aim - Integer.parseInt(command[1]);
                break;
        }

        return List.of(depth, distance, aim);
    }

    private static Integer countMovement(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int depth = 0;
        int distance = 0;
        int aim = 0;

        String currentLine = bufferedReader.readLine();
        List<Integer> results = moveSubmarine(currentLine, depth, distance, aim);
        depth = results.get(0);
        distance = results.get(1);
        aim = results.get(2);

        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }
            results = moveSubmarine(currentLine, depth, distance, aim);
            depth = results.get(0);
            distance = results.get(1);
            aim = results.get(2);
        }

        bufferedReader.close();
        return(depth*distance);
    }

}