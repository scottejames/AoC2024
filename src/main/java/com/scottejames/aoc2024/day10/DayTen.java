package com.scottejames.aoc2024.day10;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import javax.swing.text.Position;
import java.io.IOException;
import java.util.*;

public class DayTen extends AbstractDay {
    List<String> sample;
    Grid<Integer> grid = new Grid<>();

    public DayTen() throws IOException {
        super(10);

        sample = List.of(
                "89010123" ,
                        "78121874",
                        "87430965",
                        "96549874",
                        "45678903",
                        "32019012",
                        "01329801",
                        "10456732");
        loadData();
    }

    public void loadData() {
        List<String> data = getListString();

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                grid.add(new Point(j, i), c - '0');
            }
        }
    }
    public Set<Point> walkTrail(Point pos)
    {

        int currentValue = grid.get(pos);
        Set<Point> path = new HashSet<>();
        Set<Point> nextPaths = getNextPath(pos);


        if (currentValue == 9) {
            path.add(pos);
            return path;
        }
        for (Point p : nextPaths) {
            path.addAll(walkTrail(p));
        }
        return path;
    }


    public int countPaths(Point pos)
    {
        int currentValue = grid.get(pos);
        Set<Point> nextPaths = getNextPath(pos);
        int count = 0;

        if (currentValue == 9) {
            return 1;
        }
        for (Point p : nextPaths) {
            count += countPaths(p);
        }
        return count;
    }
    public Set<Point> getNextPath(Point p){
        Set<Point> results = new HashSet<>();
        Set<Point> neighbours = p.getCartNeighbours();
        for (Point n : neighbours) {
            if (grid.get(p) +1 == grid.getOrDefault(n, -100)) {
                results.add(n);
            }
        }
        return results;
    }


    @Override
    public String solvePart1() {

        int result = 0;
        for (Point p:grid.getAllPointsMatching(0)){
            result += walkTrail(p).size();
        }
        return "Part 1 solution "+ result;
    }

    @Override
    public String solvePart2() {
        int result = 0;

        for (Point p:grid.getAllPointsMatching(0)) {
            int count = countPaths(p);
             result += count;
        }
        return "Part 2 solution " + result;
    }

}
