package com.scottejames.aoc2024.Day7;

public enum Operator {
    ADD,
    MULTIPLY;

   public static Operator from(char c){
        switch(c){
            case '+':
                return ADD;
            case '*':
                return MULTIPLY;
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
    }
    public int apply(int a, int b){
        switch(this){
            case ADD:
                return a + b;
            case MULTIPLY:
                return a * b;
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
    }
}

