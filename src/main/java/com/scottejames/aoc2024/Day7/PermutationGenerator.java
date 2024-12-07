package com.scottejames.aoc2024.Day7;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator {
    public static List<String> generatePermutations(int length) {
        List<String> permutations = new ArrayList<>();
        generatePermutationsRecursive("", length, permutations);
        return permutations;
    }

    private static void generatePermutationsRecursive(String current, int length, List<String> permutations) {
        if (current.length() == length) {
            permutations.add(current);
            return;
        }

        generatePermutationsRecursive(current + "a", length, permutations);
        generatePermutationsRecursive(current + "m", length, permutations);
        generatePermutationsRecursive(current + "j", length, permutations);

    }
    public static void main(String[] args) {
        int length = 5; // Adjust the length as needed
        List<String> permutations = generatePermutations(length);
        System.out.println(permutations);
    }
}
