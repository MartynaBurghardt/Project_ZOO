package pl.zoo.virtualzoo.model;

public class Wolf extends Carnivore {

    public Wolf(String name) {
        super(name, 60);
    }

    @Override
    public void move() {
        randomMove();
        loseEnergy(8);
    }
}
