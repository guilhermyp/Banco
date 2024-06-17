package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import controller.GerenteController;

public class GerenteUI {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Gerente");

        GridPane grid = new GridPane();

        Label nomeLabel = new Label("Nome do Cliente:");
        TextField nomeInput = new TextField();
        grid.add(nomeLabel, 0, 0);
        grid.add(nomeInput, 1, 0);

        Label cpfLabel = new Label("CPF do Cliente:");
        TextField cpfInput = new TextField();
        grid.add(cpfLabel, 0, 1);
        grid.add(cpfInput, 1, 1);

        Button cadastrarClienteButton = new Button("Cadastrar Cliente");
        cadastrarClienteButton.setOnAction(e -> {
            String nome = nomeInput.getText();
            String cpf = cpfInput.getText();
            Cliente cliente = new Cliente(nome, cpf);
            GerenteController.cadastrarCliente(cliente);
            // Adicionar persistência de dados aqui
        });
        grid.add(cadastrarClienteButton, 1, 2);

        Label contaLabel = new Label("Número da Conta:");
        TextField contaInput = new TextField();
        grid.add(contaLabel, 0, 3);
        grid.add(contaInput, 1, 3);

        Label tipoContaLabel = new Label("Tipo de Conta:");
        ChoiceBox<String> tipoContaChoiceBox = new ChoiceBox<>();
        tipoContaChoiceBox.getItems().addAll("Corrente", "Poupança");
        grid.add(tipoContaLabel, 0, 4);
        grid.add(tipoContaChoiceBox, 1, 4);

        Button cadastrarContaButton = new Button("Cadastrar Conta");
        cadastrarContaButton.setOnAction(e -> {
            String numero = contaInput.getText();
            String tipo = tipoContaChoiceBox.getValue();
            Conta conta;
            if (tipo.equals("Corrente")) {
                conta = new ContaCorrente(numero);
            } else {
                conta = new ContaPoupanca(numero);
            }
            GerenteController.cadastrarConta(conta);
            // Adicionar persistência de dados aqui
        });
        grid.add(cadastrarContaButton, 1, 5);

        Scene scene = new Scene(grid, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}