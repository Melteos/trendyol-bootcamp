package com.trendyol.toyrobot.domain;

import com.trendyol.toyrobot.domain.directions.Compass;
import com.trendyol.toyrobot.domain.directions.North;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Rover {

    private String id;
    private int x;
    private int y;
    private Compass compass;
    private List<Material> material;

    public Rover() {
        this.id = UUID.randomUUID().toString();
        this.x = 0;
        this.y = 0;
        this.compass = new North();
        this.material = new ArrayList<Material>();
    }

    public Rover(int x, int y, Compass compass) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.compass = compass;
        this.material = new ArrayList<Material>();
    }

    public Rover(Compass compass) {
        this.id = UUID.randomUUID().toString();
        this.x = compass.getX();
        this.y = compass.getY();
        this.compass = compass;
        this.material = new ArrayList<Material>();
    }

    public void move() {
        this.compass.move();
        this.setX(this.compass.getX());
        this.setY(this.compass.getY());
        this.material.add(new Material(this.compass.getName()));
    }

    public void turnLeft() {
        this.compass = this.compass.turnLeft();
        this.material.add(new Material(this.compass.getName()));
    }

    public void turnRight() {
        this.compass = this.compass.turnRight();
        this.material.add(new Material(this.compass.getName()));
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }

    public Compass getCompass() {
        return compass;
    }

    public void setCompass(Compass compass) {
        this.compass = compass;
    }
}
