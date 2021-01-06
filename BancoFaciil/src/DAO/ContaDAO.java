package DAO;

import Model.Conta;
import Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ContaDAO {
    
    Connection conexao;
    Usuario usuario;

    public ContaDAO(Connection conexao, Usuario usuario) {
        this.conexao = conexao;
        this.usuario = usuario;
    }

    public ContaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public Conta trazerContaDoBanco() throws SQLException{
        
        Conta conta = new Conta(0);
        String sql = "select saldo, id from contas where id_usuario = (select id from usuarios where nome = ?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        while (resultSet.next()){
            double saldo = resultSet.getDouble("saldo");
            int id       = resultSet.getInt("id");
            conta.setSaldo(saldo);
            conta.setId(id);
        }
        return conta;
    }

    public void atualizarSaldo(double valor) throws SQLException {
        
        String sql = "update contas set saldo = ? where id_usuario = (select id from usuarios where nome = ?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setDouble(1, valor);
        statement.setString(2, usuario.getNome());
        statement.execute();
    }
    
    public void criarConta(int id_usuario) throws SQLException{
        
        String sql = "insert into contas(saldo, id_usuario) values (0,?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, id_usuario);
        statement.execute();
    }
    
    public void addMovimentação(String tipo, String valor) throws SQLException{
        
       String sql2  = "select id from contas where id_usuario = (select id from usuarios where nome = ?);";
       PreparedStatement statement2 = conexao.prepareCall(sql2);
       statement2.setString(1, usuario.getNome());
       statement2.execute();
       ResultSet resultset = statement2.getResultSet();
       while(resultset.next()){
            int id_conta = resultset.getInt("id");
            String sql = "insert into movimentacoes (id_conta, data, hora, tipo, valor) "
                    + "values (?, current_date(), current_time(), ?,?);"; 
            PreparedStatement statement = conexao.prepareCall(sql);
            statement.setInt(1, id_conta);
            statement.setString(2, tipo);
            statement.setString(3, valor);
            statement.execute();
       }
    }

    public ArrayList<String> extrato() throws SQLException {
        
        ArrayList<String> mov = new ArrayList();
        String sql  = "select id from contas where id_usuario = (select id from usuarios where nome = ?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()){
            
            int id_conta = resultSet.getInt("id");
            String sql2 = "select * from movimentacoes where id_conta = ? order by id;";
            PreparedStatement statement2 = conexao.prepareStatement(sql2);
            statement2.setInt(1, id_conta);
            statement2.execute();
            ResultSet resultset2 = statement2.getResultSet();
            while(resultset2.next()){
                String tipo  = resultset2.getString("tipo");
                String valor = resultset2.getString("valor");
                Date   data  = resultset2.getDate("data");
                Time   hora  = resultset2.getTime("hora");
                SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = formatada.format(data);
                mov.add(tipo + valor + "\n        " + dataFormatada + " " + hora);
            }
        }
        return mov;
    }
    
    public ArrayList<String> extrato(String dataIni, String dataFim) throws SQLException {
        
        ArrayList<String> mov = new ArrayList();
        String sql  = "select id from contas where id_usuario = (select id from usuarios where nome = ?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()){
            
            int id_conta = resultSet.getInt("id");
            String sql2 = "select * from movimentacoes where id_conta = ? and data between ? and ? order by id;";
            PreparedStatement statement2 = conexao.prepareStatement(sql2);
            statement2.setInt(1, id_conta);
            statement2.setString(2, dataIni);
            statement2.setString(3, dataFim);
            statement2.execute();
            ResultSet resultset2 = statement2.getResultSet();
            while(resultset2.next()){
                String tipo  = resultset2.getString("tipo");
                String valor = resultset2.getString("valor");
                Date   data  = resultset2.getDate("data");
                Time   hora  = resultset2.getTime("hora");
                SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = formatada.format(data);
                mov.add(tipo + valor + "\n        " + dataFormatada + " " + hora);
            }
        }
        return mov;
    }

    public void deleteConta(int idUsuario) throws SQLException {
        
        String sql = "delete from contas where id_usuario = ?;";
        PreparedStatement statement2 = conexao.prepareStatement(sql);
        statement2.setInt(1, idUsuario);
        statement2.execute();
    }
    
    public String validarConta(String id) throws SQLException{
        
        String sql = "select nome from usuarios where id = (select id_usuario from contas where id = ?);";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, id);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        String nome = "CONTA NÃO ENCONTRADA.";
        while(resultSet.next()){
            nome = resultSet.getString("nome");
        }
        return nome;
    }

    public boolean atualizarSaldo(double valor, String id_conta) throws SQLException {
        
        String sql = "select saldo from contas where id = ?;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, id_conta);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            Double saldo = resultSet.getDouble("saldo");
            saldo = saldo + valor;
            String sql2 = "update contas set saldo = ? where id = ?;";
            PreparedStatement statement2 = conexao.prepareStatement(sql2);
            statement2.setDouble(1, saldo);
            statement2.setString(2, id_conta);
            statement2.execute();
            return true;
        }
        return false;
    }
    
    public void addmovimentacaoTransferencia(String id_conta, String tipo, String valor) throws SQLException{
        
        String sql = "insert into movimentacoes (id_conta, data, hora, tipo, valor) "
                    + "values (?, current_date(), current_time(), ?,?);"; 
        PreparedStatement statement = conexao.prepareCall(sql);
        statement.setString(1, id_conta);
        statement.setString(2, tipo);
        statement.setString(3, valor);
        statement.execute();
    }

    public Double saldoGeral() throws SQLException {
        String sql = "select sum(saldo) as saldo_geral from contas;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            return resultSet.getDouble("saldo_geral");
        }
        return 0.0;
    }
}
