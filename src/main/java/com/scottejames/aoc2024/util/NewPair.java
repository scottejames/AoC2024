package com.scottejames.aoc2024.util;

public class NewPair <L,R>{
    public L first;
    public R second;


    public NewPair(L first, R second) {
        this.first = first;
        this.second = second;
    }

    public String toString(){
        return "(" + first + "," + second + ")";
    }
}
