package pl.zoo.virtualzoo;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pl.zoo.virtualzoo.environment.Environment;
import pl.zoo.virtualzoo.model.*;
import pl.zoo.virtualzoo.simulation.SimulationEngine;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class HelloController {

    @FXML
    private GridPane map;

    private Environment environment;
    private SimulationEngine engine;
    private Map<Class<?>, Image> images = new HashMap<>();

    @FXML
    public void initialize() {

        images.put(Lion.class,
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/images/lion.png")
                )));

        images.put(Zebra.class,
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/images/zebra.png")
                )));

        images.put(Wolf.class,
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/images/wolf.png")
                )));

        images.put(Rabbit.class,
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/images/rabbit.png")
                )));

        images.put(Bear.class,
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/images/bear.png")
                )));

        environment = new Environment();
        environment.addAnimal(new Lion("Simba"));
        environment.addAnimal(new Zebra("Marty"));
        environment.addAnimal(new Zebra("Alex"));
        environment.addAnimal(new Wolf("Alpha"));
        environment.addAnimal(new Rabbit("Bunny"));
        environment.addAnimal(new Bear("Baloo"));

        engine = new SimulationEngine(environment);
        drawMap();
    }


    @FXML
    public void nextTurn() {
        engine.nextTurn();
        drawMap();
    }

    private void drawMap() {
        map.getChildren().clear();

        for (Animal a : environment.getAnimals()) {
            if (!a.isAlive()) continue;

            Image img = images.get(a.getClass());
            ImageView view = new ImageView(img);
            view.setFitWidth(40);
            view.setFitHeight(40);

            Label label = new Label(
                    a.getName() + "\n" + a.getEnergy()
            );
            label.setStyle("-fx-font-size: 9; -fx-text-fill: black;");

            VBox box = new VBox(view, label);
            box.setSpacing(2);
            box.setAlignment(Pos.CENTER);

            map.add(box, a.getX(), a.getY());
        }
    }

}
