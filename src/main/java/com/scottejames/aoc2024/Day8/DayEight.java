package com.scottejames.aoc2024.Day8;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.*;

public class DayEight extends AbstractDay {
    List<String> sample;
    public DayEight() throws IOException {
        super(8);
        sample = List.of(
                "............",
                "........0...",
                ".....0......",
                ".......0....",
                "....0.......",
                "......A.....",
                "............",
                "............",
                "........A...",
                ".........A..",
                "............",
                "............");


    }

    @Override
    public String solvePart1() {
        Set<Character> nodeList = new HashSet<Character>();
        Set<Point> antiNodeList = new HashSet<>();

        List<String> data = getListString();
        Grid<Character> grid = new Grid<Character>();

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c= line.charAt(j);
                grid.add(new Point(j, i), c);
                if (c!= '.'){
                    nodeList.add(c);
                }
            }
        }
        for (Character c : nodeList) {
            List<Point> nodes = grid.getAllPointsMatching(c);
            for (Point x : nodes) {
                for (Point y : nodes) {
                    if (x.equals(y)) continue;
                    Point d = x.delta(y);
                    antiNodeList.add(y.subtract(d));
                    antiNodeList.add(x.add(d));

                }
            }

        }
        for (Point p: antiNodeList){
            grid.boundedAdd(p,'#');
        }
        return "" + grid.getAllPointsMatching('#').size();
    }

    @Override
    public String solvePart2() {
        Set<Character> nodeList = new HashSet<Character>();
        Set<Point> antiNodeList = new HashSet<>();

        List<String> data = getListString();  // CHANGE ME
        Grid<Character> grid = new Grid<Character>();

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c= line.charAt(j);
                grid.add(new Point(j, i), c);
                if (c!= '.'){
                    nodeList.add(c);
                }
            }
        }
        for (Character c : nodeList) {
            List<Point> nodes = grid.getAllPointsMatching(c);
            for (Point x : nodes) {
                for (Point y : nodes) {
                    if (x.equals(y)) continue;
                    Point d = x.delta(y);
                    Point i = y.subtract(d);
                    Point j = x.add(d);

                    while (grid.withinGrid(i)) {
                        antiNodeList.add(i);
                        i = i.subtract(d);
                    }
                    while (grid.withinGrid(j)) {
                        antiNodeList.add(j);
                        j = j.add(d);
                    }


                }
            }

        }
        for (Point p: antiNodeList){
            grid.boundedAdd(p,'#');
        }
        int count = grid.getAllPoints().size() - grid.getAllPointsMatching('.').size();
        return "" + count;

    }

}
