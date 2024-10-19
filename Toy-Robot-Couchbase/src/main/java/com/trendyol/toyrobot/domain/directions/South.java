package com.trendyol.toyrobot.domain.directions;

public class South extends Compass {

    public South(Compass compass) {
        this.x = compass.x;
        this.y = compass.y;
        this.name = "SOUTH";
    }

    public South(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = "SOUTH";
    }

    @Override
    public void move() {
        this.y = this.y - 1;
    }

    @Override
    public Compass turnRight() {
        this.name = "WEST";
        return new West(this);
    }

    @Override
    public Compass turnLeft() {
        this.name = "EAST";
        return new East(this);
    }
}
