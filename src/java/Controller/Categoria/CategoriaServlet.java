package Controller.Categoria;

import DAO.CategoriaDAO;
import Model.Categoria;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CategoriaServlet extends HttpServlet {
private CategoriaDAO categoriaDAO;
    
    public void init() {
        categoriaDAO = new CategoriaDAO();
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
        
        System.out.println("CATEGORIA SERVLET");
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        switch (acao) {
            case "/new":
            case "/edit":
                form(request, response, acao);
                break;
                
            case "/insert":
                String insert = insert(request, response);
                session.setAttribute("msg", insert);
                response.sendRedirect("list");
                break;
                
            case "/delete":
                String delete = delete(request, response);
                session.setAttribute("msg", delete);
                response.sendRedirect("list");
                break;
                
            case "/update":
                String update = update(request, response);
                session.setAttribute("msg", update);
                response.sendRedirect("list");
                break; 
                
            default:
                List<Categoria> categorias = list(request, response);
                request.setAttribute("categorias", categorias);
                dispatcher = request.getRequestDispatcher("/pages/categoria/categoria.jsp");
                dispatcher.forward(request, response);           
        }
    }
    
    private void form(HttpServletRequest request, HttpServletResponse response, String acao){
        try{
            if("/edit".equals(acao)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                Categoria categoria = categoriaDAO.selectById(id);
                request.setAttribute("categoria", categoria);
            }
            request.setAttribute("rota", acao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/categoria/form-categoria.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private String insert(HttpServletRequest request, HttpServletResponse response){
        String retorno = "";
        try {       
            String descricao = request.getParameter("categoria");
            categoriaDAO.insert(descricao);
            retorno = "Categoria cadastrada com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
    private List<Categoria> list(HttpServletRequest request, HttpServletResponse response){
        String categoria = request.getParameter("categoria");
        List<Categoria> categorias = null; 
        try {
            categorias = categoriaDAO.select();
            if (categoria != null) {
                categorias = categoriaDAO.selectByCategoria(categoria);
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categorias;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            categoriaDAO.delete(id);
            retorno = "Categoria excluida com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }

    private String update(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            String descricao = request.getParameter("categoria");
            
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setDescricao(descricao);
            
            categoriaDAO.update(categoria);
            
            retorno = "Categoria alterada com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
}
