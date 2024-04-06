package Controller;

import DAO.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;

public class CadastroUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("/cadastro");
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String endereco = request.getParameter("endereco");
        boolean isAdmin = false;
        
//        PrintWriter out = response.getWriter();
//        out.print(nome);
//        out.print(email);
//        out.print(senha);
//        out.print(endereco);
        
        RequestDispatcher dispatcher = null;

        try {
            Connection conexao = Conexao.getInstancia().getConexao();
            
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO USUARIO(nome, endereco, senha, email, administrador) "
                    + "VALUES(?, ?, ?, ?, ?)");
            
            ps.setString(1, nome);
            ps.setString(2, endereco);
            ps.setString(3, senha);
            ps.setString(4, email);
            ps.setBoolean(5, isAdmin);
            
            int numeroDeLinha = ps.executeUpdate();
            dispatcher = request.getRequestDispatcher("cadastro.jsp");
            
            if(numeroDeLinha > 0){
               request.setAttribute("status", "sucess");
            }
            else{
                request.setAttribute("status", "failed");
            }
            
            ps.close();
            conexao.close();
            dispatcher.forward(request, response);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
