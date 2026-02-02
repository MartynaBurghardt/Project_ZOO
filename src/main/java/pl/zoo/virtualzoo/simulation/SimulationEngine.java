package pl.zoo.virtualzoo.simulation;

import pl.zoo.virtualzoo.environment.Environment;

public class SimulationEngine {

    private Environment environment; //dostp do klasy environment
    private int turn = 0; //licznik tury

    public SimulationEngine(Environment environment) {
        this.environment = environment;
    }

    //wykorzystywane wczesniej przy testach, obecnie następna tura tylko przez przycisk
    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            nextTurn();
        }
    }

    public void nextTurn() {
        turn++;
        environment.simulateTurn();
        printState(); //status w konsoli
    }

    private void printState() {
        environment.getAnimals().forEach(a ->
                System.out.println("Tura " + turn + " | " + a.getName() + " " + a.isAlive())
        );
    }
}
