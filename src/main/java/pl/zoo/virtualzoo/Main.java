package pl.zoo.virtualzoo;

import pl.zoo.virtualzoo.environment.Environment;
import pl.zoo.virtualzoo.model.Lion;
import pl.zoo.virtualzoo.model.Zebra;
import pl.zoo.virtualzoo.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        Environment environment = new Environment();

        environment.addAnimal(new Lion("Simba"));
        environment.addAnimal(new Zebra("Marty"));
        environment.addAnimal(new Zebra("Alex"));

        SimulationEngine engine = new SimulationEngine(environment);
        engine.run(3);
    }
}
