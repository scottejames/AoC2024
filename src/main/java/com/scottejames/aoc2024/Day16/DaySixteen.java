package com.scottejames.aoc2024.Day16;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Direction;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DaySixteen extends AbstractDay {

    List<String> sample;
    Grid<Character> grid = new Grid<>();
    Grid<Integer> distances = new Grid<>();

    public DaySixteen() throws IOException {
        super(16);
        sample = List.of(
                "###############",
                "#.......#....E#",
                "#.#.###.#.###.#",
                "#.....#.#...#.#",
                "#.###.#####.#.#",
                "#.#.#.......#.#",
                "#.#.#####.###.#",
                "#...........#.#",
                "###.#.#####.#.#",
                "#...#.....#.#.#",
                "#.#.#.###.#.#.#",
                "#.....#...#.#.#",
                "#.###.#.#.#.#.#",
                "#S..#.....#...#",
                "###############"
        );
        loadData();
    }

    void loadData(){
        List<String> data = sample;
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                grid.add(new Point(j, i), c);
            }
        }

    }



    @Override
    public String solvePart1() {
        Point start = grid.locate('S');
        Point end = grid.locate('E');

        final Reindeer initialReindeer = new Reindeer(start, Direction.RIGHT);
        distances.add(start,0);
        Stack<Reindeer> stack = new Stack<>();
        stack.push(initialReindeer);

        while (!stack.isEmpty()){
            final Reindeer current = stack.pop();

            Set<Point> neighbours = current.getPosition().getNeighbours();
            Direction direction = current.getDirection();
            int currentDistance = distances.get(current.getPosition());

            for (Direction neighbour : Direction.cardinalDirections()){
                Point next = current.getPosition().move(neighbour);

                if (grid.get(next) != '#'){
                    int deltaDistance = (direction == neighbour) ? 1 : 1001;
                    int newDistance = currentDistance + deltaDistance;
                    if (newDistance < distances.getOrDefault(next,Integer.MAX_VALUE)){
                        distances.add(next,newDistance);
                        stack.push(new Reindeer(next,neighbour));
                    }

                }

            }

        }
        int result = distances.get(end);
        distances.showGrid();
        return "" + result;
    }

    @Override
    public String solvePart2() {

        return null;
    }
}
