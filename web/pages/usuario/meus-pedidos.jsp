<%@page import="Model.ItemPedido"%>
<%@page import="Model.Pedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
    List<ItemPedido> itens = (List<ItemPedido>) request.getAttribute("itens");
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
                    <div style="width: 100%; border: 2px solid orange; display: flex; flex-direction: column; border-radius: 15px;">
                        <div style="width: 100%; background-color: orange; color: white; padding: 10px 15px; display: flex; justify-content: space-between; border-radius: 10px 10px 0px 0px;">
                            <div style="display: flex; flex-direction: column;">
                                <label style="font-size: 16px;">Data Pedido</label>
                                <label style="font-size: 16px;"><%= pedido.getDataPedido() %></label>
                            </div>
                            <div style="display: flex; flex-direction: column;">
                                <label style="font-size: 16px;">Total</label>
                                <label style="font-size: 16px;">R$ <%= pedido.getValorTotal() %></label>
                            </div>
                            <div style="display: flex; flex-direction: column;">
                                <label style="font-size: 16px;">Nº Pedido <%= pedido.getIdPedido()%></label>
                            </div>
                        </div>
                        <% for (ItemPedido item : itens) { %>
                        <% if(item.getIdPedido() == pedido.getIdPedido()) { %>
                        <div style="width: 100%; height: 120px; display: flex; align-items: center;">
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getFotoProduto() %>" width="90" height="90">
                            <label><%= item.getNomeProduto() %></label>
                        </div>
                        <% } %>
                        <% } %>
                    </div>
                    <br>
                    <% } %>
                <% } %>
                </table>
            </div>
        </section>
                
        <br><br><br><br>    
    </body>
</html>
