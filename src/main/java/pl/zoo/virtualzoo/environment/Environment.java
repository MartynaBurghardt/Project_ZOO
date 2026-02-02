package pl.zoo.virtualzoo.environment;

import pl.zoo.virtualzoo.model.Animal;
import pl.zoo.virtualzoo.model.Herbivore;
import pl.zoo.virtualzoo.model.Omnivore;
import pl.zoo.virtualzoo.model.Plant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/**
 * Klasa zarządzająca stanem całego środowiska symulacji.
 * Przechowuje listy wszystkich obiektów i zawiera główną logikę tury
 */
public class Environment {

    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();
    private final int MAP_SIZE = 20;

    /**
     * Konstruktor środowiska.
     * Generuje 9 losowych przeszkód  na start mapy.
     */
    public Environment() {
        for (int i = 0; i < 9; i++) {
            obstacles.add(new Obstacle(MAP_SIZE));
        }
    }
    /**
     * Dodaje zwierzę do symulacji.
     * @param animal Obiekt zwierzęcia
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }
//poczekalnia na noworodki

    /**
     * Oblicza całą logikę pojedynczej tury.
     */
    public void simulateTurn() {
        List<Animal> newborns = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Plant newPlant = new Plant(MAP_SIZE);

            boolean onRock = false;
            for (Obstacle obs : obstacles) {
                if (obs.getX() == newPlant.getX() && obs.getY() == newPlant.getY()) {
                    onRock = true;
                    break;
                }
            }
            if (!onRock) {
                plants.add(newPlant);
            }


        }
//losowe rozmieszczenie zwierzat
        Collections.shuffle(animals);

        animals.stream()
                .filter(Animal::isAlive)
                .forEach(a -> a.move(animals, obstacles));

        //jedzenie roslin
        for (Animal animal : animals) {
            if (!animal.isAlive()) continue;

            if (animal instanceof Herbivore || animal instanceof Omnivore) {
                Iterator<Plant> plantIterator = plants.iterator();
                while (plantIterator.hasNext()) {
                    Plant p = plantIterator.next();
                    if (p.getX() == animal.getX() && p.getY() == animal.getY()) {
                        animal.eatPlant();
                        plantIterator.remove();
                        break;
                    }
                }
            }
        }
        //uzylam iteratora zeby bezpiecznie moc ususnac rosline w petli to bylby blad

        //polowanie kazdy z kazdym
        for (Animal predator : animals) {
            if (!predator.isAlive()) continue;

            for (Animal prey : animals) {
                if (predator == prey || !prey.isAlive()) continue;

                if (Math.abs(predator.getX() - prey.getX()) <= 1 && //zasieg
                        Math.abs(predator.getY() - prey.getY()) <= 1) {

                    predator.eat(prey);
                }
            }
        }
//krag zycia
        for (Animal parent1 : animals) {
            if (!parent1.isAlive() || parent1.getEnergy() < 40) continue;

            for (Animal parent2 : animals) {
                if (parent1 == parent2 || !parent2.isAlive()) continue;

                if (parent1.getClass().equals(parent2.getClass()) &&
                        Math.abs(parent1.getX() - parent2.getX()) <= 1 &&
                        Math.abs(parent1.getY() - parent2.getY()) <= 1) {

                    newborns.add(parent1.reproduce());
                    break;
                }
            }
        }

        animals.addAll(newborns);
        animals.removeIf(a -> !a.isAlive());
    }
//simulateEngine Hellocontroller
    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

}