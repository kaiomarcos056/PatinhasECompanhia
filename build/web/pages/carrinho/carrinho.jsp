<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<CarrinhoItem> carrinho = (List<CarrinhoItem>) request.getAttribute("carrinho");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/js/carrinho.js"></script>
        <title>Carrinho</title>
    </head>
    <body>
        <%@ include file="/includes/menu.jsp" %>
        <br>
        <div >
            <section style="width: 1186px; margin: 0 auto;">
                <div class="tabs-bar">
                    <button class="tab-button selected" onclick="openCity('lista-pedidos');changeBorder(this)">Carrinho</button>
                    
                    <% if (carrinho != null && carrinho.size() > 0) { %>
                    <button class="tab-button" onclick="openCity('identificacao');changeBorder(this)">Identificação</button>
                    <button class="tab-button" onclick="openCity('entrega');changeBorder(this)">Entrega</button>
                    <button class="tab-button" onclick="openCity('pagamento');changeBorder(this)">Pagamento</button>
                    <% } %>

                </div>
                
                <%@ include file="list-carrinho.jsp" %>
                
                <% if (carrinho != null && carrinho.size() > 0) { %>
                    <%@ include file="identificacao.jsp" %>
                    <%@ include file="entrega.jsp" %>
                    <%@ include file="pagamento.jsp" %>
                    <%@ include file="final-compra.jsp" %>
                <% } %>
                
            </section>
        </div>
    </body>
</html>
