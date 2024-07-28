package DAO;

import DB.Conexao;
import Model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
        
    private static final String INSERIR_CATEGORIA = "INSERT INTO CATEGORIA(DESCRICAO) VALUES(?)";
    
    private static final String SELECT_CATEGORIA = "SELECT * FROM CATEGORIA ORDER BY ID_CATEGORIA DESC";

    private static final String SELECT_CATEGORIA_POR_ID = "SELECT * FROM CATEGORIA WHERE ID_CATEGORIA = ?";
    
    private static final String SELECT_CATEGORIA_POR_CATEGORIA = "SELECT * FROM CATEGORIA WHERE UPPER(DESCRICAO) LIKE UPPER(?) ORDER BY ID_CATEGORIA DESC";

    private static final String DELETE_CATEGORIA = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA = ?";
    
    private static final String ALTERAR_CATEGORIA = "UPDATE CATEGORIA SET DESCRICAO = ? WHERE ID_CATEGORIA = ?";
    
    public List<Categoria> select() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Categoria> categorias = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_CATEGORIA);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Categoria categoria = new Categoria();
                
                categoria.setId(rs.getInt("ID_CATEGORIA"));
                categoria.setDescricao(rs.getString("DESCRICAO"));
                
                categorias.add(categoria);
            }
            
            return categorias;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Categoria> selectByCategoria(String descricao) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Categoria> categorias = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_CATEGORIA_POR_CATEGORIA);
            ps.setString(1, "%" + descricao + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Categoria categoria = new Categoria();
                
                categoria.setId(rs.getInt("ID_CATEGORIA"));
                categoria.setDescricao(rs.getString("DESCRICAO"));
                
                categorias.add(categoria);
            }
            
            return categorias;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Categoria selectById(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Categoria categoria = new Categoria();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_CATEGORIA_POR_ID);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){                
                categoria.setId(rs.getInt("ID_CATEGORIA"));
                categoria.setDescricao(rs.getString("DESCRICAO"));
            }
            
            return categoria;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void insert(String categoria) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{            
            ps = conexao.prepareStatement(INSERIR_CATEGORIA);
            
            ps.setString(1, categoria);
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
    
    public void delete(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(DELETE_CATEGORIA);
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
   
    public void update(Categoria categoria) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
           conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(ALTERAR_CATEGORIA);
            
            ps.setString(1, categoria.getDescricao());
            ps.setInt(2, categoria.getId());
            
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
}
