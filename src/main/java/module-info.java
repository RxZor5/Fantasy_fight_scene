module fantasyrollenspiel {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens fantasyrollenspiel to javafx.fxml;
    exports fantasyrollenspiel;
    exports fantasyrollenspiel.Monster;
    opens fantasyrollenspiel.Monster to javafx.fxml;
    exports fantasyrollenspiel.Hero;
    opens fantasyrollenspiel.Hero to javafx.fxml;
    exports fantasyrollenspiel.Animations;
    opens fantasyrollenspiel.Animations to javafx.fxml;
}