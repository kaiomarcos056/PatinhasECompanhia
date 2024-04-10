package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rota = request.getServletPath();
        System.out.println(rota);
        switch (rota) {
            case "/novo-usuario":
                boolean novoUsuario = novoUsuario(request, response);
                System.out.println(novoUsuario);
                String mensagem = (novoUsuario) ? "Usuario cadastro com sucesso." : "Erro ao cadastrar usuario.";
                System.out.println(mensagem);
                request.setAttribute("mensagem", mensagem);
                
                RequestDispatcher expeditor = request.getRequestDispatcher("cadastro.jsp");
                expeditor.forward(request, response); 
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    private boolean novoUsuario(HttpServletRequest request, HttpServletResponse response){
        try{
            Usuario usuario = new Usuario();
         
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setEndereco(request.getParameter("endereco"));
            usuario.setAdministrador(false);
        
            UsuarioDAO novoUsuario = new UsuarioDAO();
            return novoUsuario.cadastrarUsuario(usuario);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
