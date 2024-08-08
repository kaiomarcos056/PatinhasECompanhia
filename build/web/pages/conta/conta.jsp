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
                <% if (msg != null) {%>
                    <p><%= msg%></p>
                <%
                    session.removeAttribute("msg");
                    }
                %>
                
                <form action="update" method="POST">
                    <label>Nome</label><br>
                    <input type="text" name="nome" value="<%= usuario.getNome()%>" required><br><br>
                    
                    <label>Endereço</label><br>
                    <input type="text" name="endereco" value="<%= usuario.getEndereco()%>""><br><br>
                    
                    <label>E-mail</label><br>
                    <input type="text" name="email" value="<%= usuario.getEmail()%>" required><br><br>
                    
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
