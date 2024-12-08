package com.scottejames.aoc2024;

import com.scottejames.aoc2024.Day3.DayThree;
import com.scottejames.aoc2024.Day4.DayFour;
import com.scottejames.aoc2024.Day5.DayFive;
import com.scottejames.aoc2024.Day6.DaySix;
import com.scottejames.aoc2024.Day7.DaySeven;
import com.scottejames.aoc2024.Day8.DayEight;
import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AbstractDay day = new DayEight();

        String solnOne = day.solvePart1();
        String solnTwo = day.solvePart2();

        System.out.println("Part 1: " +  solnOne);
        System.out.println("Part 2: " +  solnTwo);    }
}
