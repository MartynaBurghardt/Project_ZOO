package pl.zoo.virtualzoo.environment;

import pl.zoo.virtualzoo.model.Animal;
import pl.zoo.virtualzoo.model.Carnivore;
import pl.zoo.virtualzoo.model.Herbivore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Environment {

    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void simulateTurn() {
        Collections.shuffle(animals);

        animals.stream()
                .filter(Animal::isAlive)
                .forEach(Animal::move);

        for (Animal predator : animals) {
            if (!predator.isAlive()) continue;

            for (Animal prey : animals) {
                if (predator == prey || !prey.isAlive()) continue;

                if (Math.abs(predator.getX() - prey.getX()) <= 1 &&
                        Math.abs(predator.getY() - prey.getY()) <= 1) {

                    predator.eat(prey);
                    if (!prey.isAlive()) return;
                }
            }
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
