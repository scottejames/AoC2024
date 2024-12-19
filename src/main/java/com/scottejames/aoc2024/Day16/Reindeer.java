package com.scottejames.aoc2024.Day16;

import com.scottejames.aoc2024.util.Direction;
import com.scottejames.aoc2024.util.Pair;
import com.scottejames.aoc2024.util.Point;

import javax.swing.text.Position;
import java.util.Objects;

public  class Reindeer {
    private final Point position;
    private final Direction direction;

    public Reindeer(final Point position, final Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reindeer reindeer = (Reindeer) o;
        return Objects.equals(position, reindeer.position) && direction == reindeer.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }
}