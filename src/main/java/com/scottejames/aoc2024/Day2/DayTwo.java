package com.scottejames.aoc2024.Day2;

import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayTwo extends AbstractDay {
    private List<String> sample;
    private List<List<Integer>> data;

    public DayTwo() throws IOException {
        super(2);
        sample = List.of(
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"
        );

    }

    public void loadData(){
        List<String> input = getListString();
        data = new ArrayList<>();

        for(String line: input){
            String[] parts = line.split("\\s+");
            List<Integer> row = new ArrayList<>();
            for(String part: parts){
                row.add(Integer.parseInt(part));
            }
            data.add(row);
        }
    }

    @Override
    public String solvePart1() {
        loadData();
        int safeReports = 0;

        for(List<Integer> row: data) {
             if (getSafeReports(row))
                 safeReports++;
        };
        return "" + safeReports;
    }



    @Override
    public String solvePart2() {
        int safeReports = 0;

        for(List<Integer> row: data) {
            if (getSafeReports(row))
                safeReports++;
            else {
                for (int i = 0; i < row.size(); i++) {
                    List<Integer> newRow = new ArrayList<>(row);
                    newRow.remove(i);
                    if (getSafeReports(newRow)) {
                        safeReports++;
                        break;
                    }
                }
            }
        }
        return "" + safeReports;
    }


    private static boolean getSafeReports(List<Integer> row){
        boolean safeReport = false;
        Set<Integer> upGradiant = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> downGradiant = new HashSet<>(Set.of(-1, -2, -3));

        for (int i = 1; i < row.size(); i++) {
            int diff = row.get(i) - row.get(i - 1);
            upGradiant.add(diff);
            downGradiant.add(diff);
        }
        if ((upGradiant.size() == 3) || (downGradiant.size() == 3)) {
            safeReport = true;
        }
        return safeReport;
    }

}
