package com.scottejames.aoc2024.day14;

import com.scottejames.aoc2024.util.Point;

public class Robot{
    static int WIDTH = 101;
    static int HEIGHT = 103;
    public final Point position;
    public final Point velocity;

    public Robot(Point position, Point velocity) {
        this.position = position;
        this.velocity = velocity;
    }
    public void step( int steps) {

        int x = (position.x + (steps * velocity.x)) % WIDTH;
        if (x < 0) {
            x += WIDTH;
        }
        int y = (position.y + (steps * velocity.y)) % HEIGHT;
        if (y < 0) {
            y += HEIGHT;
        }
        position.x = x;
        position.y = y;
    }
    public int getQuadrant()
    {
        if (position.x > (WIDTH) / 2 && position.y > (HEIGHT) / 2) return 1;
        else if (position.x < (WIDTH) / 2 && position.y > (HEIGHT) / 2) return 2;
        else if (position.x < (WIDTH) / 2 && position.y < (HEIGHT) / 2) return 3;
        else if (position.x > (WIDTH) / 2 && position.y < (HEIGHT) / 2) return 4;

        return 0;

    }
}
