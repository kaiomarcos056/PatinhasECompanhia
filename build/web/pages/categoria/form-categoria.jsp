<%@page import="Model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <% 
            String label = "Cadastrar";
            String action = "insert";
            String value = "";
            
            String rota = (String) request.getAttribute("rota");
            Categoria categoria = (Categoria) request.getAttribute("categoria");
            
            if("/edit".equals(rota)){
                label = "Alterar";
                action = "update?id="+categoria.getId();
                value = categoria.getDescricao();
            }
        %>
    </head>
    <body>
        <a href="list">Voltar</a>
        <h1><%= label %> Categoria</h1>
        <form action="<%= action %>" method="POST">
            <label>Categoria</label><br>
            <input type="text" name="categoria" placeholder="Nome da categoria..." value="<%= value %>" required><br><br>
            <input type="submit" value="<%= label %> Categoria">
        </form>
    </body>
</html>
