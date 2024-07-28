<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%@ include file="/includes/menu-login.jsp" %>
        <h1>Acesse sua conta</h1>
        <p>Informe seu e-mail e senha de cadastro</p>
        <% if(msg != null){ %>
            <p><%= msg %></p>
        <% } %>
        <form action="logar" method="POST">
            <label>E-mail</label><br>
            <input type="type" name="email"><br>
            <label>Senha</label><br>
            <input type="password" name="senha"><br>
            <a href="<%=request.getContextPath()%>/auth/cadastro">Criar uma conta</a>
            <input type="submit" value="Acessar">
        </form>
    </body>
</html>
