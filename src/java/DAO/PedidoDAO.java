package DAO;

import DB.Conexao;
import Model.Cartao;
import Model.Endereco;
import Model.ItemPedido;
import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private static final String INSERIR_PEDIDO = ""
            + "INSERT INTO PEDIDO(id_usuario, frete, logradouro, cep, numero_endereco, "
            + "complemento, bairro, cidade, uf, ponto_referencia, numero_cartao, "
            + "nome_cartao, cpf_titular, validade, cvv, valor_frete, valor_itens, "
            + "valor_total) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_pedido";
    
    private static final String INSERIR_ITEM_PEDIDO = ""
            + "INSERT INTO PEDIDO_ITEM(id_pedido, id_produto, quantidade, valor_unitario, valor_total) "
            + "VALUES(?, ?, ?, ?, ?)";
    
    private static final String DELETE_ITEM = "DELETE FROM PEDIDO_ITEM WHERE ID_PEDIDO = ?";
    
    private static final String DELETE_PEDIDO = "DELETE FROM PEDIDO WHERE ID_PEDIDO = ?";
    
    private static final String SELECT_PEDIDOS_POR_USUARIO = "SELECT * FROM PEDIDO WHERE ID_USUARIO = ? ORDER BY ID_PEDIDO DESC";
    
    private static final String SELECT_ALL_PEDIDOS = "SELECT * FROM PEDIDO WHERE 1 = 1 ORDER BY ID_PEDIDO DESC";
    
    private static final String SELECT_ALL_PEDIDOS_POR_PEDIDO = "SELECT * FROM PEDIDO WHERE ID_PEDIDO = ? ORDER BY ID_PEDIDO DESC";
    
    private static final String SELECT_PEDIDO_POR_USUARIO_E_PEDIDO = "SELECT * FROM PEDIDO WHERE ID_USUARIO = ? "
            + "AND ID_PEDIDO = ? ORDER BY ID_PEDIDO DESC";
    
    private static final String SELECT_ITENS_PEDIDO_POR_USUARIO = ""
            + "SELECT \n" +
"	IT.* , PR.NOME_FOTO, PR.NOME\n" +
"FROM PEDIDO_ITEM IT \n" +
"INNER JOIN PEDIDO PE ON PE.ID_PEDIDO = IT.ID_PEDIDO\n" +
"INNER JOIN PRODUTO PR ON PR.ID_PRODUTO = IT.ID_PRODUTO\n" +
"WHERE PE.ID_USUARIO = ?";
    
    private static final String SELECT_ITEM_PEDIDO_POR_USUARIO_E_PEDIDO = ""
            + "SELECT \n" +
"	IT.* , PR.NOME_FOTO, PR.NOME\n" +
"FROM PEDIDO_ITEM IT \n" +
"INNER JOIN PEDIDO PE ON PE.ID_PEDIDO = IT.ID_PEDIDO\n" +
"INNER JOIN PRODUTO PR ON PR.ID_PRODUTO = IT.ID_PRODUTO\n" +
"WHERE PE.ID_USUARIO = ? AND IT.ID_PEDIDO = ?";
    
    private static final String SELECT_ALL_ITENS = ""
            + "SELECT \n" +
