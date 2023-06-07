package finalproject;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class FinalProject extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        ViewManager.openLoginPatient();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
