package pl.zoo.virtualzoo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import pl.zoo.virtualzoo.environment.Environment;
import pl.zoo.virtualzoo.environment.Obstacle;
import pl.zoo.virtualzoo.model.*;
import pl.zoo.virtualzoo.simulation.SimulationEngine;
import javafx.scene.image.Image;

import java.util.Objects;
/**
 * Główna klasa aplikacji, odpowiedzialna za warstwę wizualną.
 */
public class HelloApplication extends Application {

    private SimulationEngine engine;
    private Environment environment;
    private Canvas canvas;
    private final int CELL_SIZE = 45;
    private boolean isNight = false;
    // Obrazki
    private Image bearImg, babyBearImg, lionImg, babyLionImg, wolfImg, babyWolfImg,
            rabbitImg, babyRabbitImg, zebraImg, babyZebraImg, mountImg, lakeImg;
    /**
     * Główna metoda startowa JavaFX
     */
    @Override
    public void start(Stage stage) {
        // 1. Inicjalizacja ZOO
        initZoo(); //Environment i simulation engine


        // 2. Tworzenie widoku
        Button nextTurnBtn = new Button("NASTĘPNA TURA (KLIKNIJ)");
        nextTurnBtn.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: lightblue;");
        nextTurnBtn.setOnAction(e -> {
            engine.nextTurn();
            draw();
        });

        Button dayNightBtn = new Button("ZMIEŃ PORĘ DNIA");
        dayNightBtn.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #2c3e50; -fx-text-fill: white;");
        dayNightBtn.setOnAction(e -> {
            isNight = !isNight;
            draw();
        });




        canvas = new Canvas(900, 900);

        // Kontener na mapę (żeby można przewijać)
        ScrollPane scrollPane = new ScrollPane();
        VBox centerBox = new VBox(canvas);
        centerBox.setAlignment(Pos.CENTER);
        scrollPane.setContent(centerBox);
        scrollPane.setFitToWidth(true);


        // Główny układ
        VBox root = new VBox(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(nextTurnBtn, scrollPane,  dayNightBtn);
        root.setPadding(new javafx.geometry.Insets(10));

        // 3. Start okna
        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Wirtualne ZOO - Wersja Stabilna");
        stage.setScene(scene);
        stage.show();

        // Pierwsze rysowanie
        draw();
    }
    /**
     * Inicjalizuje środowisko symulacji, dodaje początkowe zwierzęta i tworzy silnik
     */
    private void initZoo() {
        environment = new Environment();
        environment.addAnimal(new Wolf("Geralt"));
        environment.addAnimal(new Wolf("Yennefer"));
        environment.addAnimal(new Rabbit("Bugs"));
        environment.addAnimal(new Rabbit("Lola"));
        environment.addAnimal(new Zebra("Marty"));
        environment.addAnimal(new Zebra("Gloria"));
        environment.addAnimal(new Lion("Simba"));
        environment.addAnimal(new Lion("Nala"));
        environment.addAnimal(new Bear("Baloo"));
        environment.addAnimal(new Bear("Maja"));

        engine = new SimulationEngine(environment);
        loadImages();
    }
    /**
     * Wczytuje pliki obrazków z folderu resources/images
     */
    private void loadImages() {
        try {
            bearImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bear.png")));
            babyBearImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/babybear.png")));
            lionImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/lion.png")));
            babyLionImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/babylion.png")));
            wolfImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/wolf.png")));
            babyWolfImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/babywolf.png")));
            rabbitImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/rabbit.png")));
            babyRabbitImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/babyrabbit.png")));
            zebraImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/zebra.png")));
            babyZebraImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/babyzebra.png")));
            mountImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/mount.jpg")));
            lakeImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/lake.jpg")));
        } catch (Exception e) {
            System.out.println("BŁĄD OBRAZKÓW: " + e.getMessage());
        }
    }
    /**
     * Główna metoda rysująca. Odpowiada za renderowanie całej mapy (Tło , Przeszkody , Rośliny, Zwierzęta , Filtr nocny)
     */
    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.web("#228B22"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // przeszkdoy
        gc.setFill(Color.DARKGRAY);
        if (environment.getObstacles() != null) {
            for (Obstacle obs : environment.getObstacles()) {
                Image imgToDraw;
                if (obs.getType() == Obstacle.Type.MOUNTAIN) {
                    imgToDraw = mountImg;
                } else {
                    imgToDraw = lakeImg;
                }

                if (imgToDraw != null) {
                    gc.drawImage(imgToDraw,
                            obs.getX() * CELL_SIZE,
                            obs.getY() * CELL_SIZE,
                            CELL_SIZE,
                            CELL_SIZE);
                }


            }
        }
        //



        // Trawa
        gc.setFill(Color.LIGHTGREEN);
        for (Plant plant : environment.getPlants()) {
            gc.fillOval(plant.getX() * CELL_SIZE + 10, plant.getY() * CELL_SIZE + 10, 25, 25);
        }

        // Zwierzęta
        for (Animal animal : environment.getAnimals()) {
            Image img = null;
            if (animal instanceof Wolf) img = animal.getName().startsWith("Mały") ? babyWolfImg : wolfImg;
            else if (animal instanceof Rabbit) img = animal.getName().startsWith("Mały") ? babyRabbitImg : rabbitImg;
            else if (animal instanceof Lion) img = animal.getName().startsWith("Mały") ? babyLionImg : lionImg;
            else if (animal instanceof Bear) img = animal.getName().startsWith("Mały") ? babyBearImg : bearImg;
            else if (animal instanceof Zebra) img = animal.getName().startsWith("Mały") ? babyZebraImg : zebraImg;

            if (img != null) {
                gc.drawImage(img, animal.getX() * CELL_SIZE, animal.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            gc.setFill(Color.BLACK);
            gc.fillText(animal.getName() + " (" + animal.getEnergy() + ")",
                    animal.getX() * CELL_SIZE, animal.getY() * CELL_SIZE - 5);

        }
        if (isNight) {
            gc.setFill(Color.rgb(0, 0, 70, 0.6));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.fillText("TRYB NOCNY", 20, 30);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}