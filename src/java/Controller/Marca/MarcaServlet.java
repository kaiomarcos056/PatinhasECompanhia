package Controller.Marca;

import DAO.MarcaDAO;
import Model.Marca;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MarcaServlet extends HttpServlet {
    private MarcaDAO marcaDAO;
    
    public void init() {
        marcaDAO = new MarcaDAO();
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
        
        System.out.println("MARCA SERVLET");
        
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
                List<Marca> marcas = list(request, response);
                request.setAttribute("marcas", marcas);
                dispatcher = request.getRequestDispatcher("/pages/marca/marca.jsp");
                dispatcher.forward(request, response);           
        }
    }
    
    private void form(HttpServletRequest request, HttpServletResponse response, String acao){
        try{
            if("/edit".equals(acao)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                Marca marca = marcaDAO.selectById(id);
                request.setAttribute("marca", marca);
            }
            request.setAttribute("rota", acao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/marca/form-marca.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private String insert(HttpServletRequest request, HttpServletResponse response){
        String retorno = "";
        try {       
            String descricao = request.getParameter("marca");
            marcaDAO.insert(descricao);
            retorno = "Marca cadastrada com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
    private List<Marca> list(HttpServletRequest request, HttpServletResponse response){
        String marca = request.getParameter("marca");
        List<Marca> marcas = null; 
        try {
            marcas = marcaDAO.select();
            if (marca != null) {
                marcas = marcaDAO.selectByMarca(marca);
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return marcas;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            marcaDAO.delete(id);
            retorno = "Marca excluida com sucesso.";
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
            String descricao = request.getParameter("marca");
            
            Marca marca = new Marca();
            marca.setId(id);
            marca.setDescricao(descricao);
            
            marcaDAO.update(marca);
            
            retorno = "Marca alterada com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
}
