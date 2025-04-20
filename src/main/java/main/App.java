package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.ui.MainDashboard;

public class App extends Application {

    private MainDashboard mainDashboard = new MainDashboard();

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainDashboard.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
