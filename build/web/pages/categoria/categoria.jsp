<%@page import="java.util.List"%>
<%@page import="Model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categoria</title>
        
        <%
            String msg = (String) session.getAttribute("msg");
            List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
        %>
    </head>
    <body>
        <a href="<%=request.getContextPath()%>/dashboard/">Voltar</a>
        <h1>Categorias</h1>
        <a href="new">Nova Categoria</a>
        <br><br>
        
        <form action="list" method="GET">
            <input type="text" name="categoria" placeholder="Categoria">
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
                    <th>Categoria</th>
                    <th colspan="2">Ação</th>
                </tr>
                <%
                    if(categorias == null){
                %>
                    <tr>
                        <td colspan="3">Categorias não encontradas.</td>
                    </tr>
                <% } else {
                    for (Categoria categoria : categorias) {
                %>
                    <tr>
                        <td><%= categoria.getId() %></td>
                        <td><%= categoria.getDescricao()%></td>
                        <td>
                            <a href="edit?id=<%= categoria.getId() %>" class="edit">
                                Editar
                            </a>
                        </td>
                        <td>
                            <a href="delete?id=<%= categoria.getId() %>" class="remove">
                                Excluir
                            </a>
                        </td>
                    </tr>
                <% } } %>
        </table>
    </body>
</html>