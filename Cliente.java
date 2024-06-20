import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private Gerente gerente;
    private List<Conta> contas;

    public Cliente(String nome, String endereco, String rg, String cpf, String telefone, String senha, Gerente gerente) {
        super(nome, endereco, rg, cpf, telefone, senha);
        this.gerente = gerente;
        this.contas = new ArrayList<>();
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }
}