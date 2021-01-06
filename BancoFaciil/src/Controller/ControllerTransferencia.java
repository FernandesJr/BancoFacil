package Controller;

import DAO.Conexao;
import DAO.ContaDAO;
import Model.Conta;
import View.Transferencia;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class ControllerTransferencia {
    
    private final Transferencia view;
    private String capturaValor= "";
    private String valorFormatadoStr;
    private final DecimalFormat formataValor = new DecimalFormat("###,###.00");
    private double valor;

    public ControllerTransferencia(Transferencia view) {
        this.view = view;
    }
    
    public void transferindo(ControllerMenuPrincipal telaMenu, Conta conta) throws SQLException{
        
        if (this.valor > 0){
            boolean confirmado = this.confirma("Confirmar transferência no valor de: R$ " + this.valorFormatadoStr);
            if (confirmado){
                if (conta.getSaldo() < this.valor){
                    JOptionPane.showMessageDialog(null,"Saldo indisponível. "
                    + "Seu saldo é de: R$ " + formataValor.format(conta.getSaldo()));
                    this.view.getTxtTransferencia().setText("");
                    this.capturaValor = "";
                    this.valor = 0;
                }else{
                    boolean validarConta = false;
                    Conexao conexao = new Conexao();
                    Connection connection = conexao.getConnection();
                    ContaDAO contaDao = new ContaDAO(connection, this.view.getUsuario());
                    try {
                        validarConta = contaDao.atualizarSaldo(this.valor, this.view.getjTextFieldContaDestino().getText()); //Aqui pesso o valor para qual id de conta vai.
                        contaDao.addmovimentacaoTransferencia(this.view.getjTextFieldContaDestino().getText(),"Transferência (+) --->  +R$ ", this.valorFormatadoStr); //Salvo uma movimentação lá na conta de destino.
                    } catch (Exception e) {
                        
                        this.view.getjTextFieldContaDestino().requestFocus();
                    }
                    if(validarConta){
                        contaDao.atualizarSaldo(conta.getSaldo() - this.valor); //Atualizando o saldo no banco de dados
                        conta.setSaldo(conta.getSaldo() - this.valor); //Atualizando saldo na conta viva no sistema.
                        contaDao.addMovimentação("Transferência (-) --->  -R$ ", this.valorFormatadoStr);
                        JOptionPane.showMessageDialog(view, "Transferência efetuada com sucesso.");
                        telaMenu.desbloquear();
                        this.view.dispose();
                    }else{
                        JOptionPane.showMessageDialog(view, "Conta inválida.");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(view, "Operação cancelada");
            }
        }
    }

    public void setNumeroFormatado(String numero){
        
        this.valor = Double.parseDouble(numero); //Essa variável que é responsável por fazer a depósi0o
        this.valorFormatadoStr = formataValor.format(valor);
        this.view.getTxtTransferencia().setText(this.valorFormatadoStr);
    }
    
    public void setNumero(String numero, boolean seletor){
        
        if(!seletor){
            //Esse número é que será convertido e Double para execultar todas operações.
            //Caso Ainda estejá digitando o valor em moedas entrara nessa condição.
            if (capturaValor.contains(".") && numero.equals(".")){
                this.setNumeroFormatado(this.capturaValor);
                JOptionPane.showMessageDialog(view, "Agora digite os centavos.");
            }else{
                this.capturaValor = this.capturaValor + numero;
                this.setNumeroFormatado(this.capturaValor);
            }
        }else{
            //Seta o valor no campode destino de transferencia.
            this.view.getjTextFieldContaDestino().setText(this.view.getjTextFieldContaDestino().getText() + numero);
        }
    }

    public void setLimpar(boolean seletor) {
        
        if(!seletor){
            //Caso Ainda estejá digitando o valor em moedas entrara nessa condição.
            this.capturaValor = "";
            this.view.getTxtTransferencia().setText("");
            this.valor = 0;
        }else{
            //Caso esteja digitando o número da conta
            this.view.getjTextFieldContaDestino().setText("");
        }
        
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
    
    private boolean confirma(String msg){
        
        int confirmar = JOptionPane.showConfirmDialog(view, msg); //retorna 0 p/SIM e 1 p/NAO
        return confirmar == 0;
    }

    public void validarConta() throws SQLException {
        
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        ContaDAO contaDao = new ContaDAO(connection);
        String nome = contaDao.validarConta(this.view.getjTextFieldContaDestino().getText());
        this.view.getjTextFieldUserQuery().setText("Destinatário: " + nome.toUpperCase());
    }
}
