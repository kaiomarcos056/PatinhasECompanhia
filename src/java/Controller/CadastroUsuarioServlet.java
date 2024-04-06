package Controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
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
        //PrintWriter out = response.getWriter();
        //out.print("IMPRIMINDO...");
        
        /*
         * 'RequestDispatcher' é uma interface que fornece funcionalidades 
         * para encaminhar (forward) ou incluir (include) 
         * o conteúdo de uma solicitação HTTP para outro recurso 
         * dentro do mesmo contexto de servlet ou da mesma aplicação web.
        */
        RequestDispatcher expeditor = request.getRequestDispatcher("cadastro.jsp");

        try {
            // PRINTANDO ROTA
            System.out.println("/cadastro");
            
            // CRIANDO NOVO MODELO DE USUARIO
            Usuario usuario = new Usuario();
            
            // INSERINDO VALORES VINDOS DA REQUISIÇÃO NO MODELO USUARIO
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setEndereco(request.getParameter("endereco"));
            usuario.setAdministrador(false);
            
            // CRIANDO UMA NOVA INSTANCIA DE USUARIO DAO
            UsuarioDAO novoUsuario = new UsuarioDAO();
            
            // CHAMANDO FUNÇÃO 'inserirUsuario' e PASSANDO MODELO DE USUARIO CRIADO
            // DEPOIS ATRIBUINDO O RESULTADO DA FUNÇÃO EM UMA VARIAVEL BOOLEANA
            boolean inserirUsuario = novoUsuario.cadastrarUsuario(usuario);
            
            // OPERADOR TERNARIO VERIFICANDO SE VARIAVEL É TRUE OU FALSE
            String status = inserirUsuario ? "sucess" : "failed";
            
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
