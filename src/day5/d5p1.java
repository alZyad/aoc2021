package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class d5p1 {

    public static void main(String[] args) throws IOException {
        ArrayList<Point> testInputAnswer = getDangerousPoints("./src/day5/testInput.txt");
        ArrayList<Point> inputAnswer = getDangerousPoints("./src/day5/input.txt");

        System.out.println(testInputAnswer.size());
        System.out.println(inputAnswer.size());
    }

    public static class Point {
        int x;
        int y;

        public Point(int newX, int newY) {
            this.x = newX;
            this.y = newY;
        }

        public String toString() {
            return "[x: " + this.x + "; y: " + this.y+"]";
        }
    }

    private static boolean isInside(Point currentPoint, Point initialPoint, Point endPoint) {
        if (endPoint.x == initialPoint.x && currentPoint.x == endPoint.x) {
            return ((currentPoint.y >= endPoint.y && currentPoint.y <= initialPoint.y)
                    || (currentPoint.y >= initialPoint.y && currentPoint.y <= endPoint.y));
        } else if (endPoint.y == initialPoint.y && currentPoint.y == endPoint.y) {
            return ((currentPoint.x >= endPoint.x && currentPoint.x <= initialPoint.x)
                    || (currentPoint.x >= initialPoint.x && currentPoint.x <= endPoint.x));
        }
        return false;
    }

    private static ArrayList<Point> getDangerousPoints(String input) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int maxX = -1;
        int maxY = -1;

        String currentLine = bufferedReader.readLine();

        ArrayList<List<Point>> vents = new ArrayList<>();
        while(currentLine != null) {
            String[] line = currentLine.split((" -> "));
            String[] firstPoint = line[0].split(",");
            String[] secondPoint = line[1].split(",");
            Point initialPoint = new Point(Integer.parseInt(firstPoint[0]), Integer.parseInt(firstPoint[1]));
            maxX = initialPoint.x > maxX ? initialPoint.x : maxX;
            maxY = initialPoint.y > maxY ? initialPoint.y : maxY;
            Point endPoint = new Point(Integer.parseInt(secondPoint[0]), Integer.parseInt(secondPoint[1]));
            maxX = endPoint.x > maxX ? endPoint.x : maxX;
            maxY = endPoint.y > maxY ? endPoint.y : maxY;

            List<Point> points = List.of(initialPoint, endPoint);
            vents.add(points);
            currentLine = bufferedReader.readLine();
        };
        bufferedReader.close();

        Integer[][] map = new Integer[maxX + 1][maxY + 1];
        ArrayList<Point> dangerousPoints = new ArrayList<>();

        for (int x = 0; x < maxX + 1; x++) {
            for (int y = 0; y < maxY + 1; y++) {
                map[x][y] = 0;
                for (List<Point> vent: vents) {
                    Point initialPoint = vent.get(0);
                    Point endPoint = vent.get(1);
                    Point currentPoint = new Point(x,y);
                    if(map[x][y]<2 && isInside(currentPoint, initialPoint, endPoint)) {
                        map[x][y] = map[x][y] + 1;
                        if (map[x][y] > 1) {
                            dangerousPoints.add(currentPoint);
                        }
                    }
                }
            }
        }
        return(dangerousPoints);
    }

}