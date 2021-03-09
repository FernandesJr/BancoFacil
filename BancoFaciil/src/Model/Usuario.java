package Model;

import DAO.Conexao;
import DAO.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;

public class Usuario {
    
    private String nome;
    private String senha;
    private int id;
    private double saldo;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String nome, String senha, int id) {
        this.nome = nome;
        this.senha = senha;
        this.id = id;
    }

    public Usuario(String nome, String senha, int id, double saldo) {
        this.nome = nome;
        this.senha = senha;
        this.id = id;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public String getEmailDB() throws SQLException{
        
        //Busca o email no cadastrado DB
        Conexao conexao = new Conexao();
        Connection com = conexao.getConnection();
        UsuarioDAO userDao = new UsuarioDAO(com);
        return userDao.selectEmail(this.nome);
    }
    
}