"	IT.* , PR.NOME_FOTO, PR.NOME\n" +
"FROM PEDIDO_ITEM IT \n" +
"INNER JOIN PEDIDO PE ON PE.ID_PEDIDO = IT.ID_PEDIDO\n" +
"INNER JOIN PRODUTO PR ON PR.ID_PRODUTO = IT.ID_PRODUTO";
    
    
    public int insertPedido(Pedido pedido) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        int chavePrimaria = 0;
        
	try{            
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(INSERIR_PEDIDO);
            
            ps.setInt(1, pedido.getIdUsuario());
            ps.setString(2, pedido.getFrete());
            ps.setString(3, pedido.getEndereco().getLogradouro());
            ps.setString(4, pedido.getEndereco().getCep());
            ps.setString(5, pedido.getEndereco().getNumeroEndereco());
            ps.setString(6, pedido.getEndereco().getComplemento());
            ps.setString(7, pedido.getEndereco().getBairro());
            ps.setString(8, pedido.getEndereco().getCidade());
            ps.setString(9, pedido.getEndereco().getUf());
            ps.setString(10, pedido.getEndereco().getPontoReferencia());
            ps.setString(11, pedido.getCartao().getNumero());
            ps.setString(12, pedido.getCartao().getNome());
            ps.setString(13, pedido.getCartao().getCpf());
            ps.setString(14, pedido.getCartao().getValidade());
            ps.setString(15, pedido.getCartao().getCvv());
            ps.setDouble(16, pedido.getValorFrete());
            ps.setDouble(17, pedido.getValorItens());
            ps.setDouble(18, pedido.getValorTotal());
                        
            rs = ps.executeQuery();
          
            if (rs.next()) {
                chavePrimaria = rs.getInt(1);
            } else {
                throw new SQLException("Falha ao inserir pedido, nenhuma ID gerada.");
            }
	} 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
	}
        finally{
            if (rs != null) try { rs.close(); } catch (SQLException e) { }
            if (ps != null) try { ps.close(); } catch (SQLException e) { }
            if (conexao != null) try { conexao.close(); } catch (SQLException e) { }
        }
        
        return chavePrimaria;
    }
    
    public void insertPedidoItem(ItemPedido item) throws SQLException {	
        PreparedStatement ps = null;
        Connection conexao = null;
        
	try{            
            conexao = Conexao.getConexao();
            ps = conexao.prepareStatement(INSERIR_ITEM_PEDIDO);
            
            ps.setInt(1, item.getIdPedido());
            ps.setInt(2, item.getIdProduto());
            ps.setInt(3, item.getQuantidade());
            ps.setDouble(4, item.getValorUnitario());
            ps.setDouble(5, item.getValorTotal());
                        
            ps.executeUpdate();
	} 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
	}
        finally{
            if (ps != null) try { ps.close(); } catch (SQLException e) { }
            if (conexao != null) try { conexao.close(); } catch (SQLException e) { }
        }
        
    }
    
    public List<Pedido> selectByUsuario(int idUsuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Pedido> pedidos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_PEDIDOS_POR_USUARIO);
            ps.setInt(1, idUsuario);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Endereco endereco = new Endereco();
                
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setNumeroEndereco(rs.getString("NUMERO_ENDERECO"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCidade(rs.getString("CIDADE"));
                endereco.setUf(rs.getString("UF"));
                endereco.setPontoReferencia(rs.getString("PONTO_REFERENCIA"));
                
                Cartao cartao = new Cartao();
                cartao.setNumero(rs.getString("NUMERO_CARTAO"));
                cartao.setNome(rs.getString("NOME_CARTAO"));
                cartao.setCpf(rs.getString("CPF_TITULAR"));
                cartao.setValidade(rs.getString("VALIDADE"));
                cartao.setCvv(rs.getString("CVV"));
                
                Pedido pedido = new Pedido();
                pedido.setCartao(cartao);
                pedido.setEndereco(endereco);
                pedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                pedido.setIdUsuario(rs.getInt("ID_USUARIO"));
                pedido.setValorFrete(rs.getDouble("VALOR_FRETE"));
                pedido.setValorItens(rs.getDouble("VALOR_ITENS"));
                pedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                pedido.setDataPedido(rs.getDate("DATA_PEDIDO"));
                
                pedidos.add(pedido);
            }
            
            return pedidos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Pedido> selectAll() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Pedido> pedidos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_ALL_PEDIDOS);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Endereco endereco = new Endereco();
                
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setNumeroEndereco(rs.getString("NUMERO_ENDERECO"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCidade(rs.getString("CIDADE"));
                endereco.setUf(rs.getString("UF"));
                endereco.setPontoReferencia(rs.getString("PONTO_REFERENCIA"));
                
                Cartao cartao = new Cartao();
                cartao.setNumero(rs.getString("NUMERO_CARTAO"));
                cartao.setNome(rs.getString("NOME_CARTAO"));
                cartao.setCpf(rs.getString("CPF_TITULAR"));
                cartao.setValidade(rs.getString("VALIDADE"));
                cartao.setCvv(rs.getString("CVV"));
                
                Pedido pedido = new Pedido();
                pedido.setCartao(cartao);
                pedido.setEndereco(endereco);
                pedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                pedido.setIdUsuario(rs.getInt("ID_USUARIO"));
                pedido.setValorFrete(rs.getDouble("VALOR_FRETE"));
                pedido.setValorItens(rs.getDouble("VALOR_ITENS"));
                pedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                pedido.setDataPedido(rs.getDate("DATA_PEDIDO"));
                
                pedidos.add(pedido);
            }
            
            return pedidos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Pedido> selectAllByPedido(int idPedido) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Pedido> pedidos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_ALL_PEDIDOS_POR_PEDIDO);
            ps.setInt(1, idPedido);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Endereco endereco = new Endereco();
                
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setNumeroEndereco(rs.getString("NUMERO_ENDERECO"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCidade(rs.getString("CIDADE"));
                endereco.setUf(rs.getString("UF"));
                endereco.setPontoReferencia(rs.getString("PONTO_REFERENCIA"));
                
                Cartao cartao = new Cartao();
                cartao.setNumero(rs.getString("NUMERO_CARTAO"));
                cartao.setNome(rs.getString("NOME_CARTAO"));
                cartao.setCpf(rs.getString("CPF_TITULAR"));
                cartao.setValidade(rs.getString("VALIDADE"));
                cartao.setCvv(rs.getString("CVV"));
                
                Pedido pedido = new Pedido();
                pedido.setCartao(cartao);
                pedido.setEndereco(endereco);
                pedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                pedido.setIdUsuario(rs.getInt("ID_USUARIO"));
                pedido.setValorFrete(rs.getDouble("VALOR_FRETE"));
                pedido.setValorItens(rs.getDouble("VALOR_ITENS"));
                pedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                pedido.setDataPedido(rs.getDate("DATA_PEDIDO"));
                
                pedidos.add(pedido);
            }
            
            return pedidos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<ItemPedido> selectItensByUsuario(int idUsuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<ItemPedido> itemPedidos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_ITENS_PEDIDO_POR_USUARIO);
            ps.setInt(1, idUsuario);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){                
                ItemPedido itemPedido = new ItemPedido();
                
                itemPedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                itemPedido.setIdProduto(rs.getInt("ID_PRODUTO"));
                itemPedido.setIdPedidoItem(rs.getInt("ID_PEDIDO_ITEM"));
                itemPedido.setQuantidade(rs.getInt("QUANTIDADE"));
                itemPedido.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                itemPedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                itemPedido.setFotoProduto(rs.getString("NOME_FOTO"));
                itemPedido.setNomeProduto(rs.getString("NOME"));
                                
                itemPedidos.add(itemPedido);
            }
            
            return itemPedidos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<ItemPedido> selectAllItens() throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<ItemPedido> itemPedidos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_ALL_ITENS);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){                
                ItemPedido itemPedido = new ItemPedido();
                
                itemPedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                itemPedido.setIdProduto(rs.getInt("ID_PRODUTO"));
                itemPedido.setIdPedidoItem(rs.getInt("ID_PEDIDO_ITEM"));
                itemPedido.setQuantidade(rs.getInt("QUANTIDADE"));
                itemPedido.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                itemPedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                itemPedido.setFotoProduto(rs.getString("NOME_FOTO"));
                itemPedido.setNomeProduto(rs.getString("NOME"));
                                
                itemPedidos.add(itemPedido);
            }
            
            return itemPedidos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<Pedido> selectByPedidoUsuario(int idUsuario, int idPedido) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<Pedido> pedidos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_PEDIDO_POR_USUARIO_E_PEDIDO);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idPedido);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Endereco endereco = new Endereco();
                
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setNumeroEndereco(rs.getString("NUMERO_ENDERECO"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCidade(rs.getString("CIDADE"));
                endereco.setUf(rs.getString("UF"));
                endereco.setPontoReferencia(rs.getString("PONTO_REFERENCIA"));
                
                Cartao cartao = new Cartao();
                cartao.setNumero(rs.getString("NUMERO_CARTAO"));
                cartao.setNome(rs.getString("NOME_CARTAO"));
                cartao.setCpf(rs.getString("CPF_TITULAR"));
                cartao.setValidade(rs.getString("VALIDADE"));
                cartao.setCvv(rs.getString("CVV"));
                
                Pedido pedido = new Pedido();
                pedido.setCartao(cartao);
                pedido.setEndereco(endereco);
                pedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                pedido.setIdUsuario(rs.getInt("ID_USUARIO"));
                pedido.setValorFrete(rs.getDouble("VALOR_FRETE"));
                pedido.setValorItens(rs.getDouble("VALOR_ITENS"));
                pedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                pedido.setDataPedido(rs.getDate("DATA_PEDIDO"));
                
                pedidos.add(pedido);
            }
            
            return pedidos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public List<ItemPedido> selectItensByPedidoUsuario(int idUsuario, int idPedido) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        List<ItemPedido> itemPedidos = new ArrayList<>();
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(SELECT_ITEM_PEDIDO_POR_USUARIO_E_PEDIDO);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idPedido);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){                
                ItemPedido itemPedido = new ItemPedido();
                
                itemPedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                itemPedido.setIdProduto(rs.getInt("ID_PRODUTO"));
                itemPedido.setIdPedidoItem(rs.getInt("ID_PEDIDO_ITEM"));
                itemPedido.setQuantidade(rs.getInt("QUANTIDADE"));
                itemPedido.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
                itemPedido.setValorTotal(rs.getDouble("VALOR_TOTAL"));
                itemPedido.setFotoProduto(rs.getString("NOME_FOTO"));
                itemPedido.setNomeProduto(rs.getString("NOME"));
                                
                itemPedidos.add(itemPedido);
            }
            
            return itemPedidos;
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally {
            ps.close();
            conexao.close();
        }
    }
    
    public void deleteItem(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(DELETE_ITEM);
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
    
    public void deletePedido(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = Conexao.getConexao();
            
            ps = conexao.prepareStatement(DELETE_PEDIDO);
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
