<%@page import="Model.CarrinhoItem"%>
<%@page import="java.util.List"%>
<div id="lista-pedidos" class="city">
    
    <br><br>
    
    <h1>Carrinho de compras</h1>
    
    <br><br>
    
    <div style="display: flex; justify-content: space-between;">
        
        <div style="width: 630px">
            <% if(carrinho != null && carrinho.size() > 0) { %>
                <% for(CarrinhoItem item : carrinho) { %>
                
                <form action="${pageContext.request.contextPath}/carrinho/add-item">
                <input type="hidden" name="produtoID" value="<%= item.getProduto().getId() %>" />
                <div class="box-item-carrinho">
                    
                    <a href="carrinho/delete?produtoID=<%= item.getProduto().getId() %>">
                        <i class="fa-regular fa-trash-can"></i>
                    </a>
                    <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getProduto().getFoto() %>" >
                    <h2><%= item.getProduto().getNome()%></h2>
                    <div class="preco">
                        <p><b>R$ <%= item.getProduto().getValor() * item.getQuantidade() %></b></p>
                        <div class="controle">
                            <button name="action" value="remove">-</button>
                            <input type="number" min="0" value="<%= item.getQuantidade() %>">
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
                
        <div class="box-preco">
            <h2>Frete e Prazo</h2>
            
            <br>
            <!-- INICIO FORM CRIAR COMPRA -->
            <form action="carrinho/new" method="GET">
            <input type="text" name="busca-cep" placeholder="Digite seu CEP"><br><br>
            
            <button onclick="disableButton()" id="btn-busca-cep">Buscar</button>
            
            <br><br><br><br><br><br>
            
            <div class="box-flag">
                <img src="${pageContext.request.contextPath}/assets/icones/loja.png" />
                <div class="labels">
                    <label><b>Retire na loja</b></label>
                    <label>Pedido pronto em 5 dias �teis</label>
                </div>
                <input type="radio" name="tipo-frete" value="R" checked>
            </div>
                
            <br>
            
            <div class="box-flag">
                <img src="${pageContext.request.contextPath}/assets/icones/car.png" />
                <div class="labels">
                    <label><b>Econ�mico</b></label>
                    <label>Entrega padr�o de 5 a 20 dias</label>
                </div>
                <input type="radio" name="tipo-frete" value="E">
            </div>
        </div>
                
    </div>
    
    <br><br><br><br><br><br>
    <script>
        function disableButton() {
            document.getElementById("btn-busca-cep").disabled = true;
        }
    </script>
</div>