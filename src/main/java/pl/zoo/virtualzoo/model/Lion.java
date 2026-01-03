package pl.zoo.virtualzoo.model;

public class Lion extends Carnivore {

    public Lion(String name) {
        super(name, 80);
    }

    @Override
    public void move() {
        randomMove();
        loseEnergy(10);
    }
}
