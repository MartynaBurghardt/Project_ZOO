package pl.zoo.virtualzoo.model;

public class Zebra extends Herbivore {

    public Zebra(String name) {
        super(name, 50);
    }

    @Override
    public void move() {
        randomMove();
        loseEnergy(5);
    }
}
