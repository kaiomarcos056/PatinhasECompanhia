<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Integer nroPedido = (Integer) request.getAttribute("nro_pedido");
    Double vlrPedido = (Double) request.getAttribute("vlr_pedido");
    
    DecimalFormat df = new DecimalFormat("###,##0.00");
    String vlrPedidoFormatado = df.format(vlrPedido);
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <title>Inicio</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
         
        <br>
        <br>
        
        <section>
            <center>
                <img src="${pageContext.request.contextPath}/assets/ok.png" >
                <h1 style="font-size: 30px; font-weight: 500; line-height: 40px;">
                    Sua compra foi realizada <br>com sucesso!
                </h1>
                
                <br><br>
                
                <div style="width: 900px; height: 170px; border: 2px solid orange; 
                    border-radius: 40px; display: flex; align-items: center; padding: 20px; justify-content: center;
                    flex-direction: column; gap: 20px;"
                >
                    <h1 style="font-size: 40px; font-weight: 400;">Pedido nº <%=  nroPedido %></h1>
                    <div style="border-bottom: 1px solid black; width: 646px; "></div>
                    <div style="display: flex; justify-content: space-between; width: 646px; align-items: center;">
                        <h3 style="font-size: 30px; font-weight: 600;">Total</h3>
                        <h3 style="font-size: 30px; font-weight: 400;">R$ <%=  vlrPedidoFormatado %></h3>
                    </div>
                </div>
            </center>
        </section>
    </body>
</html>
