package com.scottejames.aoc2024.Day11;

import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayEleven extends AbstractDay {
    private String sample = "125 17";
    private String input = "0 7 6618216 26481 885 42 202642 8791";

    Map<Long, Long> stones = new HashMap<>() ;
    Map<Long, Long> next = new HashMap<>();

    public DayEleven() throws IOException {
        super(11);
    }

    private void loadData(){
        String line = input;
        String[] parts = line.split("\\s+");
        for (String str : parts) {
            long l = Long.parseLong(str);
            stones.put(l, stones.getOrDefault(stones, 0l) + 1);
        }


    }

    public void tick(){
        next.clear();

        for (long i : stones.keySet()) {
            if (i == 0) {
                // First rule 1 becomes zero
                next.put(1l, next.getOrDefault(1l, 0l) + stones.get(i));
            } else {
                int digits = (int) (Math.log10(i) + 1);
                if (digits % 2 == 0) {
                    // Second rule split the number in half
                    long lhs = (long) (i / Math.pow(10, digits / 2));
                    long rhs = (long) (i % Math.pow(10, digits / 2));
                    next.put(lhs, next.getOrDefault(lhs, 0l) + stones.get(i));
                    next.put(rhs, next.getOrDefault(rhs, 0l) + stones.get(i));

                } else {
                    // Third rule mult by 2024
                    long num = 2024 * i;
                    next.put(num, next.getOrDefault(num, 0l) + stones.get(i));

                }
            }
        }
        stones = Map.copyOf(next);
    }

    public String solvePart1() {
        loadData();
        for (int i = 0; i < 25; i++) {
            tick();
        }
        long count = 0;
        for (long num : stones.keySet()) {
            count += stones.get(num);
        }
        return "Day 11 Part 1: " + count ;
    }

    public String solvePart2() {
        for (int i = 0; i < 50; i++) {
            tick();
        }
        long count = 0;
        for (long num : stones.keySet()) {
            count += stones.get(num);
        }
        return "Day 11 Part 2 : " + count;
    }


}
