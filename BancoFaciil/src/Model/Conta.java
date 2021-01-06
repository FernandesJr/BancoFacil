package Model;


public class Conta {
    
    //Atributos da conta
    
    private double saldo;
    private double deposito;
    private double saque;
    private int id;
    
    public Conta(float saldo) { //Construtor
        this.saldo = saldo;
    }
    
    //Metodos especiais
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getDeposito() {
        return deposito;
    }

    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }

    public double getSaque() {
        return saque;
    }

    public void setSaque(double saque) {
        this.saque = saque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
