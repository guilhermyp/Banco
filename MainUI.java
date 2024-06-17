package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Banco");

        GridPane grid = new GridPane();
        Label label = new Label("Bem-vindo ao Sistema de Banco!");
        grid.add(label, 0, 0);

        Button gerenteButton = new Button("Gerente");
        gerenteButton.setOnAction(e -> GerenteUI.display());
        grid.add(gerenteButton, 0, 1);

        Button adminButton = new Button("Admin");
        adminButton.setOnAction(e -> AdminUI.display());
        grid.add(adminButton, 0, 2);

        Button clienteButton = new Button("Cliente");
        clienteButton.setOnAction(e -> ClienteUI.display());
        grid.add(clienteButton, 0, 3);

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}