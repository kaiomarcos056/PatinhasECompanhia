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

public class AlterarCadastro extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher expeditor = null;

        HttpSession session = request.getSession();
        String idUsuLog = session.getAttribute("id_usu").toString();
        
        try{
            UsuarioDAO usuDAO = new UsuarioDAO();
            Usuario usu = usuDAO.pegarUsuario(Integer.parseInt(idUsuLog));
            Integer id = usu.getId();
            if(id != null && id != 0){
                session.setAttribute("usu-dados", usu);
                expeditor = request.getRequestDispatcher("pages/alterar-cadastro.jsp");
            }
            else{
                expeditor = request.getRequestDispatcher("pages/home.jsp");
            }
            expeditor.forward(request, response); 
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}
