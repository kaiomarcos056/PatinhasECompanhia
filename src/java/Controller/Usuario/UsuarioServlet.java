package Controller.Usuario;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    
    public void init() {
        usuarioDAO = new UsuarioDAO();
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
        
        System.out.println("USUARIO SERVLET");
        
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
                List<Usuario> usuarios = list(request, response);
                request.setAttribute("usuarios", usuarios);
                dispatcher = request.getRequestDispatcher("/pages/usuario/usuario.jsp");
                dispatcher.forward(request, response);           
        }
    }
    
    private void form(HttpServletRequest request, HttpServletResponse response, String acao){
        try{
            if("/edit".equals(acao)){
                Integer id = Integer.valueOf(request.getParameter("id"));
                Usuario usuario = usuarioDAO.selectById(id);
                request.setAttribute("usuario", usuario);
            }
            request.setAttribute("rota", acao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/usuario/form-usuario.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private String insert(HttpServletRequest request, HttpServletResponse response){
        String retorno = "";
        try {       
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            boolean isAdm = Boolean.parseBoolean(request.getParameter("adm"));
            
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEndereco(endereco);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setAdministrador(isAdm);
            
            usuarioDAO.insert(usuario);
            retorno = "Usuario cadastrado com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
    
    private List<Usuario> list(HttpServletRequest request, HttpServletResponse response){
        String usuario = request.getParameter("usuario");
        List<Usuario> usuarios = null; 
        try {
            usuarios = usuarioDAO.select();
            if (usuario != null) {
                usuarios = usuarioDAO.selectByNome(usuario);
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            Integer id = Integer.valueOf(request.getParameter("id"));
            usuarioDAO.delete(id);
            retorno = "Usuario excluido com sucesso.";
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
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            boolean isAdm = Boolean.parseBoolean(request.getParameter("adm"));
            
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEndereco(endereco);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setAdministrador(isAdm);
            
            usuarioDAO.update(usuario);
          
            retorno = "Usuario alterado com sucesso.";
        } 
        catch (Exception e) {
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
}