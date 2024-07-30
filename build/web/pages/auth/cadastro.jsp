<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Cadastro</title>
        <%
            String msg = (String) session.getAttribute("msg");
        %>
    </head>
    <body>
        <br><br>

        <%@ include file="/includes/menu-login.jsp" %>

        <br><br>

        <% if (msg != null) {%>
        <p><%= msg%></p>
        <%
                session.removeAttribute("msg");
            }
        %>

        <section>
            <center>
                <h1>Crie uma conta</h1>
                <p>Complete os campos com as sua informações</p>
            </center>
            
            <form action="cadastrar" method="POST">
                <label>Nome</label><br>
                <input type="text" name="nome" required><br><br>

                <label>Endereço</label><br>
                <input type="text" name="endereco"><br><br>

                <label>E-mail</label><br>
                <input type="text" name="email" required><br><br>

                <label>Senha</label><br>
                <input type="password" name="senha" required><br><br>

                <label>Confirmar Senha</label><br>
                <input type="password" name="confirm-senha" required><br><br>
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
