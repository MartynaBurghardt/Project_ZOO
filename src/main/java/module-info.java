module pl.zoo.virtualzoo {
    requires javafx.controls;
    requires javafx.fxml;

    exports pl.zoo.virtualzoo;
    exports pl.zoo.virtualzoo.model;

    opens pl.zoo.virtualzoo to javafx.fxml;
}
