package pl.zoo.virtualzoo.model;

/**
 * Klasa abstrakcyjna reprezentująca zwierzęta roślinożerne.
 * Nie atakują innych zwierząt.
 */
public abstract class Herbivore extends Animal {

    public Herbivore(String name, int energy) {
        super(name, energy);
    }
    /**
     * Pusta implementacja, roślinożercy nie jedzą innych zwierząt
     */
    @Override
    public void eat(Animal other) {
    }
}