<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
%>
<!DOCTYPE html>
<html>
    <head>

        <%@ include file="/includes/header.jsp" %>
        
        <%@ include file="/includes/valida-formulario.jsp" %>
        
        <title>Minha Conta</title>

    </head>
    <body>

        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>
        
        <section style="display: flex; gap: 20px;">
            <!<!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <div class="home-content" style="width: 100%;">

                <h1>Minha Conta</h1>
                <br>
                <% if (msg != null) {%> <p class="sucesso"><%= msg%></p><br> <% session.removeAttribute("msg"); } %>
                
                <form id="formConta" action="update" method="POST">
                    <label>Nome</label><br>
                    <input type="text" name="nome" value="<%= usuario.getNome()%>" ><br><br>
                    
                    <label>Endereço</label><br>
                    <input type="text" name="endereco" value="<%= usuario.getEndereco()%>""><br><br>
                    
                    <label>E-mail</label><br>
                    <input type="text" name="email" value="<%= usuario.getEmail()%>" ><br><br>
                    
                    <label>Senha</label><br>
                    <input type="password" id="senha" name="senha" value="<%= usuario.getSenha()%>" ><br><br>
                    
                    <label>Confirmar Senha</label><br>
                    <input type="password" name="confirmaSenha" ><br><br>
                    <label>Padrão de Senha</label>
                    <ul>
                        <li>Mínimo 8 caracteres</li>
                        <li>Letra Minúscula</li>
                        <li>Letra Maiúscula</li>
                        <li>Números</li>
                    </ul>
                    <br><br>
                    <div style="display: flex; align-items: center; justify-content: space-between;">
                        <a href="delete" class="btn-link">Excluir Conta</a>
                        <input type="submit" value="Alterar Dados">
                    </div>
                </form>

            </div>
        </section>

        <br><br><br><br>

    </body>
</html>
