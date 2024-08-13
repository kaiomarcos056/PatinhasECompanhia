package Controller.Favorito;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FavoritoServlet extends HttpServlet {

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
            /*case "/login":
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
                response.sendRedirect("cadastro");*/
            default:
                //List<Marca> marcas = list(request, response);
                //request.setAttribute("marcas", marcas);
                dispatcher = request.getRequestDispatcher("/pages/favorito/favorito.jsp");
                dispatcher.forward(request, response);           
        }
    }

}
