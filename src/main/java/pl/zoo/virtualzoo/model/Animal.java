package pl.zoo.virtualzoo.model;

import pl.zoo.virtualzoo.environment.Obstacle;

import java.util.List;
import java.util.Random;



/**
 * Abstrakcyjna klasa bazowa reprezentująca każde zwierzę w symulacji.
 * Definiuje wspólne cechy (energia, pozycja) oraz wymusza implementację
 * kluczowych zachowań  w klasach pochodnych.
 */
public abstract class Animal {

    protected String name;
    protected int energy;
    protected boolean alive = true;
    protected int x;
    protected int y;

    public static final int MAP_SIZE = 20;
    protected static final Random random = new Random();
    /**
     * Konstruktor inicjalizujący zwierzę.
     * Ustawia losową pozycję startową i podwaja energię początkową.
     *
     * @param name Imię zwierzęcia.
     * @param energy Bazowa energia startowa.
     */
    public Animal(String name, int energy) {
        this.name = name;
        this.energy = energy * 2;
        this.x = random.nextInt(MAP_SIZE);
        this.y = random.nextInt(MAP_SIZE);
    }
    /**
     * Abstrakcyjna metoda ruchu
     * @param otherAnimals Lista wszystkich innych zwierząt
     * @param obstacles Lista przeszkód terenowych
     */
    public abstract void move(List<Animal> otherAnimals, List<Obstacle> obstacles);

    /**
     * Abstrakcyjna metoda jedzenia innego zwierzęcia.
     * @param other Ofiara, którą to zwierzę próbuje zjeść.
     */
    public abstract void eat(Animal other);
    /**
     * Abstrakcyjna metoda rozmnażania.
     * @return Nowa instancja zwierzęcia (dziecko) tego samego gatunku.
     */
    public abstract Animal reproduce();
    /**
     * Logika jedzenia rośliny.
     */
    public void eatPlant() {
        this.energy += 20;
        if (this.energy > 150) {
            this.energy = 150;
        }
    }
    /**
     * Zmniejsza energię zwierzęcia.
     * Jeśli energia spadnie do zera, zwierzę zostaje oznaczone jako martwe.
     * @param value Ilość energii do odjęcia.
     */
    protected void loseEnergy(int value) {
        energy -= value;
        if (energy <= 0) {
            alive = false;
        }
    }

    /**
     * Oblicza odległość euklidesową do innego zwierzęcia
     * Wykorzystuje tw. Pitagorasa
     * @param other Inne zwierzę
     * @return Odległość w linii prostej
     */
    public double distanceTo(Animal other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Sprawdza, czy dane pole (targetX, targetY) jest wolne od przeszkód.
     * @param targetX Docelowa współrzędna X.
     * @param targetY Docelowa współrzędna Y.
     * @param obstacles Lista przeszkód na mapie.
     * @return true, jeśli pole jest bezpieczne  false w przeciwnym razie.
     */
    protected boolean isSafe(int targetX, int targetY, List<Obstacle> obstacles) {
        for (Obstacle o : obstacles) {
            if (o.getX() == targetX && o.getY() == targetY) return false;
        }
        return true;
    }


    /**
     * Wykonuje ruch w kierunku podanego celu, omijając przeszkody.
     * Zmienia współrzędne x, y o 1 w stronę targetX, targetY.
     * @param targetX Współrzędna X celu.
     * @param targetY Współrzędna Y celu.
     * @param obstacles Lista przeszkód do sprawdzenia kolizji.
     */
    protected void moveTowards(int targetX, int targetY, List<Obstacle> obstacles) {
        int nextX = this.x;
        int nextY = this.y;

        if (this.x < targetX) nextX++;
        else if (this.x > targetX) nextX--;

        if (this.y < targetY) nextY++;
        else if (this.y > targetY) nextY--;

        // Idziemy jeśli nie ma tam skały
        if (isSafe(nextX, nextY, obstacles)) {
            this.x = nextX;
            this.y = nextY;
        }
        keepInBounds();//wirtualna sciaan zeby zwierze niw wyszlo za mape
    }

    /**
     * Wykonuje losowy ruch w dowolnym kierunku lub pozostaje w miejscu
     *
     * @param obstacles Lista przeszkód
     */
protected void randomMove(List<Obstacle> obstacles) {
    int nextX = x + (random.nextInt(3) - 1);
    int nextY = y + (random.nextInt(3) - 1);

    if (isSafe(nextX, nextY, obstacles)) {
        x = nextX;
        y = nextY;
    }
    keepInBounds();
}
    /**
     * Utrzymuje zwierzę w granicach mapy (0 do MAP_SIZE-1).
     * Sciana
     */
    private void keepInBounds() {
        x = Math.max(0, Math.min(MAP_SIZE - 1, x));
        y = Math.max(0, Math.min(MAP_SIZE - 1, y));
    }

    public boolean isAlive() { return alive; }
    public String getName() { return name; }
    public int getEnergy() { return energy; }
    public int getX() { return x; }
    public int getY() { return y; }
}