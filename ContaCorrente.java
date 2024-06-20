public class ContaCorrente extends Conta {
    private double taxaCDB;

    public ContaCorrente(String numero, double saldo, double limite, double taxaCDB) {
        super(numero, saldo, limite);
        this.taxaCDB = taxaCDB;
    }

    @Override
    public void aplicarRendimento() {
        double rendimento = getSaldo() * taxaCDB;
        depositar(rendimento);
    }

    // Getter e setter para taxaCDB
    public double getTaxaCDB() {
        return taxaCDB;
    }

    public void setTaxaCDB(double taxaCDB) {
        this.taxaCDB = taxaCDB;
    }
}
ContaPoupanca.java
java
Copiar código
public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(String numero, double saldo, double limite, double taxaRendimento) {
        super(numero, saldo, limite);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void aplicarRendimento() {
        double rendimento = getSaldo() * taxaRendimento;
        depositar(rendimento);
    }

    // Getter e setter para taxaRendimento
    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }
}