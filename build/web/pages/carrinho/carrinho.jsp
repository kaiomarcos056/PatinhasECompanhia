<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.CarrinhoItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    List<CarrinhoItem> carrinho = (List<CarrinhoItem>) request.getAttribute("carrinho"); 
    String msg = (String) session.getAttribute("msg");
    
    DecimalFormat df = new DecimalFormat("###,##0.00");
    
    double frete = 4.0;
    double total = frete;
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        
        <%@ include file="/includes/valida-formulario.jsp" %>
        
        <script src="${pageContext.request.contextPath}/js/carrinho.js"></script> 
        
        <title>Carrinho</title>
    </head>
    <body>
        <%@ include file="/includes/menu.jsp" %>
        
        <br><br><br>
        
        <section>
            <div class="tabs-bar">
                <button class="tab-button selected" onclick="abrirAba('lista-pedidos'); changeBorder(this)">Carrinho</button>
                
                <% if (carrinho != null && carrinho.size() > 0) { %>
                
                <button class="tab-button" onclick="abrirAba('identificacao'); changeBorder(this)">Identificação</button>
                <button class="tab-button" onclick="abrirAba('entrega'); changeBorder(this)">Entrega</button>
                <button class="tab-button" onclick="abrirAba('pagamento'); changeBorder(this)">Pagamento</button>
                
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
