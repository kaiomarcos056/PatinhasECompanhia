<%@page import="Model.Produto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    
    DecimalFormat df = new DecimalFormat("###,##0.00");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Favorito</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>
        
        <section class="home-section"> 
            <!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <!-- CONTEUDO -->
            <div class="home-content">
                <!-- TITULO DA PAGINA -->
                <div class="home-titulo">
                    <h1>Favoritos</h1>
                </div>
                
                
                <br><br>
                
                <div style="display: flex; gap: 30px;">
                <% for (Produto produto : produtos) {%>
                    <form action="${pageContext.request.contextPath}/carrinho/add">
                        <input type="hidden" name="produtoID" value="<%= produto.getId()%>" />

                        <div class="box-produto">
                            <div class="box-produto-item" style="align-self: end;" >
                                <a href="${pageContext.request.contextPath}/dashboard/favorito/remove?id=<%= produto.getId() %>"><i class="fa-solid fa-heart"></i></a>
                            </div>
                            <div class="box-produto-item" style="align-self: center;">
                                <img src="${pageContext.request.contextPath}/assets/produtos/<%= produto.getFoto()%>">
                            </div>
                            
                            <div style="flex: 1; display: flex; flex-direction: column; justify-content: center;">
                                <div class="box-produto-item"><h3><%= produto.getNome()%></h3></div>
                                <div class="box-produto-item"><h2>R$ <%= df.format(produto.getValor()) %></h2></div>
                            </div>
                            
                            
                            <div class="box-produto-item" style="align-self: end;">
                                <button type="submit">
                                    <i class="fa-solid fa-cart-shopping"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                <% } %>
                </div>
        </section>
    </body>
</html>
