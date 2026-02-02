package pl.zoo.virtualzoo.model;
/**
 * Klasa abstrakcyjna reprezentująca zwierzęta wszystkożerne.
 */
public abstract class Omnivore extends Animal {

    public Omnivore(String name, int energy) {
        super(name, energy);
    }
    /**
     * Wszystkożerca może zjeść roślinożercę.
     */
    @Override
    public void eat(Animal other) {
        if (other instanceof Herbivore && other.isAlive()) {
            other.alive = false;
            this.energy += 15;
        }
    }
}