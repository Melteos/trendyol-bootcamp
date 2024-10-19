package com.trendyol.toyrobot.domain.directions;

public interface Mover {
    void move();
    Compass turnRight();
    Compass turnLeft();
}
