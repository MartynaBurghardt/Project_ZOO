package pl.zoo.virtualzoo.model;

import pl.zoo.virtualzoo.environment.Obstacle;

import java.util.Comparator;
import java.util.List;
/**
 * Reprezentuje Królika
 */
public class Rabbit extends Herbivore {

    public Rabbit(String name) {
        super(name, 25);
    }

    public Rabbit(String name, int energy) {
        super(name, energy);
    }
    /**
     * Logika ruchu Królika:
     * Wykrywa najbliższego drapieżnika
     * Jeśli drapieżnik jest blisko (< 4 pola), oblicza wektor ucieczki w przeciwną stronę
     * Jeśli bezpiecznie, porusza się losowo
     */
    @Override
    public void move(List<Animal> otherAnimals, List<Obstacle> obstacles) {
        Animal predator = otherAnimals.stream()
                .filter(a -> a instanceof Carnivore && a.isAlive())
                .min(Comparator.comparingDouble(this::distanceTo))
                .orElse(null); //wykrycie zagrożenia

        if (predator != null && distanceTo(predator) < 4) { //wektor ucieczki ale inny niż drapieżnik
            int escapeX = this.x + (this.x - predator.getX());
            int escapeY = this.y + (this.y - predator.getY());
            moveTowards(escapeX, escapeY, obstacles);
            loseEnergy(2);
        } else {
            randomMove(obstacles);
            loseEnergy(1);
        }
    }

    @Override
    public Animal reproduce() {
        int babyEnergy = this.energy / 2;
        this.energy = this.energy / 2;
        return new Rabbit("Mały Królik", babyEnergy);
    }
}