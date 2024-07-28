<%@page import="Model.Marca"%>
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
            Marca marca = (Marca) request.getAttribute("marca");
            
            if("/edit".equals(rota)){
                label = "Alterar";
                action = "update?id="+marca.getId();
                value = marca.getDescricao();
            }
            
            //Categoria categoria = (Categoria) request.getAttribute("categoria");
            
            
        %>
    </head>
    <body>
        <a href="list">Voltar</a>
        <h1><%= label %> Marca</h1>
        <form action="<%= action %>" method="POST">
            <label>Marca</label><br>
            <input type="text" name="marca" placeholder="Nome da marca..." value="<%= value %>" required><br><br>
            <input type="submit" value="<%= label %> Marca">
        </form>
    </body>
</html>
