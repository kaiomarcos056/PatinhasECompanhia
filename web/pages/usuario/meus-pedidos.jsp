<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.ItemPedido"%>
<%@page import="Model.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
    List<ItemPedido> itens = (List<ItemPedido>) request.getAttribute("itens");
    DecimalFormat df = new DecimalFormat("###,##0.00");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Meus Pedidos</title>
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
                    <h1>Meus Pedidos</h1>
                </div>
                
                <br>

                <!-- PESQUISA -->
                <form action="list" method="GET" class="form-pesquisa">
                    <input type="number" name="pedido" placeholder="Nro. Pedido">
                    <button type="submit" class="btn-home" >Pesquisar</button>
                </form>
                
                <br>
                
                <!-- TABELA -->
                <% if(pedidos == null){ %>
                    Pedidos não encontradas.
                <% } else { %>
                    <% for (Pedido pedido : pedidos) { %>
                    <div class="content-meus-pedidos">
                        <div class="meus-pedidos-header">
                            <div class="meus-pedidos-header-item">
                                <label>Data Pedido</label>
                                <label><%= pedido.getDataPedido() %></label>
                            </div>
                            <div class="meus-pedidos-header-item">
                                <label>Total</label>
                                <label>R$ <%= df.format(pedido.getValorTotal()) %></label>
                            </div>
                            <div class="meus-pedidos-header-item">
                                <label>Nº Pedido <%= pedido.getIdPedido()%></label>
                            </div>
                        </div>
                        <% for (ItemPedido item : itens) { %>
                        <% if(item.getIdPedido() == pedido.getIdPedido()) { %>
                        <div class="meus-pedidos-content">
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getFotoProduto() %>" width="90" height="90">
                            <label><%= item.getNomeProduto() %></label>
                        </div>
                        <% } %>
                        <% } %>
                    </div>
                    <br>
                    <% } %>
                <% } %>
               
            </div>
        </section>
                
        <br><br><br><br>    
    </body>
</html>
