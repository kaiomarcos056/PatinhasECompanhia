package Controller.Auth;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthServlet extends HttpServlet {
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
        
        System.out.println("ROTA = "+rota);
        System.out.println("ACAO = "+acao);
        
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        
        switch (acao) {
            case "/login":
                dispatcher = request.getRequestDispatcher("/pages/auth/login.jsp");
                dispatcher.forward(request, response);
                break;
            case "/logar":
                logar(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            case "/cadastro":
                dispatcher = request.getRequestDispatcher("/pages/auth/cadastro.jsp");
                dispatcher.forward(request, response);
                break;
            case "/cadastrar":
                String insert = insert(request, response);
                session.setAttribute("msg", insert);
                response.sendRedirect("cadastro");
            default:
                System.out.println("");
        }
    }

    private String insert(HttpServletRequest request, HttpServletResponse response) {
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEndereco(endereco);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        
        String retorno = "";
        
        try{
            usuarioDAO.insert(usuario);
            retorno = "Usuário cadastrado com sucesso.";
        }
        catch(Exception e){
            retorno = e.getMessage();
        }
        
        return retorno;
    }
    
    private void logar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher;
        
        try{
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            
            Usuario usu = usuarioDAO.selectByLogin(email, senha);
            
            if (usu != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario_logado", usu);
                response.sendRedirect(request.getContextPath() + "/dashboard/");
            } 
            else {
                request.setAttribute("msg", "Email ou senha inválidos.");
                request.getRequestDispatcher("login").forward(request, response);
            }
        }
        catch(Exception e){
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("login").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try{
            HttpSession session = request.getSession(false);
            if (session != null) { session.invalidate(); }
            response.sendRedirect(request.getContextPath()+"/auth/login");
        }
        catch(Exception e){
            System.out.println("ERRO AO DESLOGAR: "+e.getMessage());
        }
    }
}
