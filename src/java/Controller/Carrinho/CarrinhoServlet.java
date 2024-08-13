package Controller.Carrinho;

import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import Model.Carrinho;
import Model.CarrinhoItem;
import Model.Cartao;
import Model.CookieUtils;
import Model.Endereco;
import Model.ItemPedido;
import Model.Pedido;
import Model.Produto;
import Model.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CarrinhoServlet extends HttpServlet {
    private ProdutoDAO produtoDAO;
    
    @Override
    public void init() {
        produtoDAO = new ProdutoDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String rota = request.getServletPath();
        String acao = request.getPathInfo();
        if (acao == null) { acao = "/";}
        
        System.out.println("CARRINHO SERVLET");
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        switch (acao) {
            case "/add":
                addItem(request, response);                
                break;
            case "/add-item":
                addQuantidade(request, response);                
                break;
            case "/delete":
                excluirItem(request, response);                
                break;
            case "/new":
                insert(request, response);                
                break;
            default:
                List<CarrinhoItem> carrinho = listCarrinho(request, response);
                request.setAttribute("carrinho", carrinho);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/carrinho/carrinho.jsp");
                dispatcher.forward(request, response);
         }
    }
    
    private List<CarrinhoItem> listCarrinho(HttpServletRequest request, HttpServletResponse response){
        List<CarrinhoItem> carrinho = null;
        try{
            Cookie c = CookieUtils.obterCookie(request);
            carrinho = Carrinho.obterItensCarrinho(c.getValue());
        }
        catch(Exception e){
            System.out.println("ERRO AO LISTAR CARRINHO = "+e.getMessage());
        }
        return carrinho;
    }
    
    private void addItem(HttpServletRequest request, HttpServletResponse response){
        try {
            int produtoID = Integer.parseInt(request.getParameter("produtoID"));
            
            int quantidade = 1;
            
            Cookie c = CookieUtils.obterCookie(request);
            
            String novoValorCookie = Carrinho.adicionarItem(produtoID, quantidade, c.getValue());
            
            c.setValue(novoValorCookie);
            response.addCookie(c);
            
            response.sendRedirect(request.getContextPath()+"/");
        } 
        catch (Exception e) {
            System.out.println("ERRO AO ADD ITEM = "+e.getMessage());
        }
    }
    
    private void addQuantidade(HttpServletRequest request, HttpServletResponse response){
        try {
            int produtoID = Integer.parseInt(request.getParameter("produtoID"));
            
            String acao = request.getParameter("action");
            
            int quantidade = 1;
            if (acao.equals("remove")) {
                quantidade = -1;
            }
            
            Cookie c = CookieUtils.obterCookie(request);
            
            Produto produto = produtoDAO.selectById(produtoID);
            
            Integer quantidadeAtual = Carrinho.obterQuantidadeCarrinho(produtoID, c.getValue());
            quantidadeAtual = quantidade + quantidadeAtual;
            
            System.out.println("QUANTIDADE A SER INSERIDA = "+quantidadeAtual);
            System.out.println("QUANTIDADE NO ESTOQUE = "+produto.getQuantidade());
            
            if (quantidadeAtual > produto.getQuantidade()) {
                HttpSession session = request.getSession();
                session.setAttribute("msg", "Quantidade máxima atingida.");
            }
            else{
                String novoValorCookie = Carrinho.adicionarItem(produtoID, quantidade, c.getValue());
            
                c.setValue(novoValorCookie);
                response.addCookie(c);
            }
            
            response.sendRedirect(request.getContextPath()+"/carrinho");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void excluirItem(HttpServletRequest request, HttpServletResponse response) {
        try {            
            int produtoID = Integer.parseInt(request.getParameter("produtoID"));
            
            Cookie c = CookieUtils.obterCookie(request);
            
            String novoValorCookie = Carrinho.excluirItem(produtoID, c.getValue());
            
            c.setValue(novoValorCookie);
            response.addCookie(c);
            
            response.sendRedirect(request.getContextPath()+"/carrinho");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("INICIANDO COMPRA.");
        try {
            HttpSession session = request.getSession(false);
            
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            if (usuarioLogado == null) { response.sendRedirect(request.getContextPath()+"/auth/login"); }
            
            Integer idUsuario = usuarioLogado.getId();
            
            String frete = request.getParameter("tipo-frete");
            
            String logradouro = request.getParameter("endereco");
            String cep = request.getParameter("cep");
            String numEndereco = request.getParameter("numero-casa");
            String complemento = request.getParameter("complemento");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String pontoRef = request.getParameter("ponto-referencia");
            
            Double valorFrete = Double.parseDouble(request.getParameter("valor-frete"));
            Double valorTotal = Double.parseDouble(request.getParameter("valor-total"));
            
            Endereco endereco = new Endereco();
            endereco.setLogradouro(logradouro);
            endereco.setCep(cep);
            endereco.setNumeroEndereco(numEndereco);
            endereco.setComplemento(complemento);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setUf(uf);
            endereco.setPontoReferencia(pontoRef);
            
            String numCartao = request.getParameter("numero-cartao");
            String nomeCartao = request.getParameter("nome-cartao");
            String cpfTitular = request.getParameter("cpf-titular");
            String validade = request.getParameter("validade");
            String cvv = request.getParameter("cvv");
            
            Cartao cartao = new Cartao();
            cartao.setNumero(numCartao);
            cartao.setNome(nomeCartao);
            cartao.setCpf(cpfTitular);
            cartao.setValidade(validade);
            cartao.setCvv(cvv);
            
            Pedido pedido = new Pedido();
            pedido.setCartao(cartao);
            pedido.setEndereco(endereco);
            pedido.setFrete(frete);
            pedido.setIdUsuario(idUsuario);
            pedido.setValorFrete(valorFrete);
            pedido.setValorItens(valorFrete-valorTotal);
            pedido.setValorTotal(valorTotal);

            PedidoDAO pedidoDAO = new PedidoDAO();
            Integer idPedido = pedidoDAO.insertPedido(pedido);
            
            Cookie c = CookieUtils.obterCookie(request);
            
            List<CarrinhoItem> carrinho = Carrinho.obterItensCarrinho(c.getValue());
            
            for (CarrinhoItem item : carrinho) {
                System.out.println(item.getProduto().getId());
                System.out.println(item.getQuantidade());
                
                double vlrTotal = item.getQuantidade() * item.getProduto().getValor();
                
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setIdPedido(idPedido);
                itemPedido.setIdProduto(item.getProduto().getId());
                itemPedido.setValorUnitario(item.getProduto().getValor());
                itemPedido.setQuantidade(item.getQuantidade());
                itemPedido.setValorTotal(vlrTotal);
                
                pedidoDAO.insertPedidoItem(itemPedido);
                
                Produto produto = produtoDAO.selectById(item.getProduto().getId());
                
                int qtdFinal = produto.getQuantidade() - item.getQuantidade();
                
                produto.setQuantidade(qtdFinal);
                
                produtoDAO.update(produto);
            }
            
            c.setValue("");
            response.addCookie(c);
            
            request.setAttribute("nro_pedido", idPedido);
            request.setAttribute("vlr_pedido", valorTotal);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/carrinho/final-compra.jsp");
            dispatcher.forward(request, response);
        } 
        catch (Exception e) {
            System.out.println("ERRO NO PROCESSO DE COMPRAR: "+e.getMessage());
        }
    }
}
