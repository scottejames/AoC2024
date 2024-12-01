package com.scottejames.aoc2024.util;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDay {
    private static String _input;

    public AbstractDay(int day) throws IOException {
        InputFetch i = new InputFetch("2024");
        _input = i.fetchPuzzleInput(day);
    }

    /**
     * Returns the solution to part 1 of the puzzle.
     * @return the solution to part 1 of the puzzle
     */
    public abstract String solvePart1();

    /**
     * Returns the solution to part 2 of the puzzle.
     * @return the solution to part 2 of the puzzle
     */
    public abstract String solvePart2();

    public static void Main(String [] args){

    }
    public String getInput(){
        return _input;
    }

    public List<String> getListString(){
        return getInput()
                .lines()
                .collect(Collectors.toList());
    }
}
