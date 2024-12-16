package com.scottejames.aoc2024.Day12;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Direction;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.*;

import static com.scottejames.aoc2024.util.Direction.*;

public class DayTwelve extends AbstractDay {
    private List<String> sample;
    private Grid<Character> grid;

    public DayTwelve() throws IOException {
        super(12);
        sample = List.of(
//                "AAAA",
//                "BBCD",
//                "BBCC",
//                "EEEC");
                "RRRRIICCFF",
                "RRRRIICCCF",
                "VVRRRCCFFF",
                "VVRCCCJFFF",
                "VVVVCJJCFE",
                "VVIVCCJJEE",
                "VVIIICJJEE",
                "MIIIIIJJEE",
                "MIIISIJEEE",
                "MMMISSJEEE");
        loadData();
    }

    private void loadData() {
        List<String> data = sample;
        grid = new Grid<Character>();

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                grid.add(new Point(j, i), c);
            }
        }
        grid.showGrid();
    }

    public Set<Point> flood(char goal, Point start) {
        Queue<Point> queue = new LinkedList<Point>();

        Set<Point> visited = new HashSet<>();
        Set<Point> edges = new HashSet<>();

        int edgeCount = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Point p = queue.remove();
            if (!grid.withinGrid(p)) {
                continue;
            }
            if (visited.contains(p)) {
                continue;
            }
            if (grid.get(p) == goal) {
                Set<Point> nextPoints = p.getCartNeighboursPositive();
                queue.addAll(nextPoints);
                visited.add(p);
            }

        }

        return visited;
    }

    @Override
    public String solvePart1() {
        int result = 0;
        Set<Point> points = grid.getAllPoints();
        Set<Point> visited = new HashSet<>();
        for (Point p : points) {
            if (visited.contains(p)) {
                continue;
            }

            Set<Point> flood = flood(grid.get(p), p);
            int edge = 0;
            for (Point f : flood) {
                for (Point n : f.getCartNeighbours()) {
                    if (!flood.contains(n)) {
                        edge++;
                    }
                }
            }
            visited.addAll(flood);

            result += flood.size() * edge;
        }
        return "" + result;
    }

    @Override
    public String solvePart2() {
        int result = 0;
        int side = 0;
//        Set<Point> points = grid.getAllPoints();
//        Set<Point> visited = new HashSet<>();
//        for (Point p : points){
//            if (visited.contains(p)){
//                continue;
//            }
        Set<Point> flood = flood(grid.get(new Point(0, 0)), new Point(0, 0));
        Set<Point> edge = new HashSet<>();
        Point point = null;
        for (Point f : flood) {
            for (Point n : f.getCartNeighbours()) {
                if (!flood.contains(n)) {
                    edge.add(f);
                    point = n;
                }
            }
        }
        Grid g = new Grid();
        for (Point p : flood) {
            g.add(p, grid.get(p));
        }
        g.showGrid();
        g = new Grid();
        for (Point p : edge) {
            g.add(p, grid.get(p));
        }
        g.showGrid();

//            visited.addAll(flood);

//            System.out.println("Char " + grid.get(p) + " Size " + flood.size() + " Side " + side);
//            result += flood.size() * side;

        return "";//+ result;
    }
}
