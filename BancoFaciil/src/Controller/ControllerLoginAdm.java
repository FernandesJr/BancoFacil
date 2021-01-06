package Controller;

import View.LoginAdm;
import View.LoginPrincipal;
import View.MenuAdm;
import javax.swing.JOptionPane;

public class ControllerLoginAdm {
    
    LoginAdm view;

    public ControllerLoginAdm(LoginAdm view) {
        this.view = view;
    }
    
    public void voltar(){
        LoginPrincipal login = new LoginPrincipal();
        login.setVisible(true);
        this.view.dispose();
    }
    
    public void navegarParaMenu(){
        if (view.getjPasswordFieldSenha().getText().equals("")){
            MenuAdm menu = new MenuAdm();
            menu.setVisible(true);
            this.view.dispose();
        }else{
            JOptionPane.showMessageDialog(view, "Senha inválida");
            this.view.getjPasswordFieldSenha().setText("");
        }
    }
}
