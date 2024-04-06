package DAO;

import Model.Usuario; // IMPORTANDO CLASSE USUARIO
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    
    // CONSTANTES PRIVADAS COM COMANDOS SQL SETADOS
    private static final String INSERIR_USUARIO = "INSERT INTO USUARIO(NOME, ENDERECO, EMAIL, SENHA, ADMINISTRADOR) "
            + "VALUES(?, ?, ?, ?, ?)";
    
    private static final String SELECT_TODOS_USUARIOS = "SELECT * FROM USUARIO";
    
    private static final String SELECT_USUARIO_POR_ID = "SELECT * FROM USUARIO WHERE ID = ?";
    
    private static final String DELETE_USUARIO = "DELET FROM USUARIO WHERE ID = ?";
    
    private static final String ALTERAR_USUARIO = "UPDATE USUARIO "
            + "SET NOME = ?, ENDERECO = ?, EMAIL = ?, SENHA = ?, ADMINISTRADOR = ? WHERE ID = ?";
    
    // AÇÕES DE INTERAÇÃO COM O BANCO DE DADOS
    
    // INSERT
    public boolean cadastrarUsuario(Usuario usu) throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/patinhas", "postgres", "root");
            
            ps = con.prepareStatement(INSERIR_USUARIO);
            ps.setString(1, usu.getNome());
            ps.setString(2, usu.getEndereco());
            ps.setString(3, usu.getEmail());
            ps.setString(4, usu.getSenha());
            ps.setBoolean(5, usu.isAdministrador());
            
            return ps.executeUpdate() > 0;
        }
        catch(SQLException e){
            System.err.println(e);
        }
        finally {
            ps.close();
            con.close();
        }
        
        return false;
    }
}
