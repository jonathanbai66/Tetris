package tetris1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application {

    /**
     * This method starts the game, taking in the stage parameter. This method sets the
     * scene using the root pane and the game dimensions, and gets the stage to set the scene.
     */
    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        stage.setTitle("Tetris");
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
