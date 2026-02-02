package pl.zoo.virtualzoo.environment;
import java.util.Random;
/**
 * Klasa reprezentująca przeszkodę terenową na mapie.
 * Przeszkody są generowane losowo i mogą przyjmować formę Góry lub Jeziora.
 * Zwierzęta nie mogą wchodzić na pola zajmowane przez przeszkody, a rośliny na nich nie rosną
 * </p>
 */
public class Obstacle {
    private int x;
    private int y;
    /**  Określający rodzaj przeszkody */
    public enum Type {
        MOUNTAIN,
        LAKE
    }
    private Type type;
    /**
     * Konstruktor tworzący przeszkodę w losowym miejscu na mapie
     * @param mapSize Rozmiar mapy
     */
    public Obstacle(int mapSize) {
        Random random = new Random();
        this.x = random.nextInt(mapSize);
        this.y = random.nextInt(mapSize);
        this.type = random.nextBoolean() ? Type.MOUNTAIN : Type.LAKE;
    }
    // w konretnym miejscu
    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Type getType() { return type; }
}
