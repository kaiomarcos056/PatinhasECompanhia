<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("nome_usu") == null){
        response.sendRedirect("../index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Alterar Cadastro</h1>
        
        <% 
        // Obtenha o usuário da sessão
        Object usuario = session.getAttribute("usu-dados");
        
        // Verifique se o usuário não é nulo
        if (usuario != null) {
            // Casting do objeto para o tipo apropriado (supondo que seja uma instância de uma classe Usuario)
            Usuario meuUsuario = (Usuario) usuario;
    %>
    
    <form action="updateUsuario" method="post">
        <label>Nome</label><br>
        <input type="text" name="nome" value="<%= meuUsuario.getNome() %>"><br>
            <label>Email</label><br>
            <input type="text" name="email" value="<%= meuUsuario.getEmail() %>"><br>
            <label>Senha</label><br>
            <input type="text" name="senha" value="<%= meuUsuario.getSenha() %>"><br>
            <label>Endereco</label><br>
            <input type="text" name="endereco" value="<%= meuUsuario.getEndereco() %>"><br>
            
        <input type="submit" value="Atualizar">
    </form>
    
    <% 
        } else {
            // Usuário não encontrado na sessão
            out.println("<p>Usuário não encontrado na sessão.</p>");
        }
    %>
      
    </body>
</html>
