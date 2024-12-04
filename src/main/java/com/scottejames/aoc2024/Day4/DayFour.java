package com.scottejames.aoc2024.Day4;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayFour extends AbstractDay {
    public List<String> sample;
    public Grid<Character> grid = new Grid<>();

    public DayFour() throws IOException {
        super(4);
        sample = List.of(
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX");
    }

    public void loadGrid() {
        List<String> data = getListString();
        List<String> volumeData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                grid.add(new Point(j, i), line.charAt(j));
            }
        }
//        grid.showGrid();
    }

    @Override
    public String solvePart1() {
        int count = 0;
        String xmas = "XMAS";
        loadGrid();
        Set<Point> points = grid.getAllPoints();
        Set<Point> allDirections = Point.getAllMovements();
        for (Point p: points) {
            for (Point direction : allDirections) {
                Point check = p;
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < xmas.length(); i++) {
                    if (grid.withinGrid(check)) {
                        sb.append(grid.get(check));
                    }
                    check = check.add(direction);
                }
                if (sb.toString().equals(xmas)) {
                    count++;
                }
            }
        }
        return "Day 4 Part 1 " + count;
    }

    @Override
    public String solvePart2() {
        Set<Point> points = grid.getAllPoints();
        int count = 0;
        for (Point p: points) {
            if (grid.get(p) == 'A') {
                Set<Character> allDirections = Set.of('M', 'S');
                Set<Character> a = new HashSet<>();
                Set<Character> b = new HashSet<>();

                a.add(grid.get(p.add(-1, -1)));
                a.add(grid.get(p.add(+1, +1)));

                b.add(grid.get(p.add(-1, +1)));
                b.add(grid.get(p.add(+1, -1)));

                if (a.equals(allDirections) && b.equals(allDirections)) {
                    count++;

                }
            }
        }

        return "Day 4 Part 2 " + count;
    }
}
