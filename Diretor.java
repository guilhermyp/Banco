import java.util.ArrayList;
import java.util.List;

public class Diretor extends Funcionario {
    private List<Gerente> gerentes;

    public Diretor(String nome, String endereco, String rg, String cpf, String telefone, String senha, String matricula) {
        super(nome, endereco, rg, cpf, telefone, senha, matricula);
        this.gerentes = new ArrayList<>();
    }

    public void cadastrarGerente(Gerente gerente) {
        gerentes.add(gerente);
    }

    public void deletarGerente(Gerente gerente) {
        gerentes.remove(gerente);
    }

    // Getters and setters for gerentes
    public List<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Gerente> gerentes) {
        this.gerentes = gerentes;
    }
}