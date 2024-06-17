package controller;

import model.Cliente;
import model.Conta;
import java.util.ArrayList;
import java.util.List;

public class GerenteController {
    private static List<Cliente> clientes = new ArrayList<>();

    public static void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        // Adicionar lógica de persistência de dados aqui
    }

    public static void cadastrarConta(Conta conta) {
        // Adicionar lógica para associar a conta ao cliente e persistência de dados aqui
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }
}