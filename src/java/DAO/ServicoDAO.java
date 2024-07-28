package DAO;

import DB.Conexao;
import Model.Produto;
import Model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    
    private static final String SELECT_SERVICO = "SELECT \n" +
"    PROD.ID_PRODUTO, \n" +
"    PROD.NOME, \n" +
"    PROD.DESCRICAO, \n" +
"    PROD.ID_ESPECIE, \n" +
"    ESP.DESCRICAO AS DESC_ESPECIE, \n" +
"    PROD.TAMANHO, \n" +
"    PROD.VALOR, \n" +
"    PROD.NOME_FOTO\n" +
"FROM PRODUTO PROD \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE \n" +
"WHERE SERVICO = TRUE " +
"ORDER BY PROD.ID_PRODUTO DESC";
    
        private static final String SELECT_SERVICO_POR_NOME = "SELECT \n" +
"    PROD.ID_PRODUTO, \n" +
"    PROD.NOME, \n" +
"    PROD.DESCRICAO, \n" +
"    PROD.ID_ESPECIE, \n" +
"    ESP.DESCRICAO AS DESC_ESPECIE, \n" +
"    PROD.TAMANHO, \n" +
"    PROD.VALOR, \n" +
"    PROD.NOME_FOTO\n" +
"FROM PRODUTO PROD \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE \n" +
"WHERE SERVICO = TRUE " + 
"AND UPPER(PROD.NOME) LIKE UPPER(?) " +
"ORDER BY PROD.ID_PRODUTO DESC";
        
    private static final String SELECT_SERVICO_POR_ID = "SELECT \n" +
"    PROD.ID_PRODUTO, \n" +
"    PROD.NOME, \n" +
"    PROD.DESCRICAO, \n" +
"    PROD.ID_ESPECIE, \n" +
"    ESP.DESCRICAO AS DESC_ESPECIE, \n" +
"    PROD.TAMANHO, \n" +
"    PROD.VALOR, \n" +
"    PROD.NOME_FOTO\n" +
"FROM PRODUTO PROD \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE \n" +
"WHERE SERVICO = TRUE " + 
"AND PROD.ID_PRODUTO = ? " +
"ORDER BY PROD.ID_PRODUTO DESC";
        
    private static final String INSERIR_SERVICO = "INSERT INTO PRODUTO(NOME, DESCRICAO, ID_ESPECIE, TAMANHO, VALOR, NOME_FOTO, SERVICO) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?)";
    
    private static final String ALTERAR_SERVICO = "UPDATE PRODUTO SET NOME = ?, DESCRICAO = ?, "
            + "ID_ESPECIE = ?, TAMANHO = ?, VALOR = ?, NOME_FOTO = ? WHERE ID_PRODUTO = ? ";
    
    private static final String DELETE_SERVICO = "DELETE FROM PRODUTO WHERE ID_PRODUTO = ?";
    
    public List<Servico> select() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Servico> servicos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_SERVICO);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Servico servico = new Servico();
                
                servico.setId(rs.getInt("ID_PRODUTO"));
                servico.setNome(rs.getString("NOME"));
                servico.setDescricao(rs.getString("DESCRICAO"));
                servico.setIdEspecie(rs.getInt("ID_ESPECIE"));
                servico.setEspecie(rs.getString("DESC_ESPECIE"));
                servico.setTamanho(rs.getString("TAMANHO"));
                servico.setValor(rs.getDouble("VALOR"));
                servico.setFoto(rs.getString("NOME_FOTO"));
                
                servicos.add(servico);
            }
            
            return servicos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Servico> selectByServico(String nome) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Servico> servicos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_SERVICO_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Servico servico = new Servico();
                
                servico.setId(rs.getInt("ID_PRODUTO"));
                servico.setNome(rs.getString("NOME"));
                servico.setDescricao(rs.getString("DESCRICAO"));
                servico.setIdEspecie(rs.getInt("ID_ESPECIE"));
                servico.setEspecie(rs.getString("DESC_ESPECIE"));
                servico.setTamanho(rs.getString("TAMANHO"));
                servico.setValor(rs.getDouble("VALOR"));
                servico.setFoto(rs.getString("NOME_FOTO"));
                
                servicos.add(servico);
            }
            
            return servicos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Servico selectById(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Servico servico = new Servico();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_SERVICO_POR_ID);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                servico.setId(rs.getInt("ID_PRODUTO"));
                servico.setNome(rs.getString("NOME"));
                servico.setDescricao(rs.getString("DESCRICAO"));
                servico.setIdEspecie(rs.getInt("ID_ESPECIE"));
                servico.setEspecie(rs.getString("DESC_ESPECIE"));
                servico.setTamanho(rs.getString("TAMANHO"));
                servico.setValor(rs.getDouble("VALOR"));
                servico.setFoto(rs.getString("NOME_FOTO"));
            }
            
            return servico;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void insert(Servico servico) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{            
            ps = conexao.prepareStatement(INSERIR_SERVICO);
            
            ps.setString(1, servico.getNome());
            ps.setString(2, servico.getDescricao());
            ps.setInt(3, servico.getIdEspecie());
            ps.setString(4, servico.getTamanho());
            ps.setDouble(5, servico.getValor());
            ps.setString(6, servico.getFoto());
            ps.setBoolean(7, true);
            
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
            
            ps = conexao.prepareStatement(DELETE_SERVICO);
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
    
    public void update(Servico servico) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
           conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(ALTERAR_SERVICO);
            
            ps.setString(1, servico.getNome());
            ps.setString(2, servico.getDescricao());
            ps.setInt(3, servico.getIdEspecie());
            ps.setString(4, servico.getTamanho());
            ps.setDouble(5, servico.getValor());
            ps.setString(6, servico.getFoto());
            ps.setInt(7, servico.getId());
            
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
