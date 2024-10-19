package com.trendyol.toyrobot.domain.directions;

public class North extends Compass {

    public North() {
        this.x = 0;
        this.y = 0;
        this.name = "NORTH";
    }

    public North(Compass compass) {
        this.x = compass.x;
        this.y = compass.y;
        this.name = "NORTH";
    }

    public North(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = "NORTH";
    }

    @Override
    public void move() {
        this.y = this.y + 1;
    }

    @Override
    public Compass turnRight() {
        this.name = "EAST";
        return new East(this);
    }

    @Override
    public Compass turnLeft() {
        this.name = "WEST";
        return new West(this);
    }
}
