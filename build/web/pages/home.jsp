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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <!-- FONTES -->
        <%@ include file="/includes/fontes.jsp" %>
        <title>Dashboard</title>
    </head>
    <body>
        <div class="home-container" style="display: flex;">
            
            <%@ include file="/includes/sidebar-home-adm.jsp" %>
            
            <div class="home-content" style="width: 100%; height: 100vh; display: flex; flex-direction: column;">
                
                <div style="width: 100%; padding: 10px; box-shadow: rgba(0, 0, 0, 0.24) 0px 4px 2px -2px; z-index: 8;">
                    <h3>Bem-Vindo <%= nomeUsuario %></h3>
                </div>
                
                <div style="width: 100%; padding: 10px; background-color: #04C4D9; color: white;">
                    <h3><i class="fa-solid fa-house"></i>&nbsp;&nbsp; Home</h3>
                </div>
                
                <div style="width: 100%; padding: 10px; background-color: #F8F8F8; flex-grow: 1;">
                    
                </div>
                
            </div>

        </div>
        
    </body>
</html>
