package pl.zoo.virtualzoo.model;

/**
 * Klasa abstrakcyjna reprezentująca zwierzęta mięsożerne.
 * Dziedziczy po Animal i implementuje logikę jedzenia innych zwierząt.
 */
public abstract class Carnivore extends Animal {

    public Carnivore(String name, int energy) {
        super(name, energy);
    }
    /**
     * Implementacja jedzenia dla mięsożerców
     * Atakuje tylko roślinożerców
     * @param other Potencjalna ofiara
     */
    @Override
    public void eat(Animal other) {
        if (other instanceof Herbivore && other.isAlive()) {
            other.alive = false;
            this.energy += 20;
        }
    }
}