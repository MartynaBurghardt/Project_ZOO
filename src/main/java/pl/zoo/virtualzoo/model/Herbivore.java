package pl.zoo.virtualzoo.model;

public abstract class Herbivore extends Animal {

    public Herbivore(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void eat(Animal other) {
    }
}
