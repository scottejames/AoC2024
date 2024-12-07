package com.scottejames.aoc2024.Day6;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Direction;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.*;

public class DaySix extends AbstractDay {
    private List<String> sample;
    private Grid<Character> grid;
    public DaySix() throws IOException {
        super(6);
        sample = List.of(
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#..^.....",
                "........#.",
                "#.........",
                "......#...");
        loadData();

    }
    public void loadData() {
        List<String> data = getListString();
        grid = new Grid<Character>();

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                grid.add(new Point(j, i), line.charAt(j));
            }
        }
    }

    Set<Point> walk(){

        Set<Point> path = new HashSet<>();

        Point p = grid.locate('^');
        Direction d = Direction.UP;
        int steps = 0;
        while (true){
            if (steps++ > grid.getHeight() * grid.getWidth()){
                return null;  // infinite loop
            }
            path.add(p);
            Point next = p.move(d);

            if (grid.withinGrid(next) == false){
                break;
            }
            if ((grid.get(next) == '#') ||(grid.get(next) == '0')){
                d = d.turnRight();
            } else {
                p = next;
            }
        }

        return path;
    }

    @Override
    public String solvePart1() {
        Set<Point> path = walk();
        return path!=null?path.size() + "" : "";

    }

    @Override
    public String solvePart2() {

        int objCount = 0;
        Set<Point> obj =  walk();

        for (Point p : obj){
            Character c = grid.get(p);

            if (c == '^'){
                continue;
            }
            grid.add(p,'0');
            Set<Point> path = walk();
            if (path==null){
                objCount++;
            }
            grid.add(p,c);
        }

        return objCount +  "";

    }
}
