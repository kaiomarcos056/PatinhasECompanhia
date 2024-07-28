<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Minha Conta</title>
    </head>
    <body>
        <a href="<%=request.getContextPath()%>/dashboard/">Voltar</a>
        <h1>Minha Conta</h1>
        <br>
        <% if(msg != null){ %>
                <p><%= msg %></p>
        <%      
                session.removeAttribute("msg"); 
            }
        %>
        <form action="update" method="POST">
            <label>Nome</label><br>
            <input type="type" name="nome" value="<%= usuario.getNome() %>" required><br><br>
            <label>Endereço</label><br>
            <input type="type" name="endereco" value="<%= usuario.getEndereco()%>""><br><br>
            <label>E-mail</label><br>
            <input type="type" name="email" value="<%= usuario.getEmail()%>" required><br><br>
            <label>Senha</label><br>
            <input type="password" name="senha" value="<%= usuario.getSenha()%>" required><br><br>
            <label>Confirmar Senha</label><br>
            <input type="password" name="confirm-senha" required><br><br>
            <label>Padrão de Senha</label>
            <ul>
                <li>Mínimo 8 caracteres</li>
                <li>Letra Minúscula</li>
                <li>Letra Maiúscula</li>
                <li>Números</li>
            </ul>
            <a href="delete">Excluir Conta</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="Alterar Dados">
        </form>
    </body>
</html>
