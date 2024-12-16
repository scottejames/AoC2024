package com.scottejames.aoc2024.day14;

import com.scottejames.aoc2024.util.AbstractDay;
import com.scottejames.aoc2024.util.Grid;
import com.scottejames.aoc2024.util.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DayForteen extends AbstractDay {
//    public final int WIDTH = 11;
//    private final int HEIGHT = 7;

    List<String> sample;
//    Grid<Character> grid = new Grid<Character>(WIDTH,HEIGHT);
    List<Robot> population = new ArrayList<>();


    public DayForteen() throws IOException {
        super(14);
        sample = List.of(
                "p=0,4 v=3,-3",
                "p=6,3 v=-1,-3",
                "p=10,3 v=-1,2",
                "p=2,0 v=2,-1",
                "p=0,0 v=1,3",
                "p=3,0 v=-2,-2",
                "p=7,6 v=-1,-3",
                "p=3,0 v=-1,-2",
                "p=9,3 v=2,3",
                "p=7,3 v=-1,2",
                "p=2,4 v=2,-3",
                "p=9,5 v=-3,-3");

    }

    private void loadData(){
        List<String> data = getListString();
        for (String line : data){

            String[] parts = line.split(" ");
            String[] p = parts[0].split(",");
            String[] v = parts[1].split(",");
            Point pos = new Point(Integer.parseInt(p[0].substring(2)),Integer.parseInt(p[1]));
            Point vel = new Point(Integer.parseInt(v[0].substring(2)),Integer.parseInt(v[1]));
            population.add(new Robot(pos,vel));
        }
    }
    @Override
    public String solvePart1()  {
   //     loadData();
//        List<Robot> data = new ArrayList<>(population);
//        for (Robot r : data) {
//            r.step(100);
//        }
//
//
//        int[] counts = new int[5];
//        for (Robot r : data){
//            int q = r.getQuadrant();
//            counts[q]++;
//        }
//        System.out.println(counts);
//        int result = counts[1] * counts[2] * counts[3] * counts[4];
        return ""; //+ result;
    }

    @Override
    public String solvePart2() {
        loadData();
        DisplayState display = new DisplayState(101,103);
        for (int i = 0; i< 10000 ; i++){

            for (Robot r : population) {
                r.step(1);
            }
            try {
                display.drawBots(population,i +1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        return "";
    }


}
