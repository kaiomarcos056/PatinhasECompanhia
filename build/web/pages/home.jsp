<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Dashboard</title>
    </head>
    <body>
        
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>
        
        <section>            
            <!<!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>
            
            <div class="home-content">
                
            </div>
        </section>
        
    </body>
</html>
