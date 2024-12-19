
package com.scottejames.aoc2024.Day15;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Direction;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.List;

public class DayFifteen extends AbstractDay {
    List<String> sample;
    Grid<Character> grid = new Grid<>();
    String instructions;
    Point current;
    public DayFifteen() throws IOException {
        super(15);
        sample = List.of(
                "########",
                "#..O.O.#",
                "##@.O..#",
                "#...O..#",
                "#.#.O..#",
                "#...O..#",
                "#......#",
                "########",
                "",
                "<^^>>>vv<v>>v<<");
        loadData();
        current = grid.locate('@');
        grid.showGrid();
    }

    public void loadData(){
        List<String> data = sample;
        // load data into grid until blank line

        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            if (line.length() == 0) break;
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                grid.add(new Point(j, i), c );
            }
        }
        instructions = data.get(data.size()-1);

    }
    @Override
    public String solvePart1() {
        for (int i = 0; i < instructions.length(); i++) {
            grid.showGrid();
            char c = instructions.charAt(i);
            Direction d = Direction.fromChar2(c);

            // Have a look at the next square but dont move.
            char next = grid.get(current.move(d));

            // depending on what is in the next square, move or wait
            switch (next) {
                case '.':
                    current = move(current, d);
                    System.out.println("Space so moving " + d + " to " + current);
                    break;
                case '#':
                    grid.add(current, '@');
                    System.out.println("No Space waiting");
                    break;
                case 'O':
                    current = moveBag(current, d);
                    System.out.println("Bag can i move it?");
                    break;
            }

        }

        grid.showGrid();
        return null;
    }

    private Point moveBag(Point current, Direction d) {
        Point p = current.move(d);
        int c = 0;
        while (grid.get(p) == 'O'){
            p = p.move(d);
            if (grid.get(p) == '#') {
                System.out.println("Can move bag " + c + " spaces hit a wall");
                return current;
            } else {
                System.out.println("Can move bag " + c + " spaces");
                Direction r = d.reverse();
                while (!p.equals(current)){

                    Point next = p.move(r);
                    char c1 = grid.get(p);
                    grid.add(p, '.');
                    grid.add(next, c1);
                    p = p.move(r);
                }
            }
            c++;
        }
        return current;
    }

    private Point move(Point current, Direction d) {
        char c = grid.get(current);
        grid.add(current, '.');
        Point p = current.move(d);
        grid.add(p, c);
        return p;
    }


    @Override
    public String solvePart2() {
        return null;
    }
}
