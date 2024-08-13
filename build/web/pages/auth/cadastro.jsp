<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <%@ include file="/includes/valida-formulario.jsp" %>
        <title>Cadastro</title>
    </head>
    <body>
        <br><br>

        <%@ include file="/includes/menu-login.jsp" %>

        <br><br>

        <section>
            <center>
                <h1>Crie uma conta</h1>
                <p>Complete os campos com as sua informações</p>
            </center>
            <% if (msg != null) {%> <br><p class="sucesso"><%= msg%></p><br> <% session.removeAttribute("msg"); } %>
            <form id="formularioCadastro" action="cadastrar" method="POST">
                <label>Nome</label><br>
                <input type="text" name="nome" placeholder="Fula de Tal"><br><br>

                <label>Endereço</label><br>
                <input type="text" name="endereco" placeholder="Casa do fulano"><br><br>

                <label>E-mail</label><br>
                <input type="text" name="email" placeholder="fulano@email.com"><br><br>

                <label>Senha</label><br>
                <input type="password" name="senha" id="senha" placeholder="********"><br><br>

                <label>Confirmar Senha</label><br>
                <input type="password" name="confirmaSenha"><br><br>
                <br>
                <label>Padrão de Senha</label>
                <ul>
                    <li>Mínimo 8 caracteres</li>
                    <li>Letra Minúscula</li>
                    <li>Letra Maiúscula</li>
                    <li>Números</li>
                </ul>
                <br><br><br>
                <div style="display: flex; align-items: center; justify-content: space-between;">
                    <a href="<%=request.getContextPath()%>/auth/login" class="btn-link">Já tenho uma conta</a>
                    <input type="submit" value="Criar uma conta">
                </div>

            </form>
        </section>
        <br><br><br><br><br><br><br>

    </body>
</html>
