package tetris;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This class is responsible for the highest-level graphical elements. This class
 * controls where the specific panes fall in the BorderPane.
 */
public class PaneOrganizer {
    private BorderPane root;

    /**
     * This is the constructor for the PaneOrganizer class. This constructor
     * is responsible for instantiating the root instance of BorderPane, creating
     * a new pane called gamePane, and adding the gamePane and button pane to
     * the root.
     */
    public PaneOrganizer() {
        this.root = new BorderPane();
        Pane gamePane = new Pane();
        this.root.setCenter(gamePane);
        Game game = new Game(gamePane);
        this.root.setBottom(game.getButtonPane());
    }

    /**
     * This method is responsible for returning the root pane for the app class
     * to add to the scene.
     */
    public BorderPane getRoot() {
        return this.root;
    }
}