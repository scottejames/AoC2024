package com.scottejames.aoc2024.Day3;

import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree extends AbstractDay {
    private String sample;
    private static final Pattern MULT_PATTERN = Pattern.compile("mul[(]([0-9]+),([0-9]+)[)]");
    private static final Pattern MULT_PATTERN2 = Pattern.compile("mul[(]([0-9]+),([0-9]+)[)]|do(n't)?[(][)]");

    public DayThree() throws IOException {
        super(3);
        sample = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";


    }

    public int extractCalc(String input){
        int result = 0;
        final Matcher matcher = MULT_PATTERN.matcher(input);
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            result += x * y;
        }
        return result;
    }

    public int extractCalc2(String input){
        int result = 0;
        final Matcher matcher = MULT_PATTERN2.matcher(input);
        boolean doIt = true;
        while (matcher.find()){
            switch (matcher.group(0)) {
                case "do()" -> doIt= true;
                case "don't()" -> doIt = false;
                default -> {
                    if (doIt) {
                        int x = Integer.parseInt(matcher.group(1));
                        int y = Integer.parseInt(matcher.group(2));
                        result += x * y;
                    }
                }

            }
        }
        return result;
    }
    @Override
    public String solvePart1() {
        int result = extractCalc(getInput());
        return "" + result;
    }

    @Override
    public String solvePart2() {
        int result = extractCalc2(getInput());
        return "" + result;
    }
}
