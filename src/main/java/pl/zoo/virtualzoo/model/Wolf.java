package pl.zoo.virtualzoo.model;

import pl.zoo.virtualzoo.environment.Obstacle;

import java.util.Comparator;
import java.util.List;
/**
 * Reprezentuje Wilka o nieco mniejszym zasięgu niż Lew
 */
public class Wolf extends Carnivore {

    public Wolf(String name) {
        super(name, 60);
    }

    public Wolf(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void move(List<Animal> otherAnimals, List<Obstacle> obstacles) {
        Animal target = otherAnimals.stream()
                .filter(a -> a instanceof Herbivore && a.isAlive())
                .min(Comparator.comparingDouble(this::distanceTo))
                .orElse(null);

        if (target != null && distanceTo(target) < 4) {
            moveTowards(target.getX(), target.getY(),obstacles );
            loseEnergy(2);
        } else {
            randomMove(obstacles);
            loseEnergy(5);
        }
    }

    @Override
    public Animal reproduce() {
        int babyEnergy = this.energy / 2;
        this.energy = this.energy / 2;
        return new Wolf("Mały Wilk", babyEnergy);
    }
}