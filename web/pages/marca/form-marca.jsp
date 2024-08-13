<%@page import="Model.Marca"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String label = "Cadastrar";
    String action = "insert";
    String value = "";

    String rota = (String) request.getAttribute("rota");
    Marca marca = (Marca) request.getAttribute("marca");

    if ("/edit".equals(rota)) {
        label = "Alterar";
        action = "update?id=" + marca.getId();
        value = marca.getDescricao();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <%@ include file="/includes/valida-formulario.jsp" %>
        <title>Marca</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>
        
        <!-- SEÇÃO -->
        <section class="home-section"> 
            <!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <!-- CONTEUDO -->
            <div class="home-content">
                <!-- TITULO -->
                <div class="home-titulo">
                    <h1><%= label%> Marca</h1>
                </div>
                
                <br>
                
                <!-- FORMULARIO -->
                <form id="formularioMarca" action="<%= action%>" method="POST">
                    <label>Marca</label><br>
                    <input type="text" name="marca" placeholder="Nome da marca..." value="<%= value%>"><br><br>
                    <input type="submit" value="<%= label%> Marca">
                </form>
            </div>
        </section>

        <!-- ESPAÇAMENTO FINAL -->
        <br><br><br><br>
    </body>
</html>
