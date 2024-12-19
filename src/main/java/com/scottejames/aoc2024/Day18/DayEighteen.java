package com.scottejames.aoc2024.Day18;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.*;

public class DayEighteen extends AbstractDay {

    private List<String> sample;
    private Grid<Character> grid = new Grid();
    private Grid<Integer> wtf = new Grid();
    private List<Point> points = new ArrayList<>();
    private List<String> data;
    public DayEighteen() throws IOException {
        super(18);
        sample = List.of(
                "5,4",
                "4,2",
                "4,5",
                "3,0",
                "2,1",
                "6,3",
                "2,4",
                "1,5",
                "0,6",
                "3,3",
                "2,6",
                "5,1",
                "1,2",
                "5,5",
                "2,5",
                "6,5",
                "1,4",
                "0,4",
                "6,4",
                "1,1",
                "6,1",
                "1,0",
                "0,5",
                "1,6",
                "2,0"
        );
        loadData();
    }

    private void loadData() {
        int length = 1024;
        data =getListString();
        int count = 0;
        for (String line : data) {
            count++; if (count > length) break;
            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Point p = new Point(x, y);
            points.add(p);
            grid.add(p, '#');
        }
    }

    public int steps(){
        int W = grid.getWidth();
        int H = grid.getHeight();
        Point start = new Point (0,0);
        Point end = new Point(W,H);

        PriorityQueue<State> queue= new PriorityQueue<>();
        HashSet<Point> visited = new HashSet<>();

        visited.add(start);
        queue.add(new State(start,0));
        while (!queue.isEmpty()){

            State curr = queue.poll();
            wtf.add(curr.p,curr.steps);
            if (curr.p.equals(end)){
                System.out.println("Found End");
                return curr.steps;
            }
            for (Point next : curr.p.getCartNeighbours()){
                if (!grid.withinGrid(next)) continue;

                if (grid.get(next) == null){
                    if (!visited.contains(next)){
                        visited.add(next);
                        queue.add(new State(next,curr.steps + 1));
                    }
                }
            }
        }
        return -1;
    }

    record State(Point p, int steps) implements Comparable<State> {
        @Override
        public int compareTo( State o) {
            return steps - o.steps;
        }
    }
    @Override
    public String solvePart1() {
        int steps = steps();
        return steps+"";
    }

    @Override
    public String solvePart2() {
        for (int i = 1023; i<= data.size(); i++ ) {
            String line = data.get(i);

            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Point p = new Point(x, y);
            points.add(p);
            grid.add(p, '#');
            int steps = steps();
            if (steps == -1) {
                return "No path found @" + i;

            }
        }
        return null;
    }

}
