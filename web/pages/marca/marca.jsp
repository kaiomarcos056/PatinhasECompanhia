<%@page import="java.util.List"%>
<%@page import="Model.Marca"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marca</title>
        
        <%
            String msg = (String) session.getAttribute("msg");
            List<Marca> marcas = (List<Marca>) request.getAttribute("marcas");
        %>
    </head>
    <body>
        <a href="<%=request.getContextPath()%>/dashboard/">Voltar</a>
        <h1>Marcas</h1>
        <a href="new">Nova Marca</a>
        <br><br>
        
        <form action="list" method="GET">
            <input type="text" name="marca" placeholder="Marca">
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
                    <th>Marca</th>
                    <th colspan="2">Ação</th>
                </tr>
                <%
                    if(marcas == null){
                %>
                    <tr>
                        <td colspan="3">Marcas não encontradas.</td>
                    </tr>
                <% } else {
                    for (Marca marca : marcas) {
                %>
                    <tr>
                        <td><%= marca.getId() %></td>
                        <td><%= marca.getDescricao()%></td>
                        <td>
                            <a href="edit?id=<%= marca.getId() %>" class="edit">
                                Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= marca.getId() %>" class="remove">
                                Excluir
                            </a>
                        </td>
                    </tr>
                <% } } %>
        </table>
    </body>
</html>