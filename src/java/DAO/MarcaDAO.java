package DAO;

import DB.Conexao;
import Model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO {
    
    private static final String INSERIR_MARCA = "INSERT INTO MARCA(DESCRICAO) VALUES(?)";
    
    private static final String SELECT_MARCA = "SELECT * FROM MARCA ORDER BY ID_MARCA DESC";

    private static final String SELECT_MARCA_POR_ID = "SELECT * FROM MARCA WHERE ID_MARCA = ?";
    
    private static final String SELECT_MARCA_POR_MARCA = "SELECT * FROM MARCA WHERE UPPER(DESCRICAO) LIKE UPPER(?) ORDER BY ID_MARCA DESC";

    private static final String DELETE_MARCA = "DELETE FROM MARCA WHERE ID_MARCA = ?";
    
    private static final String ALTERAR_MARCA = "UPDATE MARCA SET DESCRICAO = ? WHERE ID_MARCA = ?";
    
    public List<Marca> select() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Marca> marcas = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_MARCA);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Marca marca = new Marca();
                
                marca.setId(rs.getInt("ID_MARCA"));
                marca.setDescricao(rs.getString("DESCRICAO"));
                
                marcas.add(marca);
            }
            
            return marcas;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Marca> selectByMarca(String descricao) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Marca> marcas = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_MARCA_POR_MARCA);
            ps.setString(1, "%" + descricao + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Marca marca = new Marca();
                
                marca.setId(rs.getInt("ID_MARCA"));
                marca.setDescricao(rs.getString("DESCRICAO"));
                
                marcas.add(marca);
            }
            
            return marcas;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Marca selectById(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Marca marca = new Marca();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_MARCA_POR_ID);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){                
                marca.setId(rs.getInt("ID_MARCA"));
                marca.setDescricao(rs.getString("DESCRICAO"));
            }
            
            return marca;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void insert(String marca) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{            
            ps = conexao.prepareStatement(INSERIR_MARCA);
            
            ps.setString(1, marca);
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
            
            ps = conexao.prepareStatement(DELETE_MARCA);
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
   
    public void update(Marca marca) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
           conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(ALTERAR_MARCA);
            
            ps.setString(1, marca.getDescricao());
            ps.setInt(2, marca.getId());
            
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