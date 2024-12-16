package com.scottejames.aoc2024.day13;

import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayThirteen extends AbstractDay {
    record Game(int[] buttonA, int[] buttonB, int[] prize) { }

    private List<String> sample;
    List<Game> games = new ArrayList<>();

    public DayThirteen() throws IOException {
        super(13);
        sample = List.of(
                "Button A: X+94, Y+34",
                "Button B: X+22, Y+67",
                "Prize: X=8400, Y=5400",
                "",
                "Button A: X+26, Y+66",
                "Button B: X+67, Y+21",
                "Prize: X=12748, Y=12176",
                "",
                "Button A: X+17, Y+86",
                "Button B: X+84, Y+37",
                "Prize: X=7870, Y=6450",
                "",
                "Button A: X+69, Y+23",
                "Button B: X+27, Y+71",
                "Prize: X=18641, Y=10279"
        );

        loadData();
    }

    private void loadData(){
        List<String> data = getListString();
        int count = 0;

        while (count <= data.size()){
            String[] buttonA = data.get(count++).split(":")[1].trim().split(", ");
            String[] buttonB = data.get(count++).split(":")[1].trim().split(", ");
            String[]  prize = data.get(count++).split(":")[1].trim().split(", ");
            games.add(
                    new Game(
                            new int[]{Integer.parseInt(buttonA[0].split("\\+")[1]), Integer.parseInt(buttonA[1].split("\\+")[1])},
                            new int[]{Integer.parseInt(buttonB[0].split("\\+")[1]), Integer.parseInt(buttonB[1].split("\\+")[1])},
                            new int[]{Integer.parseInt(prize[0].split("=")[1]), Integer.parseInt(prize[1].split("=")[1])}
                )
            );
            count++; // swallow white space
        }
    }
    public static long[] solve(long x1, long y1, long a1,
                               long x2, long y2, long a2) {
        long det = x1 * y2 - y1 * x2;
        if (det == 0) {
            return new long[]{-1, -1};
        }

        long detX = a1 * y2 - y1 * a2;
        long detY = x1 * a2 - a1 * x2;

        if (detX % det != 0 || detY % det != 0) {
            return new long[]{-1, -1};
        }

        return new long[]{detX / det, detY / det};
    }
    @Override
    public String solvePart1() {
        long totalTokensA = 0;
        long totalTokensB = 0;

        for (var game : games) {
            long[] solutions = solve(game.buttonA[0], game.buttonB[0], game.prize[0],
                    game.buttonA[1], game.buttonB[1], game.prize[1]);
            if (solutions[0] != -1L && solutions[1] != -1L) {
                totalTokensA += solutions[0] * 3;
                totalTokensB += solutions[1];
            }
        }
        long result = totalTokensA + totalTokensB;
        return "" + result;
    }

    @Override
    public String solvePart2() {
        long totalTokensA = 0;
        long totalTokensB = 0;

        for (var game : games) {
            long[] solutions = solve(game.buttonA[0], game.buttonB[0], game.prize[0]  + 10000000000000L,
                    game.buttonA[1], game.buttonB[1], game.prize[1] + 10000000000000L);
            if (solutions[0] != -1L && solutions[1] != -1L) {
                totalTokensA += solutions[0] * 3;
                totalTokensB += solutions[1];
            }
        }
        long result = totalTokensA + totalTokensB;
        return "" + result;
    }
}
