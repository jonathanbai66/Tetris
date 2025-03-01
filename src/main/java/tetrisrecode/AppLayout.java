package tetrisrecode;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AppLayout {
    private BorderPane root;
    public AppLayout() {
        this.root = new BorderPane();
        Pane gamePane = new Pane();
        this.root.setCenter(gamePane);
        Game game = new Game(gamePane);
        this.root.setBottom(game.getButtonPane());
    }
    public BorderPane getRoot() {
        return this.root;
    }
}