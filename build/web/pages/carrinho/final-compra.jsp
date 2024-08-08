<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <h1 style="font-size: 40px; font-weight: 400;">Pedido nº 123456</h1>
                    <div style="border-bottom: 1px solid black; width: 646px; "></div>
                    <div style="display: flex; justify-content: space-between; width: 646px;">
                        <h3>Total</h3>
                        <h3>R$ </h3>
                    </div>
                </div>
            </center>
        </section>
    </body>
</html>
