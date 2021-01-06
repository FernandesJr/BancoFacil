package Controller;

import Model.Conta;
import View.MenuPrincipal;
import View.Saldo;
import View.Deposito;
import View.Extrato;
import View.Login;
import View.Saque;
import View.Transferencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;

public class ControllerMenuPrincipal {
    
    private final MenuPrincipal view;
    
    private Conta conta = new Conta(0);  //Essa é a conta que vai ser usada durante toda a logica de execulção
    

    public ControllerMenuPrincipal(MenuPrincipal view) {    //Contrutor que recebe a tela que controla, Menuprincipal no caso
        this.view = view;
    }
    
    public void navegarParaSaldo(){
        Saldo saldo = new Saldo();
        saldo.setVisible(true);
        saldo.trazerMenu(this);   //Importa o controlador do menu
        saldo.trazerConta(conta); //Leva a conta para ser usada na tela Saldo
    }
    
    public void navegarParaDeposito(){
        Deposito deposito = new Deposito();
        deposito.setVisible(true);
        deposito.trazerMenu(this);  //Importa o controlador do menu
        deposito.trazerConta(conta);//Leva a conta para ser usada na tela Deposito
        deposito.trazerUsuario(this.view.usuario);
    }
    
    public void navegarParaSaque(){
        Saque saque = new Saque();
        saque.setVisible(true);
        saque.trazerMenu(this);   //Importa o controlador do menu
        saque.trazerConta(conta); //Leva a conta para ser usada na tela Saque
        saque.trazerUsuario(this.view.usuario);
    }
    
    public void navegarParaExtrato(){
        Extrato extrato = new Extrato();
        extrato.setVisible(true);
        extrato.trazerMenu(this);   //Importa o controlador do menu
        extrato.trazerConta(conta); //leva a conta para ser usada na tela extrato
        extrato.trazerUsuario(this.view.usuario); //leva o usuario para o extrato
    }
    
    public void navegarParaTransferencia(){
        Transferencia transferencia = new Transferencia();
        transferencia.setVisible(true);
        transferencia.trazerMenu(this);
        transferencia.trazerConta(conta);
        transferencia.trazerUsuario(this.view.usuario);
    }
    
    public void bloquear(){
        //Bloquear todos os botões do Menu Principal, por segurança das informações
        this.view.getjButtonDeposito().setEnabled(false); 
        this.view.getjButtonSaldo().setEnabled(false);
        this.view.getjButtonExtrato().setEnabled(false);
        this.view.getjButtonSaque().setEnabled(false);
        this.view.getjButtonSair().setEnabled(false);
        this.view.getjButtonTransferencia().setEnabled(false);
    }
    
    public void desbloquear(){
        //Desbloquear todos os botões do Menu Principal, por segurança das informações
        this.view.getjButtonDeposito().setEnabled(true); 
        this.view.getjButtonSaldo().setEnabled(true);
        this.view.getjButtonExtrato().setEnabled(true);
        this.view.getjButtonSaque().setEnabled(true);
        this.view.getjButtonSair().setEnabled(true);
        this.view.getjButtonTransferencia().setEnabled(true);
    }
    
    public void importarDeposito(double valor){
        //Trás o valor do deposito lá do Controller deposito
        this.conta.setDeposito(valor);
        this.conta.setSaldo(this.conta.getSaldo() + this.conta.getDeposito());
    }
    
    public void importarSaque(double valor){
        //Trás o valor do saque lá do controller saque
        this.conta.setSaque(valor);
        this.conta.setSaldo(this.conta.getSaldo() - this.conta.getSaque());
    }
    
    public void sair(){
        //Faz logoff
        this.view.dispose();
        Login login = new Login();
        login.setVisible(true);
    }
    
    public void data(){
        Date dataSistema = new Date();
        SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
        view.getjLabelData2().setText(formatada.format(dataSistema));
    }
    
    public void hora(){
        Timer time = new Timer(1000, new Hora()); //Objeto que mostra hora atualizada
        time.start();
    }
    
    class Hora implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Calendar dataHora = Calendar.getInstance();  //Esse metodo trás tudo, hora e data
            view.getjLabelData().setText(String.format("%1$tH:%1$tM:%1$tS", dataHora)); //Formatação para mostrar somente a hora
        }
    }
    
    public void trazerConta(Conta conta){
        this.conta = conta;
    }
}
