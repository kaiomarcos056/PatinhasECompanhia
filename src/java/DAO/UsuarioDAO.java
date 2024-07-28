package DAO;

import DB.Conexao;
import Model.Usuario;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    private static final String SELECT_USUARIO = "SELECT * FROM USUARIO ORDER BY ID_USUARIO DESC";
    
    private static final String SELECT_LOGIN = "SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = ?";
    
    private static final String SELECT_USUARIO_POR_ID = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?";
    
    private static final String SELECT_USUARIO_POR_NOME= "SELECT * FROM USUARIO WHERE UPPER(NOME) LIKE UPPER(?) ORDER BY ID_USUARIO DESC";
    
    private static final String INSERIR_USUARIO = "INSERT INTO USUARIO(NOME, ENDERECO, EMAIL, SENHA, ADMINISTRADOR) VALUES(?, ?, ?, ?, ?)";
    
    private static final String ALTERAR_USUARIO = "UPDATE USUARIO "
            + "SET NOME = ?, ENDERECO = ?, EMAIL = ?, SENHA = ?, ADMINISTRADOR = ? WHERE ID_USUARIO = ?";
    
    private static final String DELETE_USUARIO = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
    
    public List<Usuario> select() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_USUARIO);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setEndereco(rs.getString("ENDERECO"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setAdministrador(rs.getBoolean("ADMINISTRADOR"));
                
                usuarios.add(usuario);
            }
            
            return usuarios;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Usuario selectByLogin(String email, String senha) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Usuario usuario = null;
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_LOGIN);
            
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                usuario = new Usuario();
                
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setEndereco(rs.getString("ENDERECO"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setAdministrador(rs.getBoolean("ADMINISTRADOR"));
            }
            
            return usuario;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Usuario selectById(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Usuario usuario = null;
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_USUARIO_POR_ID);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                usuario = new Usuario();
                
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setEndereco(rs.getString("ENDERECO"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setAdministrador(rs.getBoolean("ADMINISTRADOR"));
            }
            
            return usuario;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Usuario> selectByNome(String nome) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_USUARIO_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNome(rs.getString("NOME"));
                usuario.setEndereco(rs.getString("ENDERECO"));
                usuario.setEmail(rs.getString("EMAIL"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setAdministrador(rs.getBoolean("ADMINISTRADOR"));
                
                usuarios.add(usuario);
            }
            
            return usuarios;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void insert(Usuario usuario) throws SQLException{
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{            
            ps = conexao.prepareStatement(INSERIR_USUARIO);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEndereco());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setBoolean(5, usuario.isAdministrador());
            ps.executeUpdate();
	} 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
	}
        finally{
            ps.close();
            conexao.close();
        }
    }
    
    public void update(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
           conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(ALTERAR_USUARIO);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEndereco());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setBoolean(5, usuario.isAdministrador());
            ps.setInt(6, usuario.getId());
            
            ps.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally{
            conexao.close();
            ps.close();
        }
    }
    
    public void delete(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(DELETE_USUARIO);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally{
            ps.close();
            conexao.close();
        }
    }
    
}
