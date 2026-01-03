package pl.zoo.virtualzoo.model;

import java.util.Random;

public abstract class Animal {

    protected String name;
    protected int energy;
    protected boolean alive = true;
    protected int x;
    protected int y;

    protected static final Random random = new Random();

    public Animal(String name, int energy) {
        this.name = name;
        this.energy = energy;
        this.x = random.nextInt(10);
        this.y = random.nextInt(10);
    }

    public abstract void move();
    public abstract void eat(Animal other);

    protected void loseEnergy(int value) {
        energy -= value;
        if (energy <= 0) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void randomMove() {
        x = Math.max(0, Math.min(9, x + random.nextInt(3) - 1));
        y = Math.max(0, Math.min(9, y + random.nextInt(3) - 1));
    }
}
