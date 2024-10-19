package com.trendyol.toyrobot.domain.directions;

public class West extends Compass {

    public West(Compass compass) {
        this.x = compass.x;
        this.y = compass.y;
        this.name = "WEST";
    }

    public West(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = "WEST";
    }

    @Override
    public void move() {
        this.x = this.x - 1;
    }

    @Override
    public Compass turnRight() {
        this.name = "NORTH";
        return new North(this);
    }

    @Override
    public Compass turnLeft() {
        this.name = "SOUTH";
        return new South(this);
    }
}
