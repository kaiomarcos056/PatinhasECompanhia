package DAO;

import DB.Conexao;
import Model.Favorito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritoDAO {
    private static final String INSERIR_FAVORITO = "INSERT INTO FAVORITO(ID_USUARIO, ID_PRODUTO) VALUES(?, ?)";
    
    private static final String SELECT_POR_USUARIO = "SELECT * FROM FAVORITO WHERE ID_USUARIO = ?";
    
    private static final String DELETE_POR_USUARIO_PRODUTO = "DELETE FROM FAVORITO WHERE ID_USUARIO = ? AND ID_PRODUTO = ?";
    
    public void insert(Favorito favorito) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{            
            ps = conexao.prepareStatement(INSERIR_FAVORITO);
            
            ps.setInt(1, favorito.getIdUsuario());
            ps.setInt(2, favorito.getIdProduto());
            
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
    
    public List<Favorito> selectByUsuario(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Favorito> favoritos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_POR_USUARIO);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Favorito favorito = new Favorito();
                
                favorito.setIdFavorito(rs.getInt("ID_FAVORITO"));
                favorito.setIdUsuario(rs.getInt("ID_USUARIO"));
                favorito.setIdProduto(rs.getInt("ID_PRODUTO"));
                
                favoritos.add(favorito);
            }
            
            return favoritos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void deleteByUsuarioAndProduto(int idUsuario, int idProduto) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(DELETE_POR_USUARIO_PRODUTO);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProduto);
            
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
