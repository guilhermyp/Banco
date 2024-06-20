import java.util.ArrayList;
import java.util.List;

public class Gerente extends Funcionario {
    private List<Cliente> clientes;

    public Gerente(String nome, String endereco, String rg, String cpf, String telefone, String senha, String matricula) {
        super(nome, endereco, rg, cpf, telefone, senha, matricula);
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarContaParaCliente(Cliente cliente, Conta conta) {
        cliente.addConta(conta);
    }

    // Getters and setters for clientes
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}