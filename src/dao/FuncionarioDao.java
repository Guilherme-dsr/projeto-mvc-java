package dao;


import bean.FuncionarioBean;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FuncionarioDao {
        
    public void create(FuncionarioBean b){ //Função para salvar os dados
        Connection con = ConnectionFactory.getConnection(); //Variável que irá receber a conexão
        PreparedStatement stmt = null; //Variável que irá guardar o comando SQL
        
        try {
            stmt = con.prepareStatement("INSERT INTO funcionario(id,nome,username,email,cidade,senha) VALUES (?,?,?,?,?,?)"); //Varável stmt irá receber os comando SQL
            stmt.setInt(1, b.getCod());
            stmt.setString(2, b.getNome()); //Enviar o nome do usuário para o banco
            stmt.setString(3,b.getUsername()); //Enviar a descrição para o banco
            stmt.setString(4, b.getEmail()); //Enviar...
            stmt.setString(5, b.getCidade());//Enviar...
            stmt.setInt(6, b.getSenha());
            
            stmt.executeUpdate();//Executar o comando
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso"); // Exibir mensagem caso tudo funcione
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void Update(FuncionarioBean b){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE funcionario SET nome = ?, username = ?,email = ?,cidade = ?, senha = ? WHERE cod = ? ");
            stmt.setString(1, b.getNome()); //Enviar o nome do usuário para o banco
            stmt.setString(2,b.getUsername()); //Enviar a descrição para o banco
            stmt.setString(3, b.getEmail()); //Enviar...
            stmt.setString(4, b.getCidade());//Enviar...
            stmt.setInt(5, b.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Atualizado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Atualizar.." + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    
}
    public void delete(FuncionarioBean b){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE cod = ?");
            stmt.setInt(1, b.getCod());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluido com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir.." + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}


