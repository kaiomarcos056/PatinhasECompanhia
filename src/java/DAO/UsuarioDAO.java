package DAO;

import Model.Usuario; // IMPORTANDO CLASSE USUARIO
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

public class UsuarioDAO {
    
    // CONSTANTES PRIVADAS COM COMANDOS SQL SETADOS
    
    // QUERY INSERT
    private static final String INSERIR_USUARIO = "INSERT INTO USUARIO(NOME, ENDERECO, EMAIL, SENHA, ADMINISTRADOR) "
            + "VALUES(?, ?, ?, ?, ?)";
    
    // QUERY SELECT
    private static final String SELECT_TODOS_USUARIOS = "SELECT * FROM USUARIO";
    
    // QUERY SELECT
    private static final String SELECT_USUARIO_POR_ID = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?";
    
    // QUERY SELECT
    private static final String SELECT_USUARIO_LOGIN = "SELECT * FROM USUARIO WHERE UPPER(EMAIL) = UPPER(?) AND UPPER(SENHA) = UPPER(?)";
    
    // QUERY DELETE
    private static final String DELETE_USUARIO = "DELET FROM USUARIO WHERE ID = ?";
    
    // QUERY UPDATE
    private static final String ALTERAR_USUARIO = "UPDATE USUARIO "
            + "SET NOME = ?, ENDERECO = ?, EMAIL = ?, SENHA = ?, ADMINISTRADOR = ? WHERE ID_USUARIO = ?";
    
    // AÇÕES DE INTERAÇÃO COM O BANCO DE DADOS
    
    // SELECT LOGIN
    public Usuario login(String email, String senha) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        Usuario usu = new Usuario();
        
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/patinhas", "postgres", "root");
            
            ps = con.prepareStatement(SELECT_USUARIO_LOGIN);
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usu.setId(rs.getInt("ID_USUARIO"));
                usu.setEndereco(rs.getString("ENDERECO"));
                usu.setNome(rs.getString("NOME"));
                usu.setAdministrador(rs.getBoolean("ADMINISTRADOR"));
                usu.setEmail(rs.getString("EMAIL"));
                usu.setSenha(rs.getString("SENHA"));
            }
            System.out.println("EMAIL: "+usu.getEmail());
            return usu;
        }
        catch(SQLException e){
            System.err.println(e);
        }
        finally {
            ps.close();
            con.close();
        }
        
        return null;
    }
    
    // SELECT USUARIO
    public Usuario pegarUsuario(Integer id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        Usuario usu = new Usuario();
        
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/patinhas", "postgres", "root");
            
            ps = con.prepareStatement(SELECT_USUARIO_POR_ID);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usu.setId(rs.getInt("ID_USUARIO"));
                usu.setEndereco(rs.getString("ENDERECO"));
                usu.setNome(rs.getString("NOME"));
                usu.setAdministrador(rs.getBoolean("ADMINISTRADOR"));
                usu.setEmail(rs.getString("EMAIL"));
                usu.setSenha(rs.getString("SENHA"));
            }
            return usu;
        }
        catch(SQLException e){
            System.err.println(e);
        }
        finally {
            ps.close();
            con.close();
        }
        
        return null;
    }

    
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
    
    
    // UPDATE
    public boolean alterarUsuario(Usuario usu) throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/patinhas", "postgres", "root");
           
            ps = con.prepareStatement(ALTERAR_USUARIO);
            ps.setString(1, usu.getNome());
            ps.setString(2, usu.getEndereco());
            ps.setString(3, usu.getEmail());
            ps.setString(4, usu.getSenha());
            ps.setBoolean(5, usu.isAdministrador());
            ps.setInt(6, usu.getId());
            
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
