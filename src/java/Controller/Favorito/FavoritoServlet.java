package Controller.Favorito;

import DAO.FavoritoDAO;
import DAO.MarcaDAO;
import DAO.ProdutoDAO;
import Model.Favorito;
import Model.Produto;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FavoritoServlet extends HttpServlet {
    private FavoritoDAO favoritoDAO;
    private ProdutoDAO produtoDAO;
    
    public void init() {
        favoritoDAO = new FavoritoDAO();
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
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        switch (acao) {
            case "/remove":
                delete(request, response);
                break;
            case "/add":
                insert(request, response);
                break;
            default:
                List<Produto> produtos = list(request, response);
                request.setAttribute("produtos", produtos);
                dispatcher = request.getRequestDispatcher("/pages/favorito/favorito.jsp");
                dispatcher.forward(request, response);           
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            if (usuarioLogado == null) { response.sendRedirect(request.getContextPath()+"/auth/login"); }
            
            Integer idUsuario = usuarioLogado.getId();
            
            Integer idProduto = Integer.valueOf(request.getParameter("id"));
            
            Favorito favorito = new Favorito();
            favorito.setIdProduto(idProduto);
            favorito.setIdUsuario(idUsuario);
            
            //String currentUrl = request.getRequestURL().toString();
            //System.out.println(currentUrl);
            
            String referer = request.getHeader("Referer");
            System.out.println("REFERER = "+referer);
            
            favoritoDAO.insert(favorito);
            
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
               response.sendRedirect(request.getContextPath()+"/");
            }
            
        } 
        catch (Exception e) {
            System.out.println("#ERRO AO CADASTRAR FAVORITO = "+e.getMessage());
        }
    }

    private List<Produto> list(HttpServletRequest request, HttpServletResponse response) { 
        List<Produto> produtos = new ArrayList<>();
        
        try {           
            HttpSession session = request.getSession(false);
            
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            Integer idUsuario = usuarioLogado.getId();
            
            List<Favorito> favoritos = favoritoDAO.selectByUsuario(idUsuario);
            
            for (Favorito favorito: favoritos) {
                int idProduto = favorito.getIdProduto();
                
                Produto produto = produtoDAO.selectById(idProduto);
                
                produtos.add(produto);
            }
        } 
        catch (Exception e) {
            System.out.println("#ERRO AO LISTAR FAVORITO"+e.getMessage());
        }
        
        return produtos;
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {  
            HttpSession session = request.getSession(false);
            
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado");
            
            Integer idUsuario = usuarioLogado.getId();
            Integer idProduto = Integer.valueOf(request.getParameter("id"));
            
            System.out.println("USUARIO = "+idUsuario);
            System.out.println("PRODUTO = "+idProduto);
            
            String referer = request.getHeader("Referer");
            System.out.println("REFERER = "+referer);
            
            favoritoDAO.deleteByUsuarioAndProduto(idUsuario, idProduto);
            
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
               response.sendRedirect(request.getContextPath()+"/dashboard/favorito/list");
            }
               
        }
        catch(Exception e){
            System.out.println("#ERRO AO DELETAR FAVORITO"+e.getMessage());
        }
    }

}
