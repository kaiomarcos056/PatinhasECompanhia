<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%
    String nomeUsuario = "";
    Usuario usuario = (Usuario) session.getAttribute("usuario_logado");
    if (usuario != null) {
        nomeUsuario = usuario.getNome();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        
        <title>Dashboard</title>
    </head>
    <body>
        
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <br>
        <label style="padding-left: 20px;">Bem-Vindo $NOME.</label>
        <br><br>
        
        <section>            
            <!<!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>
            
            <div class="home-content">
                
            </div>
        </section>
        
    </body>
</html>
