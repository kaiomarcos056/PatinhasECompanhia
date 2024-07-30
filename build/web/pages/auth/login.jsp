<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Login</title>
    </head>
    <body>
        <br>
        <br>

        <%@ include file="/includes/menu-login.jsp" %>

        <br>
        <br>
        <br>

        <section>
            <div style="text-align: center;">
                <h1>Acesse sua conta</h1>
                <p>Informe seu e-mail e senha de cadastro</p>
            </div>
            
            <br>
            <br>
            
            <% if (msg != null) {%> <p class="msg-erro"><%= msg%></p><br> <% }%>
            
            <form action="logar" method="POST">
                <label>E-mail</label><br>
                <input type="text" name="email" placeholder="Kelvin Erick"><br><br>
                
                <label>Senha</label><br>
                <input type="password" name="senha" placeholder="*******"><br>
                
                <br><br><br><br>
                
                <div style="display: flex; align-items: center; justify-content: space-between;">
                    <a href="<%=request.getContextPath()%>/auth/cadastro" class="btn-link">Criar uma conta</a>
                    <input type="submit" value="Acessar">
                </div>
            </form>
            
        </section>
    </body>
</html>
