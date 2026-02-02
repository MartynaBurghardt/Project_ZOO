package pl.zoo.virtualzoo.model;

import pl.zoo.virtualzoo.environment.Obstacle;

import java.util.List;
/**
 * Reprezentuje Niedźwiedzia. Niedźwiedź porusza się losowo, nie goni aktywnie ofiar.
 */
public class Bear extends Carnivore {

    public Bear(String name) {
        super(name, 90);
    }

    public Bear(String name, int energy) {
        super(name, energy);
    }
    /**
     * Niedźwiedź wykonuje tylko losowe ruchy, tracąc dużo energii
     */
    @Override
    public void move(List<Animal> otherAnimals, List<Obstacle> obstacles) {
        randomMove(obstacles);
        loseEnergy(5);
    }

    @Override
    public Animal reproduce() {
        int babyEnergy = this.energy / 2;
        this.energy = this.energy / 2;
        return new Bear("Mały Miś", babyEnergy);
    }
}