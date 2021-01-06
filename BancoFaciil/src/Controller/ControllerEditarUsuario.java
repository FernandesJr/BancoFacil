package Controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import View.EditarUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControllerEditarUsuario {
    
    EditarUsuario view;

    public ControllerEditarUsuario(EditarUsuario view) {
        this.view = view;
    }
    
    public void voltar(){
        this.view.dispose();
        ControllerMenuAdm menu = this.view.getMenu();
        menu.desbloquear();
    }

    public void editar(Usuario usuario) throws SQLException {
        String nome  = this.view.getTxtNovoNome().getText();
        String senha = this.view.getTxtNovaSenha().getText();
        Usuario novoUsuario = new Usuario(nome,senha);
        
        
        boolean confirma = this.confirma("Deseja concluir a edição?");
        
        if(confirma){
            Conexao conexao = new Conexao();
            Connection connection = conexao.getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(connection);
            boolean atualizado = usuarioDao.update(usuario, novoUsuario);
            if (atualizado){
                this.view.getMenu().desbloquear();
                this.view.dispose();
            }
        }else{
            JOptionPane.showMessageDialog(view, "Operação cancelada.");
        }
    }
    
    private boolean confirma(String msg){
        int confirmar = JOptionPane.showConfirmDialog(view, msg); //retorna 0 p/SIM e 1 p/NAO
        return confirmar == 0;
    }
}
