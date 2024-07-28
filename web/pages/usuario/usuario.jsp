<%@page import="java.util.List"%>
<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
        
        <%
            String msg = (String) session.getAttribute("msg");
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        %>
    </head>
    <body>
        <a href="<%=request.getContextPath()%>/dashboard/">Voltar</a>
        <h1>Usuarios</h1>
        <a href="new">Novo Usuario</a>
        <br><br>
        
        <form action="list" method="GET">
            <input type="text" name="usuario" placeholder="Usuario">
            <button type="submit" class="">Pesquisar</button>
        </form>
        <br>
        
        <% if(msg != null){ %>
                <p><%= msg %></p>
        <%      
                session.removeAttribute("msg"); 
            }
        %>
        
        <table border="1">
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
                    if(usuarios == null){
                %>
                    <tr>
                        <td colspan="3">Usuarios não encontradas.</td>
                    </tr>
                <% } else {
                    for (Usuario usuario : usuarios) {
                %>
                    <tr>
                        <td><%= usuario.getId() %></td>
                        <td><%= usuario.getNome()%></td>
                        <td><%= usuario.getEndereco()%></td>
                        <td><%= usuario.getEmail()%></td>
                        <td><%= usuario.getSenha()%></td>
                        <td><%= usuario.isAdministrador()%></td>
                        <td>
                            <a href="edit?id=<%= usuario.getId() %>" class="edit">
                                Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= usuario.getId() %>" class="remove">
                                Excluir
                            </a>
                        </td>
                    </tr>
                <% } } %>
        </table>
    </body>
</html>