package com.scottejames.aoc2024.Day7;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.ArrayHelper;
import com.scottejames.aoc2024.util.NewPair;
import com.scottejames.aoc2024.util.Pair;

import java.io.IOException;
import java.util.List;

public class DaySeven extends AbstractDay {
    private List<String> sample;

    public DaySeven() throws IOException {
        super(7);
        sample = List.of(
                "190: 10 19",
                "3267: 81 40 27",
                "83: 17 5",
                "156: 15 6",
                "7290: 6 8 6 15",
                "161011: 16 10 13",
                "192: 17 8 14",
                "21037: 9 7 18 13",
                "292: 11 6 16 20"
        );
        loadData();
    }

    public void loadData() {

    }

    public NewPair<Long,long[]> processRow(String row){
        String[] parts = row.split(":");
        long key = Long.parseLong(parts[0]);
        long[] values = ArrayHelper.convertToLongArray(parts[1].trim().split("\\s+"));
        return new NewPair<>(key,values);
    }
    @Override
    public String solvePart1() {
        List<String> data = getListString();
        long result = 0;

//        for (String row : data) {
//            NewPair<Long, long[]> pair = processRow(row);
//            long target = pair.first;
//            long[] calculation = pair.second;
//            List<String> permutations = PermutationGenerator.generatePermutations(calculation.length - 1);
//
//            for (String permutation : permutations) {
//                long calcResult = calculation[0];
//                for (int i = 1; i < calculation.length; i++) {
//                    if (permutation.charAt(i - 1) == 'a') {
//                        calcResult += calculation[i];
//                    } else if (permutation.charAt(i - 1) == 'm') {
//                        calcResult *= calculation[i];
//                    }
//                }
//                if (target == calcResult) {
//                    result += target;
//
//                    break;
//                }
//            }
//        }
        return result + "";
    }


    @Override
    public String solvePart2() {
            List<String> data = getListString();
            long result = 0;

            for (String row : data) {
                NewPair<Long, long[]> pair = processRow(row);
                long target = pair.first;
                long[] calculation = pair.second;
                List<String> permutations = PermutationGenerator.generatePermutations(calculation.length - 1);

                for (String permutation : permutations) {
                    long calcResult = calculation[0];
                    for (int i = 1; i < calculation.length; i++) {
                        if (permutation.charAt(i - 1) == 'a') {
                            calcResult += calculation[i];
                        } else if (permutation.charAt(i - 1) == 'm') {
                            calcResult *= calculation[i];
                        } else if(permutation.charAt(i - 1) == 'j') {
                            calcResult = Long.parseLong("" + calcResult + calculation[i]);
                        }
                    }
                    if (target == calcResult) {
                        result += target;

                        break;
                    }
                }
            }
            return result + "";

    }
}
