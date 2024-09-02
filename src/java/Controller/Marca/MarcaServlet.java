package Controller.Marca;

import DAO.MarcaDAO;
import Model.Marca;
import Model.Mensagem;
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
                Mensagem insert = insert(request, response);
                session.setAttribute("msg", insert);
                response.sendRedirect("list");
                break;
            case "/delete":
                Mensagem delete = delete(request, response);
                session.setAttribute("msg", delete);
                response.sendRedirect("list");
                break;
            case "/update":
                Mensagem update = update(request, response);
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
    
    private Mensagem insert(HttpServletRequest request, HttpServletResponse response){
        Mensagem msg = new Mensagem();
        try {       
            String descricao = request.getParameter("marca");
            marcaDAO.insert(descricao);
            
            msg.setMensagem("Marca cadastrada com sucesso.");
            msg.setStatus("sucesso");
        } 
        catch (Exception e) {
            msg.setMensagem(e.getMessage());
            msg.setStatus("erro");
        }
        return msg;
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

    private Mensagem delete(HttpServletRequest request, HttpServletResponse response) {
        Mensagem msg = new Mensagem();
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            marcaDAO.delete(id);
            
            msg.setMensagem("Marca excluida com sucesso.");
            msg.setStatus("sucesso");
        } 
        catch (Exception e) {
            msg.setMensagem(e.getMessage());
            msg.setStatus("erro");
        }
        return msg;
    }

    private Mensagem update(HttpServletRequest request, HttpServletResponse response) {
        Mensagem msg = new Mensagem();
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            String descricao = request.getParameter("marca");
            
            Marca marca = new Marca();
            marca.setId(id);
            marca.setDescricao(descricao);
            
            marcaDAO.update(marca);
            
            msg.setMensagem("Marca alterada com sucesso.");
            msg.setStatus("sucesso");
        } 
        catch (Exception e) {
            msg.setMensagem(e.getMessage());
            msg.setStatus("erro");
        }
        return msg;
    }
}
