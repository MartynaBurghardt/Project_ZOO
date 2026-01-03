package pl.zoo.virtualzoo.model;

public abstract class Omnivore extends Animal {

    public Omnivore(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void eat(Animal other) {
        if (other instanceof Animal && other.isAlive()) {
            other.alive = false;
            energy += 15;
        }
    }
}
