package pl.zoo.virtualzoo.model;

public abstract class Carnivore extends Animal {

    public Carnivore(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void eat(Animal other) {
        if (other instanceof Herbivore && other.isAlive()) {
            other.alive = false;
            energy += 20;
        }
    }
}
