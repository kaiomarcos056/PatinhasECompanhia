package Controller.Usuario;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ContaServlet extends HttpServlet {
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
        
        System.out.println("CONTA SERVLET");
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        switch (acao) {
            case "/delete":
                delete(request, response);
                break;
            case "/update":
                String update = update(request, response);
                session.setAttribute("msg", update);
                response.sendRedirect("list");
                //dispatcher = request.getRequestDispatcher("/pages/conta/conta.jsp");
                //dispatcher.forward(request, response);
                break; 
            default:
                dispatcher = request.getRequestDispatcher("/pages/conta/conta.jsp");
                dispatcher.forward(request, response);           
        }
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {       
            HttpSession session = request.getSession(false);
            Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
            usuarioDAO.delete(usuario.getId());
            if (session != null) { session.invalidate(); }
            response.sendRedirect(request.getContextPath()+"/auth/login");
        } 
        catch (Exception e) {
            System.out.println("#ERRO AO EXCLUIR CONTA: "+e.getMessage());
        }
    }
    
    private String update(HttpServletRequest request, HttpServletResponse response) {
        String retorno = "";
        try {
            HttpSession session = request.getSession(false);
            
            Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
            
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            
            usuario.setNome(nome);
            usuario.setEndereco(endereco);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            
            usuarioDAO.update(usuario);
          
            retorno = "Conta alterada com sucesso.";
        } 
        catch (Exception e) {
            System.out.println("ERRO: "+e.getMessage());
            retorno = "#ERRO "+e.getMessage();
        }
        return retorno;
    }
}
