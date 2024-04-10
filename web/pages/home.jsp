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
        <title>Home</title>
    </head>
    <body>
        <h1>Seja Bem-Vindo <%= session.getAttribute("nome_usu") %></h1>
        <form action="alterarCadastro" method="POST">
            <input type="submit" value="alterar cadastro" />
        </form>
        
        <form action="logout" method="GET">
            <input type="submit" value="sair" />
        </form>
    </body>
</html>
