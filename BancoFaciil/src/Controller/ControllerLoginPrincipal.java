package Controller;

import View.LoginPrincipal;
import View.Login;
import View.LoginAdm;

public class ControllerLoginPrincipal {
    private final LoginPrincipal telaLogin;

    public ControllerLoginPrincipal(LoginPrincipal login) {
        this.telaLogin = login;
    }
    
    public void navegarParaLoginUser(){
        Login loginUser = new Login();
        loginUser.setVisible(true);
        this.telaLogin.dispose();
    }
    
    public void navegarParaLoginAdm(){
        LoginAdm loginAdm = new LoginAdm();
        loginAdm.setVisible(true);
        this.telaLogin.dispose();
    }
}
