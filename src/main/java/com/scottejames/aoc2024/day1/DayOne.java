package com.scottejames.aoc2024.day1;

import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayOne extends AbstractDay {
    private List<String> sample;
    private List<Integer> lhs = new ArrayList<>();
    private List<Integer> rhs = new ArrayList<>();

    public DayOne() throws IOException {
        super(1);
        sample = List.of(
                "3   4",
                "4   3",
                "2   5",
                "1   3",
                "3   9",
                "3   3");


    }

    private void loadData(){
        List<String> data = getListString();
       // List<String> data = sample;
        for (String line : data){
            String[] parts = line.split("\\s+");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            lhs.add(x);
            rhs.add(y);
        }

        // sort lhs and rhs
        lhs.sort(Integer::compareTo);
        rhs.sort(Integer::compareTo);

    }
    @Override
    public String solvePart1() {
        loadData();
        int difference = 0;
        int sumDifference = 0;
        for (int i = 0; i < lhs.size(); i++){
            difference = rhs.get(i) - lhs.get(i);
            difference = Math.abs(difference);
            sumDifference += difference;
        }

        return "" + sumDifference;
    }

    @Override
    public String solvePart2() {
        // Convert rhs into a hash map with frequency
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int key : rhs) {
            if (frequency.containsKey(key)) {
                frequency.put(key, frequency.get(key) + 1);
            } else {
                frequency.put(key, 1);
            }
        }
        int mult = 0;
        int multSum = 0;

        for (Integer key: lhs){
            mult = frequency.getOrDefault(key,0);
            if (mult!=0) mult = key * mult;

            multSum += mult;
        }
        return multSum + "";
    }
}
