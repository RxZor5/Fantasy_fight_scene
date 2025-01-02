module fantasyrollenspiel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens fantasyrollenspiel to javafx.fxml;
    exports fantasyrollenspiel;
    exports fantasyrollenspiel.Fight;
    exports fantasyrollenspiel.Fight.Buttons;
}
