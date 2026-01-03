package pl.zoo.virtualzoo.simulation;

import pl.zoo.virtualzoo.environment.Environment;

public class SimulationEngine {

    private Environment environment;
    private int turn = 0;

    public SimulationEngine(Environment environment) {
        this.environment = environment;
    }

    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            nextTurn();
        }
    }

    public void nextTurn() {
        turn++;
        environment.simulateTurn();
        printState();
    }

    private void printState() {
        environment.getAnimals().forEach(a ->
                System.out.println("Tura " + turn + " | " + a.getName() + " " + a.isAlive())
        );
    }
}
