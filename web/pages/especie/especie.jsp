<%@page import="java.util.List"%>
<%@page import="Model.Especie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Especie</title>
        
        <%
            String msg = (String) session.getAttribute("msg");
            List<Especie> especies = (List<Especie>) request.getAttribute("especies");
        %>
    </head>
    <body>
        <a href="<%=request.getContextPath()%>/dashboard/">Voltar</a>
        <h1>Especies</h1>
        <a href="new">Nova Especie</a>
        <br><br>
        
        <form action="list" method="GET">
            <input type="text" name="especie" placeholder="Especie">
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
                    <th>Especie</th>
                    <th colspan="2">Ação</th>
                </tr>
                <%
                    if(especies == null){
                %>
                    <tr>
                        <td colspan="3">Especies não encontradas.</td>
                    </tr>
                <% } else {
                    for (Especie especie : especies) {
                %>
                    <tr>
                        <td><%= especie.getId() %></td>
                        <td><%= especie.getDescricao()%></td>
                        <td>
                            <a href="edit?id=<%= especie.getId() %>" class="edit">
                                Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= especie.getId() %>" class="remove">
                                Excluir
                            </a>
                        </td>
                    </tr>
                <% } } %>
        </table>
    </body>
</html>