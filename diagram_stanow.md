stateDiagram-v2
    [*] --> Zywy
    
    Zywy --> Ruch : move()
    Ruch --> Zywy : energia spada
    
    Zywy --> Jedzenie : eat()
    Jedzenie --> Zywy : energia rosne
    
    Zywy --> Martwy : energia <= 0
    Martwy --> [*]
