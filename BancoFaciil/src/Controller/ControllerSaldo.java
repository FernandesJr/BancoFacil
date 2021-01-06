package Controller;

import Model.Conta;
import View.Saldo;
import java.text.DecimalFormat;

public class ControllerSaldo {
    
    private final Saldo view;
    private Conta conta;

    public ControllerSaldo(Saldo view) {  //Construtor que recebe a tela que controla, Saldo no caso
        this.view = view;
    }
    
    public void voltarMenu(ControllerMenuPrincipal Cmenu){
        //Fecha tela atual e volta ao menu e desbloqueia os botões do menu
        Cmenu.desbloquear();
        this.view.dispose();
    }
    
    public String mostrarSaldo(Conta conta){
        //Busca o saldo e transforma em String para setar na tela
        this.conta = conta;
        String valor;
        DecimalFormat valorf = new DecimalFormat("###,###.00");
        valor = valorf.format(conta.getSaldo());
        return valor;
    }
}
