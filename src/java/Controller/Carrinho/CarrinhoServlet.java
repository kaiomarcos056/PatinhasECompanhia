package Controller.Carrinho;

import Model.Carrinho;
import Model.CarrinhoItem;
import Model.CookieUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarrinhoServlet extends HttpServlet {

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
            
            String novoValorCookie = Carrinho.adicionarItem(produtoID, quantidade, c.getValue());
            
            c.setValue(novoValorCookie);
            response.addCookie(c);
            
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
}
