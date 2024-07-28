<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
        <%
            String msg = (String) session.getAttribute("msg");
        %>
    </head>
    <body>
        <%@ include file="/includes/menu-login.jsp" %>
        
        <h1>Crie uma conta</h1>
        <p>Complete os campos com as sua informações</p>
        
        <% if(msg != null){ %>
                <p><%= msg %></p>
        <%      
                session.removeAttribute("msg"); 
            }
        %>
        
        <form action="cadastrar" method="POST">
            <label>Nome</label><br>
            <input type="type" name="nome" required><br>
            <label>Endereço</label><br>
            <input type="type" name="endereco"><br>
            <label>E-mail</label><br>
            <input type="type" name="email" required><br>
            <label>Senha</label><br>
            <input type="password" name="senha" required><br>
            <label>Confirmar Senha</label><br>
            <input type="password" name="confirm-senha" required><br>
            <label>Padrão de Senha</label>
            <ul>
                <li>Mínimo 8 caracteres</li>
                <li>Letra Minúscula</li>
                <li>Letra Maiúscula</li>
                <li>Números</li>
            </ul>
            <a href="<%=request.getContextPath()%>/auth/login">Já tenho uma conta</a>
            <input type="submit" value="Criar uma conta">
        </form>
    </body>
</html>
