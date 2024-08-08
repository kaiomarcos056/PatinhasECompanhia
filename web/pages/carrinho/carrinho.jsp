<%@page import="Model.CarrinhoItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    List<CarrinhoItem> carrinho = (List<CarrinhoItem>) request.getAttribute("carrinho"); 

    double frete = 4.0;
    double total = frete;
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <script src="${pageContext.request.contextPath}/js/carrinho.js"></script> 
        <style>
            .tabs-bar{
                display: flex;
                justify-content: start;
                margin-bottom: 15px;
                width: 100%;
            }
            .tab-button{
                border-radius: 10px 10px 0px 0px;
                padding: 8px 8px 10px 8px;
                font-size: 20px;
                background-color: white;
                border: none;
                text-align: center;
                font-weight: 700;
                border-bottom: 2px solid gray;
                width: 25%;
                cursor: pointer;
            }
            .selected {
                color: var(--azul);
                border-bottom: 4px solid var(--azul);;
            }
        </style>
        <title>Carrinho</title>
    </head>
    <body>
        <%@ include file="/includes/menu.jsp" %>
        <br><br><br>
        <section>
            <div class="tabs-bar">
                <button class="tab-button selected" onclick="openCity('lista-pedidos');changeBorder(this)">Carrinho</button>
                <% if (carrinho != null && carrinho.size() > 0) { %>
                <button class="tab-button" onclick="openCity('identificacao');changeBorder(this)">Identificação</button>
                <button class="tab-button" onclick="openCity('entrega');changeBorder(this)">Entrega</button>
                <button class="tab-button" onclick="openCity('pagamento');changeBorder(this)">Pagamento</button>
                <% } %>
            </div>
            <div>
                <%@ include file="list-carrinho.jsp" %>
                <%@ include file="identificacao.jsp"%>
                <%@ include file="entrega.jsp" %>
                <%@ include file="pagamento.jsp" %>
            </div>
        </section>
    </body>
</html>
