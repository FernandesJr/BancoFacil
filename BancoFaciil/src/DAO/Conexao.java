package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    Connection conexao = null;
    
    public Connection getConnection() throws SQLException{
        
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancofacil", "root", "");
            System.out.println("conectado");
        } catch (SQLException e){
            System.out.println("Erro:");
            JOptionPane.showMessageDialog(null, "Sem conexão com o banco de dados.");
       }
        return conexao;
    }
}
