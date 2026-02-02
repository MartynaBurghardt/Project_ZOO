package pl.zoo.virtualzoo.model;

import pl.zoo.virtualzoo.environment.Obstacle;

import java.util.Comparator;
import java.util.List;
/**
 * Reprezentuje Lwa
 */
public class Lion extends Carnivore {

    public Lion(String name) {
        super(name, 80); //super bo przekazuje do Carnivore a stamtad do Animal
    }

    public Lion(String name, int energy) {
        super(name, energy);
    }
    /**
     * Logika ruchu Lwa:
     *  Szuka najbliższego żywego roślinożercy.
     *  Jeśli jest blisko (< 5 pól), idzie w jego stronę
     * 3W przeciwnym razie porusza się losowo
     */
    @Override
    public void move(List<Animal> otherAnimals, List<Obstacle> obstacles) {
        Animal target = otherAnimals.stream()
                .filter(a -> a instanceof Herbivore && a.isAlive())
                .min(Comparator.comparingDouble(this::distanceTo))
                .orElse(null);

        if (target != null && distanceTo(target) < 5) {
            moveTowards(target.getX(), target.getY(), obstacles);
            loseEnergy(2);
        } else {
            randomMove(obstacles);
            loseEnergy(5);
        }
    }
    /**
     * Tworzy nowego Lwa, przekazując mu połowę energii rodzica.
     */
    @Override
    public Animal reproduce() {
        int babyEnergy = this.energy / 2;
        this.energy = this.energy / 2;
        return new Lion("Mały Simba", babyEnergy);
    }
}