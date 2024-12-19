package com.scottejames.aoc2024.Day17;

import com.scottejames.aoc2024.util.AbstractDay;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySeventeen extends AbstractDay {
    public List<String> sample;

    private Set<Long> findCandidates( long start,  int position,  List<String> input) {
        final Set<Long> candidates = new HashSet<>();

        for (long a = start; a < start + 8; a++) {
            final Computer computer = new Computer(input);
            computer.setA(a);
            computer.run();
            final int[] program = computer.getProgram();
            final int[] output = computer.getOutput();
            boolean valid = true;
                for (int i = position; i > 0; i--) {
                    if (program[program.length - position] != output[output.length - position]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    candidates.add(position < program.length ? a << 3 : a);
                }

        }
        return candidates;
    }
    public  long findA( List<String> input) {
        Set<Long> candidates = new HashSet<>();
        candidates.add(0L);
        final Computer computer = new Computer(input);
        for (int i = 1; i <= computer.getProgram().length; i++) {
            final Set<Long> newCandidates = new HashSet<>();
            for (final long candidate : candidates) {
                newCandidates.addAll(findCandidates(candidate, i, input));
            }
            candidates = newCandidates;
        }
        return candidates.stream().mapToLong(l -> l).min().getAsLong();
    }
    public DaySeventeen() throws IOException {
        super(17);
        sample = List.of(
                "Register A: 2024",
        "Register B: 0",
        "Register C: 0",
        "",
        "Program: 0,3,5,4,3,0"
        );
    }
    @Override
    public String solvePart1() {
        Computer computer = new Computer(getListString());
        computer.run();
        return computer.getOutputString();
    }

    @Override
    public String solvePart2() {
        Long l = findA(getListString());

        return  "" + l;
    }
}
