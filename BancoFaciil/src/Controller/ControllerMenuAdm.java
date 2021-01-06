package Controller;

import DAO.Conexao;
import DAO.ContaDAO;
import DAO.UsuarioDAO;
import Model.Usuario;
import View.EditarUsuario;
import View.ListaUsuarios;
import View.LoginPrincipal;
import View.MenuAdm;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class ControllerMenuAdm {
    
    private final MenuAdm view;

    public ControllerMenuAdm(MenuAdm view) {
        this.view = view;
    }
    
    public void sair(){
        LoginPrincipal login = new LoginPrincipal();
        login.setVisible(true);
        this.view.dispose();
    }
    
    public void cadastrar() throws SQLException{
        
        String nome = view.getTxtNome().getText().trim();  //O Trim retira os espaços do inicio e final da String
        String senha= view.getTxtSenha().getText().trim();
        
        boolean confirmar = confirma("Confirma o cadastro de: " + nome);
        
        Usuario usuario = new Usuario(nome, senha);
        
        if(confirmar){
            Conexao conexao = new Conexao();
            Connection connection = conexao.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            usuarioDAO.inserir(usuario);
            this.limpaTela();
        }else{
            JOptionPane.showMessageDialog(view, "Operação cancelada");
        }
    }
    
    public void deletar() throws SQLException{
        
        String nome  = view.getTxtNome().getText();
        String senha = view.getTxtSenha().getText();
        Usuario usuario = new Usuario(nome, senha);
        
        boolean confirmar = confirma("Realmente deseja excluir o usuário? " + nome);
        
        if (confirmar){
            Conexao conexao = new Conexao();
            Connection connection = conexao.getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(connection);
            usuarioDao.delete(usuario);
            this.limpaTela();
            
        } else {
            JOptionPane.showMessageDialog(view, "Operação cancelada");
        }
        
    }
    
    public void editar() throws SQLException{
        
        String nome  = view.getTxtNome().getText();
        String senha = view.getTxtSenha().getText();
        Usuario usuario = new Usuario(nome, senha);
        
        boolean confirmar = confirma("Realmente deseja editar o usuário? " + nome);
        
        if (confirmar){
            Conexao conexao = new Conexao();
            Connection connection = conexao.getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(connection);
            boolean valido = usuarioDao.validarUsuario(usuario);
            if (valido){
                EditarUsuario user = new EditarUsuario();
                user.setVisible(true);
                user.trazerMenu(this);
                user.trazerUsuario(usuario);
                this.bloquear();
                this.view.getTxtNome().setText("");
                this.view.getTxtSenha().setText("");
            }else{
                JOptionPane.showMessageDialog(view, "Usuário não existente no Banco de Dados.");
            }
        }else {
           JOptionPane.showMessageDialog(view, "Operação cancelada"); 
        }
    }
    
    public void limpaTela(){
        this.view.getTxtNome().setText("");
        this.view.getTxtSenha().setText("");
        this.view.getTxtNome().requestFocus();
    }
    
    public void desbloquear(){
        this.view.getjButtonCadastrar().setEnabled(true);
        this.view.getjButtonExcluir().setEnabled(true);
        this.view.getjButtonLista().setEnabled(true);
        this.view.getjButtonSair().setEnabled(true);
        this.view.getjButtonEditar().setEnabled(true);
    }
    
    public void bloquear(){
        this.view.getjButtonCadastrar().setEnabled(false);
        this.view.getjButtonExcluir().setEnabled(false);
        this.view.getjButtonLista().setEnabled(false);
        this.view.getjButtonSair().setEnabled(false);
        this.view.getjButtonEditar().setEnabled(false);
    }

    public void navegarParaLista() {
        bloquear();
        ListaUsuarios lista = new ListaUsuarios();
        lista.setVisible(true);
        lista.trazerMenu(this);
    }
    
    private boolean confirma(String msg){
        int confirmar = JOptionPane.showConfirmDialog(view, msg); //retorna 0 p/SIM e 1 p/NAO
        return confirmar == 0;
    }
    
    public void saldoGeral() throws SQLException{
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        ContaDAO contaDao = new ContaDAO(connection);
        Double valorGeral = contaDao.saldoGeral();
        DecimalFormat valorf = new DecimalFormat("###,###.00");
        String valorFormatado = valorf.format(valorGeral);
        this.view.getjLabelTxtSaldoGeral().setText(valorFormatado);
    }
}
