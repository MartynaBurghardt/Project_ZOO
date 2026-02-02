sequenceDiagram
    actor User
    participant GUI as HelloController
    participant Engine as SimulationEngine
    participant Env as Environment
    participant Wolf as Animal

    User->>GUI: Kliknięcie "Następna Tura"
    activate GUI
    GUI->>Engine: nextTurn()
    activate Engine
    
    Engine->>Env: getAnimals()
    Env-->>Engine: lista zwierząt

    loop Dla każdego zwierzęcia
        Engine->>Wolf: move()
        Engine->>Wolf: eat()
    end

    Engine->>Env: removeDeadAnimals()
    Engine-->>GUI: tura zakończona
    deactivate Engine

    GUI->>GUI: draw()
    deactivate GUI
