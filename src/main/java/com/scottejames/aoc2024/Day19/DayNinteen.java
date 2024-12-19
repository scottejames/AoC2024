package com.scottejames.aoc2024.Day19;

import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DayNinteen extends AbstractDay {
    List<String> sample = getListString();
//            List.of(
//            "r, wr, b, g, bwu, rb, gb, br",
//            "",
//            "brwrr",
//            "bggr",
//            "gbbr",
//            "rrbgbr",
//            "ubwu",
//            "bwurrg",
//            "brgr",
//            "bbrgwb");
    HashMap <String, Long> cache = new HashMap<>();
    List<String> towels;
    String [] colours;

    public DayNinteen() throws IOException {
        super(19);
        towels = new ArrayList<>();
        for (int i = 2; i < sample.size();i++){
            towels.add(sample.get(i));
        }
        colours = sample.getFirst().split(", ");
    }

    public boolean isPossible(String towel,String [] colours) {
        if (towel.isEmpty()) return true;
        boolean found = false;
        for (int i = 0; i < colours.length; i++) {
            if (towel.startsWith(colours[i])) {
                found = found || isPossible(towel.substring(colours[i].length()), colours);
            }

        }
        return found;
    }
    public long count(String towel,String [] colours){
        if (cache.containsKey(towel)) return cache.get(towel);
        if (towel.isEmpty()) return 1;
        else {
            long total = 0L;
            for (int i = 0; i < colours.length; i++){
                if (towel.startsWith(colours[i])){
                    total += count(towel.substring(colours[i].length()),colours);
                }
            }
            cache.put(towel,total);
            return total;
        }
    }
    @Override
    public String solvePart1() {
        long count = 0L;
//        System.out.println(isPossible("bbrgwb",colours));
        for (String towel : towels){
            if (isPossible(towel,colours)) {
                count++;
            }
        }
        return count + "";
    }

    @Override
    public String solvePart2() {
        long co = 0;
        for (String towel : towels){
            System.out.println(co);

            co += count(towel,colours);
        }
        return co + "";
    }
}
