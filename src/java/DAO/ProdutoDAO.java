package DAO;

import DB.Conexao;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    private static final String SELECT_PRODUTO = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE "
            + "ORDER BY PROD.ID_PRODUTO DESC";
    
        private static final String SELECT_PRODUTO_DIFERENTE_ZERO = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE "
                + "WHERE PROD.QUANTIDADE > 0"
            + "ORDER BY PROD.ID_PRODUTO DESC";
    
    private static final String SELECT_PRODUTO_POR_NOME = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE "
            + "WHERE UPPER(PROD.NOME) LIKE UPPER(?)"
            + "ORDER BY PROD.ID_PRODUTO DESC";
    
        private static final String SELECT_ALL_PRODUTO_POR_NOME = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE "
            + "WHERE UPPER(PROD.NOME) LIKE UPPER(?)"
            + "ORDER BY PROD.ID_PRODUTO DESC";
    
        private static final String SELECT_PRODUTO_POR_ID = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE "
            + "WHERE PROD.ID_PRODUTO = ?";
    
    private static final String SELECT_PRODUTO_POR_CATEGORIA = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE\n" +
"WHERE 1 = 1\n" +
"AND PROD.ID_CATEGORIA = ?\n "
            + "AND PROD.QUANTIDADE > 0 " +
"ORDER BY PROD.ID_PRODUTO DESC";
    
    private static final String SELECT_PRODUTO_POR_ESPECIE = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE\n" +
"WHERE 1 = 1\n" +
"AND PROD.ID_ESPECIE = ?\n "
            + "AND PROD.QUANTIDADE > 0 " +
"ORDER BY PROD.ID_PRODUTO DESC";
    
        private static final String SELECT_PRODUTO_POR_CATEGORIA_E_ESPECIE = "SELECT\n" +
"	PROD.ID_PRODUTO, \n" +
"	PROD.NOME AS NOME_PROD,\n" +
"	PROD.VALOR,\n" +
"	PROD.QUANTIDADE, \n" +
"	PROD.NOME_FOTO, \n" +
"	PROD.SERVICO, \n" +
"	PROD.ID_MARCA, \n" +
"	MARC.DESCRICAO AS DESC_MARCA,\n" +
"	PROD.ID_CATEGORIA, \n" +
"	CAT.DESCRICAO AS DESC_CATEGORIA, \n" +
"	PROD.ID_ESPECIE, \n" +
"	ESP.DESCRICAO AS DESC_ESPECIE\n" +
"FROM PRODUTO PROD\n" +
"INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PROD.ID_CATEGORIA\n" +
"INNER JOIN MARCA MARC ON MARC.ID_MARCA = PROD.ID_MARCA \n" +
"INNER JOIN ESPECIE ESP ON ESP.ID_ESPECIE = PROD.ID_ESPECIE\n" +
"WHERE 1 = 1\n" +
"AND PROD.ID_CATEGORIA = ?\n" +
"AND PROD.ID_ESPECIE = ?\n AND PROD.QUANTIDADE > 0 " +
"ORDER BY PROD.ID_PRODUTO DESC";
        
    private static final String INSERIR_PRODUTO = "INSERT INTO PRODUTO(NOME, NOME_FOTO, VALOR, QUANTIDADE, ID_MARCA, ID_CATEGORIA, ID_ESPECIE, SERVICO) "
                                                             + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String ALTERAR_PRODUTO = "UPDATE PRODUTO SET NOME = ?, NOME_FOTO = ?, VALOR = ?, QUANTIDADE = ?, ID_MARCA = ?, "
            + "ID_CATEGORIA = ?, ID_ESPECIE = ? WHERE ID_PRODUTO = ? ";
    
    private static final String DELETE_PRODUTO = "DELETE FROM PRODUTO WHERE ID_PRODUTO = ?";
    
    public List<Produto> select() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_PRODUTO_DIFERENTE_ZERO);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
                
                produtos.add(produto);
            }
            
            return produtos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Produto> selectAll() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(SELECT_PRODUTO);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
                
                produtos.add(produto);
            }
            
            return produtos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Produto> selectByCategoria(int idCategoria) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_PRODUTO_POR_CATEGORIA);
            ps.setInt(1, idCategoria);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
                
                produtos.add(produto);
            }
            
            return produtos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Produto> selectByEspecie(int idEspecie) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_PRODUTO_POR_ESPECIE);
            ps.setInt(1, idEspecie);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
                
                produtos.add(produto);
            }
            
            return produtos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Produto> selectByCategoriaEEspecie(int idCategoria, int idEspecie) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_PRODUTO_POR_CATEGORIA_E_ESPECIE);
            ps.setInt(1, idCategoria);
            ps.setInt(2, idEspecie);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
                
                produtos.add(produto);
            }
            
            return produtos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Produto> selectByProduto(String nome) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_PRODUTO_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
                
                produtos.add(produto);
            }
            
            return produtos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Produto> selectByAllProduto(String nome) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_ALL_PRODUTO_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
                
                produtos.add(produto);
            }
            
            return produtos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public Produto selectById(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        Produto produto = new Produto();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_PRODUTO_POR_ID);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                produto.setId(rs.getInt("ID_PRODUTO"));
                produto.setNome(rs.getString("NOME_PROD"));
                produto.setFoto(rs.getString("NOME_FOTO"));
                produto.setValor(rs.getDouble("VALOR"));
                produto.setQuantidade(rs.getInt("QUANTIDADE"));
                produto.setIdMarca(rs.getInt("ID_MARCA"));
                produto.setMarca(rs.getString("DESC_MARCA"));
                produto.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                produto.setCategoria(rs.getString("DESC_CATEGORIA"));
                produto.setIdEspecie(rs.getInt("ID_ESPECIE"));
                produto.setEspecie(rs.getString("DESC_ESPECIE"));
            }
            
            return produto;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void insert(Produto produto) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = Conexao.getConexao();
        
	try{            
            ps = conexao.prepareStatement(INSERIR_PRODUTO);
            
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getFoto());
            ps.setDouble(3, produto.getValor());
            ps.setInt(4, produto.getQuantidade());
            ps.setInt(5, produto.getIdMarca());
            ps.setInt(6, produto.getIdCategoria());
            ps.setInt(7, produto.getIdEspecie());
            ps.setBoolean(8, false);
            
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
            
            ps = conexao.prepareStatement(DELETE_PRODUTO);
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
    
    public void update(Produto produto) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
           conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(ALTERAR_PRODUTO);
            
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getFoto());
            ps.setDouble(3, produto.getValor());
            ps.setInt(4, produto.getQuantidade());
            ps.setInt(5, produto.getIdMarca());
            ps.setInt(6, produto.getIdCategoria());
            ps.setInt(7, produto.getIdEspecie());
            ps.setInt(8, produto.getId());
            
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
