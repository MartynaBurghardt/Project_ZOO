package pl.zoo.virtualzoo.model;

import pl.zoo.virtualzoo.environment.Obstacle;

import java.util.Comparator;
import java.util.List;
/**
 * Reprezentuje Zebrę
 * Ucieka zarówno przed Mięsożercami, jak i Wszystkożercami.
 */
public class Zebra extends Herbivore {

    public Zebra(String name) {
        super(name, 50);
    }

    public Zebra(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void move(List<Animal> otherAnimals, List<Obstacle> obstacles) {
        Animal predator = otherAnimals.stream()
                .filter(a -> (a instanceof Carnivore || a instanceof Omnivore) && a.isAlive())
                .min(Comparator.comparingDouble(this::distanceTo))
                .orElse(null);

        if (predator != null && distanceTo(predator) < 4) {
            // Ucieczka w przeciwną stronę
            int escapeX = this.x + (this.x - predator.getX());
            int escapeY = this.y + (this.y - predator.getY());
            moveTowards(escapeX, escapeY, obstacles);
            loseEnergy(3);
        } else {
            randomMove(obstacles);
            loseEnergy(2);
        }
    }

    @Override
    public Animal reproduce() {
        int babyEnergy = this.energy / 2;
        this.energy = this.energy / 2;
        return new Zebra("Mała Zebra", babyEnergy);
    }
}