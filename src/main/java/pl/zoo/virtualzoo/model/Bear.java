package pl.zoo.virtualzoo.model;

public class Bear extends Omnivore {

    public Bear(String name) {
        super(name, 100);
    }

    @Override
    public void move() {
        randomMove();
        loseEnergy(12);
    }
}
