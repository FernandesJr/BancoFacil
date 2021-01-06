package Controller;

import DAO.Conexao;
import DAO.ContaDAO;
import Model.Conta;
import View.Saque;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class ControllerSaque {
    private final Saque view;
    private double valor;
    private String capturaValor= ""; //Sempre contém o número sem formatação ex 1555.1
    private final DecimalFormat formataValor = new DecimalFormat("###,###.00"); //Padrão de formatação em moeda ela pega o lacale da máquina
    private String valorFormatadoStr; //Nessa variável contém o valor do campo txt formatada em Moeda REAL

    public ControllerSaque(Saque view) {
        this.view = view;
    }
    
    public void voltarMenu(ControllerMenuPrincipal menu){   //Contrutor que recebe a tela que controla, Menuprincipal no caso
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
    
    public void sacando(ControllerMenuPrincipal menu, Conta conta){
        //Verifica o saldo em conta e caso positivo efetua a busca do que o usuario digitou em seta no valor de saque
        try {
            if(this.valor > 0){
                boolean confirmado = this.confirma("Confirma o saque no valor de: R$ " + this.valorFormatadoStr);
                if (confirmado){
                    if (conta.getSaldo() < this.valor){
                        JOptionPane.showMessageDialog(null,"Saldo indisponível. "
                        + "Seu saldo é de: R$ " + formataValor.format(conta.getSaldo()));
                        view.getTxtValorSaque().setText("");
                        this.capturaValor = "";
                        this.valor = 0;
                    }else {
                        conta.setSaque(this.valor);
                        menu.importarSaque(conta.getSaque());
                        JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso.");
                
                        //Atualizar todas as informações no banco de dados
                        Conexao conexao = new Conexao();
                        Connection connection = conexao.getConnection();
                        ContaDAO contaDao = new ContaDAO(connection, this.view.getUsuario());
                        contaDao.atualizarSaldo(conta.getSaldo());
                        contaDao.addMovimentação("Saque     ----------->  -R$ ", this.valorFormatadoStr);
                        view.getTxtValorSaque().setText("Volte ao menu");
                        this.capturaValor = "";
                        this.valorFormatadoStr = "";
                        this.valor = 0;
                    }
                }else{
                    JOptionPane.showMessageDialog(view, "Operação cancelada");
                }
            }
        }catch (HeadlessException | SQLException erro){
            JOptionPane.showMessageDialog(view, "Valor invalido.");
            this.view.getTxtValorSaque().setText("");
        }
    }
    
    public void setNumeroFormatado(String numero){
        
        this.valor = Double.parseDouble(numero); //Essa variável que é responsável por fazer o depósito
        String numFormatado = formataValor.format(valor);
        this.view.getTxtValorSaque().setText(numFormatado);
        this.valorFormatadoStr = numFormatado;
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
        this.view.getTxtValorSaque().setText("");
    }
    
    private boolean confirma(String msg){
        int confirmar = JOptionPane.showConfirmDialog(view, msg); //retorna 0 p/SIM e 1 p/NAO
        return confirmar == 0;
    }
}
