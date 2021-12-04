package day4;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class d4p1 {

    public static void main(String[] args) throws IOException {
        int testInputAnswer = winningBoardScore("./src/day4/testInput.txt");
        int inputAnswer = winningBoardScore("./src/day4/input.txt");

        System.out.println(testInputAnswer);
        System.out.println(inputAnswer);
    }

    private static Integer winningBoardScore(String input) throws IOException {
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

        for (String draw: draws) {
            for (int matrixId = 0; matrixId < matrices.size(); matrixId++) {
                boolean marked = markDraw(matrices.get(matrixId), markedMatrices.get(matrixId), draw);
                if (marked) {
                    if(checkBingoWon(markedMatrices.get(matrixId))) {
                        int totalUnmarked = matrixSum(matrices.get(matrixId)) - matrixSum(markedMatrices.get(matrixId));
                        return totalUnmarked * Integer.parseInt(draw);
                    }
                }
            }

        }


        return(0);
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
                if (matrix[col][row] == null) {
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