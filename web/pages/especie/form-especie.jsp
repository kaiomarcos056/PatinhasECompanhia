<%@page import="Model.Especie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String label = "Cadastrar";
    String action = "insert";
    String value = "";

    String rota = (String) request.getAttribute("rota");
    Especie especie = (Especie) request.getAttribute("especie");

    if ("/edit".equals(rota)) {
        label = "Alterar";
        action = "update?id=" + especie.getId();
        value = especie.getDescricao();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <%@ include file="/includes/valida-formulario.jsp" %>
        <title>Especie</title>

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
                    <h1><%= label%> Especie</h1>
                </div>
                
                <br>
                
                <!-- FORMULARIO -->
                <form id="formularioEspecie" action="<%= action%>" method="POST">
                    <label>Especie</label><br>
                    <input type="text" name="especie" placeholder="Nome da especie..." value="<%= value%>" required><br><br>
                    <input type="submit" value="<%= label%> Especie">
                </form>
            </div>
        </section>

        <!-- ESPAÇAMENTO FINAL -->
        <br><br><br><br>
    </body>
</html>