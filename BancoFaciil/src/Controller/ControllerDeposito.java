package Controller;

import DAO.Conexao;
import DAO.ContaDAO;
import Model.Conta;
import View.Deposito;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class ControllerDeposito {
    
    private final Deposito view;
    private double valor;
    private String capturaValor= "";
    private final DecimalFormat formataValor = new DecimalFormat("###,###.00");
    private String valorFormatadoStr; //Nessa variável contém o valor do campo txt formatada em Moeda REAL

    public ControllerDeposito(Deposito view) {   //Contrutor que recebe a tela que controla, depoisto no caso
        this.view = view;
    }
    
    public void voltarMenu(ControllerMenuPrincipal menu){
        //Fecha tela atual e volta ao menu e desbloqueia os botões do menu
        if(this.valor > 0){
            boolean conf = this.confirma("Deseja CANCELAR a operação?");
            if (conf){
                menu.desbloquear();
                this.view.dispose();
            }
        }else{
            menu.desbloquear();
            this.view.dispose();
        }
    }
    
    public void depositando(ControllerMenuPrincipal menu, Conta conta){
        //Busca o valor que o usuario digitou e seta em valor de depoisto
        try {
            if (this.valor > 0){
                boolean confirmado = confirma("Confirma o depósito no valor de: R$ " + this.valorFormatadoStr);
                if (confirmado){
                    conta.setDeposito(this.valor);
                    menu.importarDeposito(conta.getDeposito());
                    JOptionPane.showMessageDialog(null, "Deposito efetuado com sucesso");

                    //Atualizar todas as informações no banco de dados
                    Conexao conexao = new Conexao();
                    Connection connection = conexao.getConnection();
                    ContaDAO contaDao = new ContaDAO(connection, this.view.getUsuario()); 
                    contaDao.atualizarSaldo(conta.getSaldo()); //Quando eu importo o valor de depósito ele já atualiza na conta por isso que aqui eu já pego o valor de saldo.
                    contaDao.addMovimentação("Depósito ----------->  +R$ ", this.valorFormatadoStr);
                    this.view.getTxtDeposito().setText("volte ao menu");
                    this.capturaValor = "";
                    this.valorFormatadoStr = "";
                    this.valor = 0;
                }else{
                    JOptionPane.showMessageDialog(view, "Operação cancelada");
                }
            }
        }catch(HeadlessException | NumberFormatException | SQLException erro){
            JOptionPane.showMessageDialog(view, "Valor incorreto.");
            this.view.getTxtDeposito().setText("");
        }
    }
    
    public void setNumeroFormatado(String numero){
        
        this.valor = Double.parseDouble(numero); //Essa variável que é responsável por fazer o depósito
        this.valorFormatadoStr = formataValor.format(valor);
        this.view.getTxtDeposito().setText(this.valorFormatadoStr);
    }
    
    public void setNumero(String numero){
        //Esse número é que será convertido e Double para execultar todas operações.
        
        if (capturaValor.contains(".") && numero.equals(".")){
            this.setNumeroFormatado(this.capturaValor);
            JOptionPane.showMessageDialog(view, "Agora digite os centavos.");
        }else{
            this.capturaValor = this.capturaValor + numero;
            this.setNumeroFormatado(this.capturaValor);
        }
    }

    public void setLimpar() {
        this.capturaValor = "";
        this.view.getTxtDeposito().setText("");
    }
    
    private boolean confirma(String msg){
        int confirmar = JOptionPane.showConfirmDialog(view, msg); //retorna 0 p/SIM e 1 p/NAO
        return confirmar == 0;
    }
}
