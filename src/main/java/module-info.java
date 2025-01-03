module fantasyrollenspiel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens fantasyrollenspiel to javafx.fxml;
    exports fantasyrollenspiel;
    exports fantasyrollenspiel.Fight;
    exports fantasyrollenspiel.Fight.Buttons;
    exports fantasyrollenspiel.Fight.Potions;
    exports fantasyrollenspiel.Controller;
    opens fantasyrollenspiel.Controller to javafx.fxml;
}
