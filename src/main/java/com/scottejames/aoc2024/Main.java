package com.scottejames.aoc2024;

import com.scottejames.aoc2024.Day2.DayTwo;
import com.scottejames.aoc2024.day1.DayOne;
import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AbstractDay day = new DayTwo();

        String solnOne = day.solvePart1();
        String solnTwo = day.solvePart2();

        System.out.println("Part 1: " +  solnOne);
        System.out.println("Part 2: " +  solnTwo);    }
}
