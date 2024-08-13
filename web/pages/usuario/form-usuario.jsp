<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String label = "Cadastrar";
    String action = "insert";

    String nome = "";
    String endereco = "";
    String email = "";
    String senha = "";

    boolean isAdm = false;

    String rota = (String) request.getAttribute("rota");
    Usuario usuario = (Usuario) request.getAttribute("usuario");

    if ("/edit".equals(rota)) {
        label = "Alterar";
    }
    if (usuario != null) {
        action = "update?id=" + usuario.getId();
        nome = usuario.getNome();
        endereco = usuario.getEndereco();
        email = usuario.getEmail();
        senha = usuario.getSenha();
        isAdm = usuario.isAdministrador();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <%@ include file="/includes/valida-formulario.jsp" %>
        <title>Usuario</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>

        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>

        <section style="display: flex; gap: 20px;"> 
            <!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <div class="home-content" style="width: 100%;">
                
                <h1><%= label%> Usuario</h1>
                <br>
                
                <form id="formCriaUsuario" action="<%= action%>" method="POST">
                    <label>Nome</label><br>
                    <input type="text" name="nome" value="<%= nome%>" placeholder="Kelvin Erick"><br><br>

                    <label>Administrador</label><br>
                    <select name="adm">
                        <% if (isAdm) { %>
                        <option value="false">não</option>
                        <option value="true" selected>sim</option>
                        <% } else { %>
                        <option value="false" selected>não</option>
                        <option value="true">sim</option>
                        <% }%>
                    </select><br><br>

                    <label>Endereço</label><br>
                    <input type="text" name="endereco" value="<%= endereco%>"  placeholder="Rua A"><br><br>

                    <label>E-mail</label><br>
                    <input type="text" name="email" value="<%= email%>" placeholder="erick@email.com"><br><br>

                    <label>Senha</label><br>
                    <input type="password" name="senha" value="<%= senha%>" id="senha" placeholder="******"><br><br>

                    <label>Confirmar Senha</label><br>
                    <input type="password" name="confirmaSenha" placeholder="******"><br><br>

                    <label>Padrão de Senha</label>
                    <ul>
                        <li>Mínimo 8 caracteres</li>
                        <li>Letra Minúscula</li>
                        <li>Letra Maiúscula</li>
                        <li>Números</li>
                    </ul>
                    <br><br>
                    <input type="submit" value="<%= label%> Usuario">
                </form>
            </div>
        </section>
                
                <br><br><br><br>
    </body>
</html>