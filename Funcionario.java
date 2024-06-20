public class Funcionario extends Pessoa {
    private String matricula;

    public Funcionario(String nome, String endereco, String rg, String cpf, String telefone, String senha, String matricula) {
        super(nome, endereco, rg, cpf, telefone, senha);
        this.matricula = matricula;
    }

    // Getter and setter for matricula
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}