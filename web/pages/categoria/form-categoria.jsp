<%@page import="Model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String label = "Cadastrar";
    String action = "insert";
    String value = "";

    String rota = (String) request.getAttribute("rota");
    Categoria categoria = (Categoria) request.getAttribute("categoria");

    if ("/edit".equals(rota)) {
        label = "Alterar";
        action = "update?id=" + categoria.getId();
        value = categoria.getDescricao();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <%@ include file="/includes/valida-formulario.jsp" %>
        <title>Categoria</title>

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
                    <h1><%= label%> Categoria</h1>
                </div>
                
                <br>
                
                <!-- FORMULARIO -->
                <form id="formularioCategoria" action="<%= action%>" method="POST">
                    <label>Categoria</label><br>
                    <input type="text" name="categoria" placeholder="Nome da categoria..." value="<%= value%>" required><br><br>
                    <input type="submit" value="<%= label%> Categoria">
                </form>
            </div>
        </section>

        <!-- ESPAÇAMENTO FINAL -->
        <br><br><br><br>
    </body>
</html>
