package Controller;

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

public class LoginServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher expeditor = null;
        HttpSession session = request.getSession();
        
        try {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            
            UsuarioDAO usuDAO = new UsuarioDAO();
            
            Usuario usuarioLogado = usuDAO.login(email, senha);
            Integer id = usuarioLogado.getId();
            System.out.println("ID: "+id);
            if(id != null && id != 0){
                expeditor = request.getRequestDispatcher("pages/home.jsp");
                session.setAttribute("id_usu", usuarioLogado.getId()+"");
                session.setAttribute("nome_usu", usuarioLogado.getNome());
            }
            else{
                expeditor = request.getRequestDispatcher("index.jsp");
            }
            
            expeditor.forward(request, response); 
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.sendRedirect("pages/home.jsp");
    }
}
