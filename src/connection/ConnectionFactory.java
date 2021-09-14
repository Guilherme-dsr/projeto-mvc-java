package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ConnectionFactory {
 
    //Declaração de variáveis para caminho da conexão
    private static final String DRIVER = "com.mysql.jdbc.Driver"; //Localização do Driver de banco de dados Java
    private static final String URL = "jdbc:mysql://localhost:3306/epica"; //Url do banco de dados
    private static final String USER = "root"; //Usuário do banco de dados
    private static final String PASS = ""; //Senha do Usuário do banco
    
    public static Connection getConnection(){ //função para criação de conexão do banco com o Java
        try {
            Class.forName(DRIVER); //Classe para ser usado
            return DriverManager.getConnection(URL, USER, PASS); //Acesso ao banco
        } catch (ClassNotFoundException | SQLException ex) { //Caso a conexão falhe
            throw new RuntimeException("Erro na conexão",ex); //Mostra um aviso de erro
        }
    }
    
    public static void closeConnection(Connection con){ //Função para fechar a conexão
        if(con != null){ //caso a conexão não retorne a nada(null)
            try {
                con.close(); //Fecha a conexão
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex); //Não importa
            }
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt){ //Função para fechar a conexão
        
        closeConnection(con); //Fechar a conexão com o banco
        
        if(stmt != null){ //caso a conexão não retorne a nada(null)
            try {
                stmt.close(); //Fecha a conexão com o PreparedStatement
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);//Não importa
            }
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){ //Função para fechar a conexão
        closeConnection(con, stmt);
        
        if(rs != null){ //caso a conexão não retorne a nada(null)
            try {
                rs.close(); //Fecha a conexão com o ResultSet
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);//Não importa
            }
        }
    }
}
