package Controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import View.ListaUsuarios;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ControllerListaUsuarios {
    
    ListaUsuarios view;

    public ControllerListaUsuarios(ListaUsuarios view) {
        this.view = view;
    }
    
    public void voltarMenu(ControllerMenuAdm menu){
        menu.desbloquear();
        this.view.dispose();
    }
    
    public void MostraUsuarios() throws SQLException{
        
        Conexao conexao = new Conexao();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao.getConnection());
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        
        listaUsuarios = usuarioDao.selectAll();
        
        for (Usuario user: listaUsuarios){
            int id = user.getId();
            String nome = user.getNome();
            String senha = user.getSenha();
            double saldo = user.getSaldo();
            
            DecimalFormat valorf = new DecimalFormat("###,###.00");
            String valorFormatado = valorf.format(saldo);
            
            this.view.getjTextPaneListaID().setText(view.getjTextPaneListaID().getText() + "\n" + id);
            this.view.getjTextPaneListaNOME().setText(view.getjTextPaneListaNOME().getText() + "\n" + nome);
            this.view.getjTextPaneListaSENHA().setText(view.getjTextPaneListaSENHA().getText() + "\n" + senha);
            this.view.getjTextPaneListaSaldo().setText(view.getjTextPaneListaSaldo().getText() + "\n" + valorFormatado);
        }
    }
}
