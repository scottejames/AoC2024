package com.scottejames.aoc2024.util;

import java.io.IOException;

public abstract class AbstractDay {
    private static String _input;

    public AbstractDay(int day) throws IOException {
        InputFetch i = new InputFetch();
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
}
