classDiagram
class Animal {
    <<abstract>>
    #String name
    #int energy
    #int x
    #int y
    #boolean alive
    +move(List~Animal~ others)
    +eat(Animal other)
    +reproduce() Animal
    +distanceTo(Animal other)
}
class Wolf {
    +move()
    +eat()
}
class Rabbit {
    +move()
    +eat()
}
class Lion
class Zebra
class Bear
Animal <|-- Wolf
Animal <|-- Rabbit
Animal <|-- Lion
Animal <|-- Zebra
Animal <|-- Bear
class Plant {
    -int x
    -int y
}
class Environment {
    -List~Animal~ animals
    -List~Plant~ plants
    +addAnimal(Animal a)
    +getAnimals()
    +getPlants()
}
Environment o-- Animal : zawiera
Environment o-- Plant : zawiera
class SimulationEngine {
    -Environment env
    +nextTurn()
    -simulateMovement()
    -simulateEating()
}
SimulationEngine --> Environment : zarządza
class HelloController {
    -SimulationEngine engine
    -Canvas canvas
    +onNextTurnClick()
    -draw()
}
HelloController --> SimulationEngine : steruje
