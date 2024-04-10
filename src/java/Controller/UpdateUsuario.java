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

public class UpdateUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        RequestDispatcher expeditor = request.getRequestDispatcher("/pages/home.jsp");
        HttpSession session = request.getSession();
        String idUsuLog = session.getAttribute("id_usu").toString();
        
        try {
            // PRINTANDO ROTA
            System.out.println("/cadastro");
            
            // CRIANDO NOVO MODELO DE USUARIO
            Usuario usuario = new Usuario();
            
            // INSERINDO VALORES VINDOS DA REQUISIÇÃO NO MODELO USUARIO
            usuario.setId(Integer.parseInt(idUsuLog));
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setEndereco(request.getParameter("endereco"));
            usuario.setAdministrador(false);
            
            // CRIANDO UMA NOVA INSTANCIA DE USUARIO DAO
            UsuarioDAO usuDAO = new UsuarioDAO();
            
            // CHAMANDO FUNÇÃO 'inserirUsuario' e PASSANDO MODELO DE USUARIO CRIADO
            // DEPOIS ATRIBUINDO O RESULTADO DA FUNÇÃO EM UMA VARIAVEL BOOLEANA
            boolean alterarUsuario = usuDAO.alterarUsuario(usuario);
            
            // OPERADOR TERNARIO VERIFICANDO SE VARIAVEL É TRUE OU FALSE
            String status = alterarUsuario ? "sucess" : "failed";
            
            session.setAttribute("nome_usu", request.getParameter("nome"));
            
            // SETANDO UM ATRIBUTO DE NOME SUCESSO COM UM VALOR DEPENDENDO DO RESULTADO DA INSERÇÃO NO REQUEST
            request.setAttribute("status", status);
            
            // ENCAMINHANDO PAGINA
            expeditor.forward(request, response); 
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
