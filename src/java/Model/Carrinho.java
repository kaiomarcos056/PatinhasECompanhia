package Model;

import DAO.ProdutoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    
    // STRING PARA DEFINIR OS SEPARADORES DA STRING NO VALOR DO COOKIE
    private static final String SEPARADOR_DE_ITENS = "SEP_ITENS";
    private static final String SEPARADOR_NO_ITEM = "SEP_REGISTRO";
    
    // CONSTRUTOR DE OBJETO
    private Carrinho() {
    }
    
    // METODO PARA RETORNAR VALORES DO CARRINHO
    public static final List<CarrinhoItem> obterItensCarrinho(String valor) throws SQLException {
        // CRIANDO UMA LISTA VAZIA DE ITENS DE CARRINHO
        List<CarrinhoItem> carrinhoItens = new ArrayList<CarrinhoItem>();
        
        // VERIFICANDO SE VALOR = null, O TAMANHO DO VALOR = 0, SE VALOR NÃO CONTEM ALGUM DOS SEPARADORES
        // CASO TODAS ESSAS CONDIÇÕES SEJAM ATENDIDAS, RETORNA A LSTA VAZIA
        if (valor == null || valor.trim().length() == 0 || !valor.contains(SEPARADOR_NO_ITEM)) {
            return carrinhoItens;
        }
        
        // CHAMANDO ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        // VERIFICANDO SE O VALOR POSSUI SEPARADOR DE ITENS
        // CASO TENHA, ENTAO POSSUI MAIS DE UM ITEM
        if (valor.contains(SEPARADOR_DE_ITENS)) {
            // CASO POSSUA MAIS DE UM ITEM NO COOKIE
            
            // TRANSFORMANDO A STRING EM ARRAY, QUEBRANDO ONDE TEM O 'SEPARADOR_NO_ITENS'
            String[] itens = valor.split(SEPARADOR_DE_ITENS);
            
            // PERCORRENDO O ARRAY DE ITENS
            for (int i = 0; itens != null && i < itens.length; i++) {
                // TRANSFORMANDO A STRING EM ARRAY, QUEBRANDO ONDE TEM O 'SEPARADOR_NO_ITEM'
                String[] item = itens[i].split(SEPARADOR_NO_ITEM);
                // PEGANDO ID DO PRODUTO
                Integer produtoID = Integer.parseInt(item[0]);
                // PEGANDO QUANTIDADE
                Integer quantidade = Integer.parseInt(item[1]);
                // CRIANDO UM NOVO OBJTO 'CarrinhoItem'
                CarrinhoItem carrinhoItem = new CarrinhoItem();
                // BUSCANDO PRODUTO PELO ID NO BANCO DE DADOS
                Produto produto = produtoDAO.selectById(produtoID);
                // ADICIONANDO PRODUTO AO OBJETO carrinhoItem
                carrinhoItem.setProduto(produto);
                // ADICIONANDO QUANTIDADE AO OBJETO carrinhoItem
                carrinhoItem.setQuantidade(quantidade);
                // ADICIONANDO carrinhoItem NA LISTA carrinhoItens
                carrinhoItens.add(carrinhoItem);
            }
        }
        else{
            // CASO POSSUA UM ITEM NO COOKIE
            
            // TRANSFORMANDO A STRING EM ARRAY, QUEBRANDO ONDE TEM O 'SEPARADOR_NO_ITEM'
            String[] item = valor.split(SEPARADOR_NO_ITEM);
            
            // PEGANDO ID DO PRODUTO
            Integer produtoID = Integer.parseInt(item[0]);
            // PEGANDO QUANTIDADE
            Integer quantidade = Integer.parseInt(item[1]);
            
            // CRIANDO UM NOVO OBJTO 'CarrinhoItem'
            CarrinhoItem carrinhoItem = new CarrinhoItem();
            
            // BUSCANDO PRODUTO PELO ID NO BANCO DE DADOS
            Produto produto = produtoDAO.selectById(produtoID);
            
            // ADICIONANDO PRODUTO AO OBJETO carrinhoItem
            carrinhoItem.setProduto(produto);
            
            // ADICIONANDO QUANTIDADE AO OBJETO carrinhoItem
            carrinhoItem.setQuantidade(quantidade);
            
            // ADICIONANDO carrinhoItem NA LISTA carrinhoItens
            carrinhoItens.add(carrinhoItem);
        }
        
        // RETORNANDO LISTA DE ITENS DO CARRINGO
        return carrinhoItens;
    }
    
    // METODO PARA RETORNAR VALORES DO CARRINHO
    public static final String adicionarItem(int produtoId, int quantidade, String valor) throws SQLException {
        List<CarrinhoItem> carrinhoItens = obterItensCarrinho(valor);
        
        // CASO LISTA DE ITENS ESTEJA VAZIA, RETORNA VALOR DO PRIMEIRO ITEM QUE ESTA SENDO ADICIONADO
        if (carrinhoItens.isEmpty()) {
            System.out.println("CARRINHO IS EMPTY");
            return produtoId + SEPARADOR_NO_ITEM + quantidade;
        }
        
        boolean mudouQuantidade = false;
        boolean maiorQueZero = true;
        String valorCookie = "";
        
        // PERCORRENDO LISTA JÁ QUE NAO ENTROU NO IF, ENTAO TEM PELO MENOS UM ITEM
        for (CarrinhoItem carrinhoItem : carrinhoItens) {
            // VERIFICANDO SE PRODUTO JA TA NA LISTA
            if (carrinhoItem.getProduto().getId() == produtoId) {
                System.out.println("ENTROU AQUi");
                // CASO ESTEJA, SO MUDA A QUANTIDADE
                int novaQuantidade = carrinhoItem.getQuantidade() + quantidade;
                
                if (novaQuantidade > 0) {
                    carrinhoItem.setQuantidade(novaQuantidade);
                    mudouQuantidade = true;
                }
                else {
                    maiorQueZero = false;
                    continue;
                }
            }
            
            // CASO valorCookie SEJA VAZIO, ADICIONA UM SEPARADOR
            if (!valorCookie.isEmpty()) { 
                valorCookie += SEPARADOR_DE_ITENS; 
            }
            
            if (maiorQueZero) {
                // ADICIONANDO VALOR DA LISTA NO VALOR DO COOKIE
                valorCookie += carrinhoItem.getProduto().getId() + SEPARADOR_NO_ITEM + carrinhoItem.getQuantidade();
            }
        }
        
        // CASO PRODUTO NÃO SEJA IGUAL AO QUE JA TEM NO COOKIE, ADICIONA O NOVO PRODUTO
        if (!mudouQuantidade && maiorQueZero) {
            valorCookie += SEPARADOR_DE_ITENS + produtoId + SEPARADOR_NO_ITEM + quantidade;
        }
        
        return valorCookie;
    }
    
    public static final String excluirItem(int produtoId, String valor) throws SQLException {
        List<CarrinhoItem> carrinhoItens = obterItensCarrinho(valor);
        
        if (carrinhoItens.isEmpty()) {
            return "";
        }
        
        String resultado = "";
        
        for (CarrinhoItem carrinhoItem : carrinhoItens) {
            if (carrinhoItem.getProduto().getId() == produtoId) {
                continue;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_DE_ITENS;
            }
            resultado += carrinhoItem.getProduto().getId() + SEPARADOR_NO_ITEM + carrinhoItem.getQuantidade();
        }
        return resultado;
    }
}
