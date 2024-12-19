package com.scottejames.aoc2024.Day17;

import java.util.List;

import java.util.Arrays;

public class Computer {

    private long a = 0;
    private long b = 0;
    private long c = 0;

    private final int[] program ;
    private int ptr = 0;
    private String output = new String();

    public Computer (final List<String> input){
        a = Long.parseLong(input.get(0).split(": ")[1]);
        b = Long.parseLong(input.get(1).split(": ")[1]);
        c = Long.parseLong(input.get(2).split(": ")[1]);
        program = Arrays.stream(input.get(4).split(": ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
        ptr = 0;
    }

    private long getValue(int oparand){
        return switch (oparand) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> a;
            case 5 -> b;
            case 6 -> c;
            case 7 -> throw new IllegalArgumentException(" NOPE 7 is not valid");
            default -> throw new IllegalArgumentException(" NOPE " + oparand + " is not valid");
        };
    }
    private void adv(int op){
        a = (long) (a / Math.pow(2, getValue(op)));
        ptr += 2;
    }
    private void bdv(int op){
        b = (long)  (a / Math.pow(2, getValue(op)));
        ptr += 2;
    }
    private void cdv(int op){
        c = (long)  (a / Math.pow(2, getValue(op)));
        ptr += 2;
    }

    private void bxl(int op){
        b = b ^ op;
        ptr += 2;
    }

    private void bst(int op){
        b = getValue(op) % 8;
        ptr += 2;
    }

    private void jnz(int op){
        ptr = a == 0? ptr+ 2: op;
    }
    private void bxc(){
        b = b ^ c;
        ptr += 2;
    }

    private void out(int op){
        String result = String.valueOf(getValue(op) % 8);
        if (!output.isEmpty()) {
            result = "," + result;
        }
        output += result;
        ptr += 2;

    }

    private void runInstruction(int opCode, int operand){
        switch (opCode){
            case 0 -> adv(operand);
            case 1 -> bxl(operand);
            case 2 -> bst(operand);
            case 3 -> jnz(operand);
            case 4 -> bxc();
            case 5 -> out(operand);
            case 6 -> bdv(operand);
            case 7 -> cdv(operand);
            default -> throw new IllegalArgumentException("Unknown opcode: " + opCode);
        }
    }

    public void run(){
        while (ptr < program.length){
            int opCode = program[ptr];
            int operand = program[ptr + 1];
            runInstruction(opCode, operand);
        }
    }
    public void setA(long a){
        this.a = a;
    }
    public void setB(long b){
        this.b = b;
    }
    public void setC(long c){
        this.c = c;
    }
    public int[] getOutput(){
        return Arrays.stream(output.split(",")).mapToInt(Integer::parseInt).toArray();
    }
    public String getOutputString(){
        return output;
    }

    public int[] getProgram(){
        return program;
    }
    public String toString(){

        return "a: " + a + " b: " + b + " c: " + c + " ptr: " + ptr;
    }
}
