package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    Connection conexao;

    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void inserir(Usuario usuario) throws SQLException{
        
        //Verificando se já existe o mesmo usuario no banco
        boolean resultadoPesquisa = validarUsuarioPorNome(usuario);
        
        //Inserindo no bannco de dados
        if(resultadoPesquisa){
            JOptionPane.showMessageDialog(null, "Usuário já existente no banco de dados.\nTente outro nome.");
        }else{
            // Insere o usuário no banco
            String sql = "insert into usuarios(nome, senha) values (?,?);";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.execute();
            
            //Busca o ID que o banco setou no novo usuário
            String sql2 = "select id from usuarios where nome = ?;";
            PreparedStatement statement2 = conexao.prepareStatement(sql2);
            statement2.setString(1, usuario.getNome());
            statement2.execute();
            
            ResultSet resultSet2 = statement2.getResultSet();
            
            while(resultSet2.next()){
                int idUsuario = resultSet2.getInt("id"); //captura o ID do banco de dados
                // Criando uma nova conta e associando a mesma ao novo usuario no banco de dados
                ContaDAO contaDao = new ContaDAO(conexao, usuario);
                contaDao.criarConta(idUsuario);
            }
            JOptionPane.showMessageDialog(null, "Usuário Cadastrado com sucesso.");
        }
        
    }
    
    public boolean validarUsuario(Usuario usuario) throws SQLException{
        try {
            String sql = "select * from usuarios where nome = ? and senha = ?;";
            PreparedStatement statement = conexao.prepareStatement(sql);
            
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.execute();
        
            ResultSet resultSet = statement.getResultSet();
            
            return resultSet.next(); //Ira varrer o resultado do select caso o select dê algum resultado retorna TRUE
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean validarUsuarioPorNome(Usuario usuario) throws SQLException{
        String sql = "select * from usuarios where nome = ?;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        
        statement.setString(1, usuario.getNome());
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        return resultSet.next();
    }
    
    public ArrayList<Usuario> selectAll() throws SQLException{
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        String sql = "select c.id, u.nome, u.senha, c.saldo from usuarios as u"
                + " inner join contas as c on u.id = c.id_usuario;";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        
        while (resultSet.next()){
            int id       = resultSet.getInt("id");      //retorna o valor contido no atributo id do Banco
            String nome  = resultSet.getString("nome"); // idem
            String senha = resultSet.getString("senha");// idem
            double saldo = resultSet.getDouble("saldo");// idem
            Usuario user = new Usuario(nome, senha, id, saldo);
            
            usuarios.add(user);
        }
        return usuarios;
    }
    
    public void delete(Usuario usuario){
        
            String sql = "select * from usuarios where nome = ? and senha = ?;";
            try{
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getSenha());
                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                boolean aux = false;
                while (resultSet.next()){
                    int id       = resultSet.getInt("id");      //retorna o valor contido no atributo id do Banco
                    String sql3 = "select saldo from contas where id_usuario = ?;";
                    PreparedStatement statement3 = conexao.prepareStatement(sql3);
                    statement3.setInt(1, id);
                    statement3.execute();
                    ResultSet resultSet3 = statement3.getResultSet();
                    while(resultSet3.next()){
                        double saldo = resultSet3.getDouble("saldo");
                        if (saldo == 0){
                            String sql2 = "delete from usuarios where id = ?;";
                            PreparedStatement statement2 = conexao.prepareStatement(sql2);
                            statement2.setInt(1, id);
                            statement2.execute();
                            ContaDAO contaDao = new ContaDAO(conexao);
                            contaDao.deleteConta(id);
                            JOptionPane.showMessageDialog(null,"Usuário Deletado.");
                            aux = true;
                        }else{
                            JOptionPane.showMessageDialog(null,"Saque todo o valor disponível em conta antes de deletar.\n"
                                    + "Saldo disponível: " + saldo);
                            aux = true;
                        }
                    }
                }
                if(!aux){
                    JOptionPane.showMessageDialog(null,"Esse usuário não existe no banco de dados.");
                }
            }catch(SQLException e){
                System.out.println(e);
            }
    }

    public boolean update(Usuario usuario, Usuario novoUsuario) {
        try {
            boolean validarNovoUsuario =  validarUsuarioPorNome(novoUsuario);
            if (validarNovoUsuario){
                JOptionPane.showMessageDialog(null, "Já temos um usuário com esse nome.\n Por favor, tente outro nome.");
                return false;
            }else{
                String sql = "select * from usuarios where nome = ? and senha = ?;";
                PreparedStatement statement = conexao.prepareStatement(sql);
            
                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getSenha());
                statement.execute();
        
                ResultSet resultSet = statement.getResultSet();
            
            if (resultSet.next()){
                int id       = resultSet.getInt("id");      //retorna o valor contido no atributo id do Banco
                String nome  = resultSet.getString("nome"); // idem
                String senha = resultSet.getString("senha");// idem
                
                String sql2 = "update usuarios set nome = ?, senha = ? where id = ?;";
                PreparedStatement statement2 = conexao.prepareStatement(sql2);
            
                statement2.setString(1, novoUsuario.getNome());
                statement2.setString(2, novoUsuario.getSenha());
                statement2.setInt(3, id);
                statement2.execute();
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso.\nVerifique na lista de usuários.");
                }
            }
        } catch (SQLException e) {
        }
        return true;
    }
    
    public String selectEmail(String nome){
        
        //Como o software foi recebendo nossas funcionalidades não estava previsto conter email.
        //Então esse método busca informações inputadas no DB
        //Usado no ControllerExtrato
        String email = null;
        String sql = "select email from usuarios where nome = ?";
        
        try {
            PreparedStatement statment = this.conexao.prepareStatement(sql);
            statment.setString(1, nome);
            statment.executeQuery();
            
            ResultSet resultSet = statment.getResultSet();
            
            while(resultSet.next()){
                email = resultSet.getString("email");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:" + e);
        }
        return email;
    }
}
