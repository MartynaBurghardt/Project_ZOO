flowchart TD
    A([Start Tury]) --> B[Rosną nowe rośliny]
    B --> C[Ruch wszystkich zwierząt]
    C --> D{Czy jest kolizja?}
    D -- Tak --> E[Jedzenie / Walka]
    D -- Nie --> F{Energia <= 0?}
    E --> F
    F -- Tak --> G[Oznacz jako Martwe]
    F -- Nie --> H{Może się rozmnożyć?}
    H -- Tak --> I[Dodaj dziecko do listy]
    H -- Nie --> J[Koniec dla tego zwierzęcia]
    G --> K[Usuń martwe z listy]
    I --> K
    J --> K
    K --> L([Koniec Tury])
