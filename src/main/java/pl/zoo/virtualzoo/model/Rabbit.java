package pl.zoo.virtualzoo.model;

public class Rabbit extends Herbivore {

    public Rabbit(String name) {
        super(name, 30);
    }

    @Override
    public void move() {
        randomMove();
        loseEnergy(3);
    }
}
