package tetrisrecode;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start (Stage stage) {
        stage.setTitle("Tetris Recode");
        AppLayout appL = new AppLayout();
        Scene scene = new Scene (appL.getRoot(), Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] argv) {launch(argv);}
}