package com.scottejames.aoc2024.Day12;

import com.scottejames.aoc2024.util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DayTwelveOLD extends AbstractDay {
    private List<String> sample;
    private Grid<Character> grid;
    private Grid<Character> complete;


    public DayTwelveOLD() throws IOException {
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

    private void loadData(){
        List<String> data = sample;
        grid = new Grid<Character>();
        complete = new Grid<Character>();

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c= line.charAt(j);
                grid.add(new Point(j, i), c);
            }
        }
        grid.showGrid();
    }

    public int flood(Point p, char c,List<Point> edges) {
//        System.out.println("Flood " + p + " " + c);
        int count = 1;


        if (!grid.withinGrid(p)) {
//            System.out.println("FOUND Edge " + p);
            edges.add(p);
            return 0;
        }
        if (complete.getOrDefault(p, '-') == '0'){
//            System.out.println("FOUND Edge " + p);
//            edges.add(p);
            return 0;
        }
        if (grid.get(p) != c) {
//            System.out.println("FOUND Edge " + p);
            edges.add(p);
            return 0;
        }

    //    System.out.println("Flood " + p + " " + c);
       complete.add(p,'0');
       Set<Point> allDirections = p.getCartNeighbours();
       for (Point neighbour : allDirections) {
           count += flood(neighbour,c,edges);

       }
       return count;
    }
    @Override
    public String solvePart1() {
        int count;
        for (Point p : grid.getAllPoints()) {
            List<Point> edges = new ArrayList<>();

//            Point p = new Point(2,1);
            count  = flood(p, grid.get(p),edges);
            if (count !=0) {
                System.out.println(grid.get(p) + " = " + count + " Edges " + edges.size());
//                complete.showGrid();

            }
            }

//        Grid<Character> showme = new Grid<>();
//        for (Point edge : edges){
//            showme.add(edge,'X');
//        }
//        showme.showGrid();
        return "" + 0;
    }

    @Override
    public String solvePart2() {
        return null;
    }
}
