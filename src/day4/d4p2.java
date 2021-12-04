package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class d4p2 {

    public static void main(String[] args) throws IOException {
        ArrayList<List<Integer>> testInputAnswer = allScores("./src/day4/testInput.txt");
        ArrayList<List<Integer>> inputAnswer = allScores("./src/day4/input.txt");

        System.out.println(testInputAnswer.get(testInputAnswer.size() - 1).get(0));
        System.out.println(inputAnswer.get(inputAnswer.size() - 1).get(0));
    }

    private static ArrayList<List<Integer>> allScores(String input) throws IOException {

        ArrayList<List<Integer>> scores = new ArrayList<>();

        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine = bufferedReader.readLine();
        String[] draws = currentLine.split(",");

        ArrayList<Integer[][]> matrices = new ArrayList<>();

        while(true) {
            currentLine = bufferedReader.readLine();
            if(currentLine == null) {
                break;
            }
            Integer[][] matrix = new Integer[5][5];
            for (int row = 0; row < 5; row++) {
                currentLine =  bufferedReader.readLine();
                String[] rowList = currentLine.split(" ");
                rowList = Arrays.stream(rowList).filter(value -> !value.isEmpty()).toArray(String[]::new);
                for(int col = 0; col < 5; col++) {
                    matrix[row][col] = Integer.parseInt(rowList[col]);
                }
            }
            matrices.add(matrix);
        }

        bufferedReader.close();
        List<Integer[][]> markedMatrices = new ArrayList<>(matrices);
        for (int matrixId = 0; matrixId < markedMatrices.size(); matrixId++) {
            markedMatrices.set(matrixId, new Integer[5][5]);
        }
        ArrayList<Integer> bingoMatrices = new ArrayList<>();
        for (String draw: draws) {
            for (int matrixId = 0; matrixId < matrices.size(); matrixId++) {
                if(bingoMatrices.contains(matrixId)) {
                    continue;
                }
                boolean marked = markDraw(matrices.get(matrixId), markedMatrices.get(matrixId), draw);
                if (marked) {
                    if(checkBingoWon(markedMatrices.get(matrixId))) {
                        int totalUnmarked = matrixSum(matrices.get(matrixId)) - matrixSum(markedMatrices.get(matrixId));
                        int score = totalUnmarked * Integer.parseInt(draw);
                        scores.add(List.of(score, matrixId));
                        bingoMatrices.add(matrixId);
                    }
                }
            }

        }


        return(scores);
    }

    private static int matrixSum(Integer[][] matrix) {
        int sum = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if(matrix[row][col] != null) {
                    sum += matrix[row][col];
                }
            }
        }
        return sum;
    }

    private static boolean markDraw(Integer[][] matrix, Integer[][] markedMatrix, String draw){

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col].toString().equals(draw)) {
                    markedMatrix[row][col] = Integer.parseInt(draw);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkBingoWon(Integer[][] matrix) {
        for (int row = 0; row < 5; row++) { // check for row Bingo
            boolean rowBingo = true;
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col] == null) {
                    rowBingo = false;
                    break;
                }
            }
            if(rowBingo) {
                return true;
            }
        }
        for (int col = 0; col < 5; col++) { // check for column Bingo
            boolean colBingo = true;
            for (int row = 0; row < 5; row++) {
                if (matrix[row][col] == null) {
                    colBingo = false;
                    break;
                }
            }
            if(colBingo) {
                return true;
            }
        }
        return false;
    }
}