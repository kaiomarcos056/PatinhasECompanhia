<%@page import="java.util.List"%>
<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>

        <title>Usuario</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <br>
        <label style="padding-left: 20px;">Bem-Vindo $NOME.</label>
        <br><br>
        
        <section style="display: flex; gap: 20px;"> 
            
            <!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <div class="home-content" style="width: 100%;">
                <div style="display: flex; align-content: center; justify-content: space-between;">
                    <h1>Usuarios</h1>
                    <a href="new" style="align-self: center;">Novo Usuario</a>
                </div>
                
                <br>

                <form action="list" method="GET" style="display: flex; gap: 20px;">
                    <input type="text" name="usuario" placeholder="Usuario">
                    <button type="submit" class="">Pesquisar</button>
                </form>
                <br>

                <% if (msg != null) {%>
                <p><%= msg%></p>
                <%
                        session.removeAttribute("msg");
                    }
                %>

                <table class="tabela-home">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Endereco</th>
                        <th>Email</th>
                        <th>Senha</th>
                        <th>Administrador</th>
                        <th colspan="2">Ação</th>
                    </tr>
                    <%
                        if (usuarios == null) {
                    %>
                    <tr>
                        <td colspan="3">Usuarios não encontradas.</td>
                    </tr>
                    <% } else {
                        for (Usuario usuario : usuarios) {
                    %>
                    <tr>
                        <td><%= usuario.getId()%></td>
                        <td><%= usuario.getNome()%></td>
                        <td><%= usuario.getEndereco()%></td>
                        <td><%= usuario.getEmail()%></td>
                        <td><%= usuario.getSenha()%></td>
                        <td><%= usuario.isAdministrador()%></td>
                        <td>
                            <a href="edit?id=<%= usuario.getId()%>" class="edit">
                                Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= usuario.getId()%>" class="remove">
                                Excluir
                            </a>
                        </td>
                    </tr>
                    <% }
                    }%>
                </table>
            </div>
        </section>
    </body>
</html>