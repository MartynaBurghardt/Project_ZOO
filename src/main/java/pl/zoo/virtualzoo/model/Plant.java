package pl.zoo.virtualzoo.model;

import java.util.Random;

public class Plant {
    private int x;
    private int y;

    public Plant(int mapSize) {
        Random random = new Random();
        this.x = random.nextInt(mapSize);
        this.y = random.nextInt(mapSize);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}