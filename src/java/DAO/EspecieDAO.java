package DAO;

import DB.Conexao;
import Model.Especie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecieDAO {
    
    private static final String INSERIR_ESPECIE = "INSERT INTO ESPECIE(DESCRICAO) VALUES(?)";
    
    private static final String SELECT_ESPECIE = "SELECT * FROM ESPECIE ORDER BY ID_ESPECIE DESC";

    private static final String SELECT_ESPECIE_POR_ID = "SELECT * FROM ESPECIE WHERE ID_ESPECIE = ?";
    
    private static final String SELECT_ESPECIE_POR_ESPECIE = "SELECT * FROM ESPECIE WHERE UPPER(DESCRICAO) LIKE UPPER(?) ORDER BY ID_ESPECIE DESC";

    private static final String DELETE_ESPECIE = "DELETE FROM ESPECIE WHERE ID_ESPECIE = ?";
    
    private static final String ALTERAR_ESPECIE = "UPDATE ESPECIE SET DESCRICAO = ? WHERE ID_ESPECIE = ?";
    
    public List<Especie> select() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Especie> especies = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_ESPECIE);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Especie especie = new Especie();
                
                especie.setId(rs.getInt("ID_ESPECIE"));
                especie.setDescricao(rs.getString("DESCRICAO"));
                
                especies.add(especie);
            }
            
            return especies;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Especie> selectByEspecie(String descricao) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Especie> especies = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_ESPECIE_POR_ESPECIE);
            ps.setString(1, "%" + descricao + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Especie especie = new Especie();
                
                especie.setId(rs.getInt("ID_ESPECIE"));
                especie.setDescricao(rs.getString("DESCRICAO"));
                
                especies.add(especie);
            }
            
            return especies;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Especie selectById(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Especie especie = new Especie();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_ESPECIE_POR_ID);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){                
                especie.setId(rs.getInt("ID_ESPECIE"));
                especie.setDescricao(rs.getString("DESCRICAO"));
            }
            
            return especie;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void insert(String especie) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{            
            ps = conexao.prepareStatement(INSERIR_ESPECIE);
            
            ps.setString(1, especie);
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
            
            ps = conexao.prepareStatement(DELETE_ESPECIE);
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
   
    public void update(Especie especie) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
           conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(ALTERAR_ESPECIE);
            
            ps.setString(1, especie.getDescricao());
            ps.setInt(2, especie.getId());
            
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