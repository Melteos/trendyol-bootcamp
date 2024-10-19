package com.trendyol.toyrobot.domain.directions;

public class East extends Compass {

    public East(Compass compass) {
        this.x = compass.x;
        this.y = compass.y;
        this.name = "EAST";
    }

    public East(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = "EAST";
    }

    @Override
    public void move() {
        this.x = this.x + 1;
    }

    @Override
    public Compass turnRight() {
        this.name = "SOUTH";
        return new South(this);
    }

    @Override
    public Compass turnLeft() {
        this.name = "NORTH";
        return new North(this);
    }
}
