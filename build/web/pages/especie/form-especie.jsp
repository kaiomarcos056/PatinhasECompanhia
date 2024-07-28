<%@page import="Model.Especie"%>
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
            Especie especie = (Especie) request.getAttribute("especie");
            
            if("/edit".equals(rota)){
                label = "Alterar";
                action = "update?id="+especie.getId();
                value = especie.getDescricao();
            }         
        %>
    </head>
    <body>
        <a href="list">Voltar</a>
        <h1><%= label %> Especie</h1>
        <form action="<%= action %>" method="POST">
            <label>Especie</label><br>
            <input type="text" name="especie" placeholder="Nome da especie..." value="<%= value %>" required><br><br>
            <input type="submit" value="<%= label %> Especie">
        </form>
    </body>
</html>