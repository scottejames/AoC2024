package com.scottejames.aoc2024.Day5;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Pair;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFive extends AbstractDay {
    public List<String> sample;
    List<Pair<Integer>> rules = new ArrayList<>();

    public DayFive() throws IOException {
        super(5);
        sample = List.of(
                "47|53",
                "97|13",
                "97|61",
                "97|47",
                "75|29",
                "61|13",
                "75|53",
                "29|13",
                "97|29",
                "53|29",
                "61|53",
                "97|53",
                "61|29",
                "47|13",
                "75|47",
                "97|75",
                "47|61",
                "75|61",
                "47|29",
                "75|13",
                "53|13",
                "",
                "75,47,61,53,29",
                "97,61,53,29,13",
                "75,29,13",
                "75,97,47,61,53",
                "61,13,29",
                "97,13,75,29,47");
    }

    public void loadData(){
        List <String> lines = getListString();
        int result1 = 0;
        int result2 = 0;

        for (String line: lines) {
            if (line.length() == 5) {
                String[] update = line.split("\\|");
                Integer lhs = Integer.parseInt(update[0]);
                Integer rhs = Integer.parseInt(update[1]);
                rules.add(new Pair<Integer>(lhs,rhs));
            }
        }
        for (String line: lines) {
            if (line.length() > 5) {
                String[] stringPair = line.split(",");
                List<Integer> numbers = new ArrayList<>();
                for (String s : stringPair) {
                    numbers.add(Integer.parseInt(s));
                }
                boolean isValid = true;
                for (int i = 0; i < numbers.size() - 1; i++) {
                    Pair<Integer> updatePair = new Pair<>(numbers.get(i), numbers.get(i + 1));
                    if (!rules.contains(updatePair)) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid==true) {
                    int size = numbers.size();
                    int result = numbers.get(size / 2);
                    System.out.println("Result: " + result);
                    result1 += result;
                }
                else {
                    List<Integer> ordered = sortPages(rules, numbers);
                    result2 += ordered.get(ordered.size()/2);
                }
            }
        }
        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);

    }

    @Override
    public String solvePart1() {
        loadData();
        return "Day 5 Part 1";
    }

    @Override
    public String solvePart2() {
        return "Day 5 Part 2";
    }

    private static List<Integer> sortPages(List<Pair<Integer>> rules, List<Integer> pagesList) {
        Integer[] pagesArray = pagesList.toArray(Integer[]::new);
        for (int i = 0; i < rules.size(); i++) {
            for (Pair<Integer> rule: rules) {
                int value0 = rule.first;
                int value1 = rule.second;
                if (Arrays.asList(pagesArray).contains(value0) && Arrays.asList(pagesArray).contains(value1)){
                    int index0 = Arrays.asList(pagesArray).indexOf(value0);
                    int index1 = Arrays.asList(pagesArray).indexOf(value1);
                    if (index0 > index1) {
                        pagesArray[index0] = value1;
                        pagesArray[index1] = value0;
                        break;
                    }
                }
            }
        }
        return Arrays.asList(pagesArray);
    }
}
