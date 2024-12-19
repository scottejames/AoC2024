package com.scottejames.aoc2024;

import com.scottejames.aoc2024.Day12.DayTwelve;
import com.scottejames.aoc2024.Day15.DayFifteen;
import com.scottejames.aoc2024.Day16.DaySixteen;
import com.scottejames.aoc2024.Day17.DaySeventeen;
import com.scottejames.aoc2024.Day18.DayEighteen;
import com.scottejames.aoc2024.Day19.DayNinteen;
import com.scottejames.aoc2024.day13.DayThirteen;
import com.scottejames.aoc2024.day14.DayForteen;
import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        AbstractDay day = new DaySeventeen();

        String solnOne = day.solvePart1();
        String solnTwo = day.solvePart2();

        System.out.println("Part 1: " +  solnOne);
        System.out.println("Part 2: " +  solnTwo);    }
}


/*
OO.#OOO
.O#OO#O
.OOO#OO
...#OO#
..#OO#.
.#.O#..
#.#OOOO

 */