<%@page import="Model.CarrinhoItem"%>
<%@page import="java.util.List"%>
<div id="lista-pedidos" class="city">
    <h1>Carrinho de compras</h1>
    
    <div style="display: flex; justify-content: space-between;">
        
        <div style="width: 630px">
            <% if(carrinho != null && carrinho.size() > 0) { %>
                <% for(CarrinhoItem item : carrinho) { %>
                
                <form action="${pageContext.request.contextPath}/carrinho/add-item">
                    
                <div style="padding: 20px; border: 2px solid orange; border-radius: 40px; display: flex; flex-flow: column;">
                    
                    <a href="carrinho/delete?produtoID=<%= item.getProduto().getId() %>" style="width: 60px; align-self: flex-end;">Excluir</a>
                    
                    <input type="hidden" name="produtoID" value="<%= item.getProduto().getId() %>" />
                    
                    <img  style="align-self: center;" src="${pageContext.request.contextPath}/assets/produtos/<%= item.getProduto().getFoto() %>" width="40%">
                    <h3><%= item.getProduto().getNome()%></h3>
                    
                    <div style="display: flex; justify-content: space-between; align-items: baseline;">
                        <p><b> R$ <%= item.getProduto().getValor() * item.getQuantidade() %></b></p>
                        <div>
                            <button name="action" value="remove">-</button>
                            <input type="number" min="0" value="<%= item.getQuantidade() %>" style="text-align: center;">
                            <button name="action" value="add">+</button>
                        </div>
                    </div>
                            
                </div>
                </form>
                <br>
                <% } %>
            <% } else { %>
            <h1>Carrinho vazio</h1>
            <% } %>
        </div>
                
        <div style="width: 415px; height: 700px; border: 2px solid orange; border-radius: 40px; padding: 20px; box-sizing: border-box;">
            <h2>Frete e Prazo</h2>
            
            <input type="text" name="name" placeholder="Digite seu CEP"><br><br>
            
            <button>Buscar</button><br><br><br>
            
            <div style="display: flex; justify-content: space-between; align-items: center; border: 2px solid orange; border-radius: 10px; padding: 10px;">
                <img src="${pageContext.request.contextPath}/assets/icones/loja.png" alt="alt"/>
                <div style="display: flex; flex-flow: column;">
                    <label><b>Retire na loja</b></label>
                    <label>Pedido pronto em 5 dias úteis</label>
                </div>
                <input type="radio" name="name">
            </div>
            <br>
            <div style="display: flex; justify-content: space-between; align-items: center; border: 2px solid orange; border-radius: 10px; padding: 10px;">
                <img src="${pageContext.request.contextPath}/assets/icones/car.png" alt="alt"/>
                <div style="display: flex; flex-flow: column;">
                    <label><b>Econômico</b></label>
                    <label>Entrega padrão de 5 a 20 dias</label>
                </div>
                <input type="radio" name="name">
            </div>
        </div>
                
    </div>
    
    <br><br>
    
    <div style="display: flex; justify-content: flex-end;">
        <button>Proximo</button>
    </div>
    
    <br><br>
</div>