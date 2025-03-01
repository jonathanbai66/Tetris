package tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris");
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.GAME_WIDTH,
                Constants.GAME_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] argv) {
        launch(argv);
    }
}
