package Controller;

import DAO.Conexao;
import DAO.ContaDAO;
import DAO.UsuarioDAO;
import Model.Conta;
import Model.Usuario;
import View.Login;
import View.LoginPrincipal;
import View.MenuPrincipal;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControllerLogin {

    private final Login view;
    
    public ControllerLogin(Login view) { //Construtor que recebe a tela que controla, Login no caso
        this.view = view;
    }
    
    public void entrarNoSistema() throws SQLException{
        
        //Verifica o usuario e cria o menu
        String nome = this.view.getTxtUsuario().getText();
        String senha = this.view.getTxtSenha().getText();
        Usuario usuario = new Usuario(nome, senha);
        
        //Faz a conexão com o Banco de dados e verifica se o usuario esta cadastrado
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        boolean validarUsuario = usuarioDAO.validarUsuario(usuario);
        
        if(validarUsuario){
            ContaDAO contaNoBanco = new ContaDAO(connection, usuario);
            Conta conta = contaNoBanco.trazerContaDoBanco();
            MenuPrincipal menu = new MenuPrincipal();
            usuario = usuarioDAO.recarregarUsuario(usuario.getNome());
            menu.trazerUsuario(usuario);
            menu.trazerConta(conta);
            menu.setVisible(true);
            this.view.dispose();
        }else{
            JOptionPane.showMessageDialog(view, "Usuário ou Senha inválido.");
            this.view.getTxtUsuario().setText("");
            this.view.getTxtSenha().setText("");
            this.view.getTxtUsuario().requestFocus(); //para selecionar o campo usuairo
        }
    }
    
    public void voltarLoginPrincipal(){
        LoginPrincipal login = new LoginPrincipal();
        login.setVisible(true);
        this.view.dispose();
    }
}
