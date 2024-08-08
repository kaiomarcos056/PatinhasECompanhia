<div id="identificacao" class="city" style="display:none">
    <br><br>
    
    <h1>Identificação</h1>
    <p>Solicitamos que você confirme os dados inseridos durante o seu cadastro</p>
    
    <br><br>
    
    <section>
        
        <div style="width: 900px; margin: 0 auto;">
            
            <label>Nome</label>
            <input type="text" name="nome" placeholder="Kelvin Erick"><br><br>
            
            <label>Email</label>
            <input type="text" name="email" placeholder="erick@email.com"><br><br>
            
            <label>Telefone</label>
            <input type="text" name="telefone" placeholder="(85) 94002-8922"><br><br>
            
            <br>
            
            <% if(carrinho != null) { %>
            <div class="box-list-carrinho">
                <h2>Resumo do pedido</h2>
                
                <br>
                
                <table border="0" style="text-align: left;">
                    <tr>
                        <th>Produto</th>
                        <th style="width: 30%;">Descrição</th>
                        <th style="text-align: center;">Quantidade</th>
                        <th style="text-align: right;">Vlr Unit</th>
                        <th style="text-align: right;">Total</th>
                    </tr>
                <% for(CarrinhoItem item : carrinho) { %>
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/assets/produtos/<%= item.getProduto().getFoto() %>" alt="Descrição da imagem" width="170px">
                        </td>
                        <td>
                            <h3><%= item.getProduto().getNome()%></h3>
                        </td>
                        <td style="text-align: center;"> <%= item.getQuantidade() %> </td>
                        <td style="text-align: right;"> <p>R$ <%= item.getProduto().getValor() %></p> </td>
                        <td style="text-align: right;"> <p>R$ <%= item.getProduto().getValor() * item.getQuantidade() %></p> </td>
                        <% total += item.getProduto().getValor() * item.getQuantidade(); %>
                    </tr>
                <% } %>
                </table>
                
                <br>
                
                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                
                <table>
                    <tr>
                        <td>
                            <b>Frete:</b>
                        </td>
                        <td style="text-align: right;"> <p>R$ <%= frete %></p> </td>
                    </tr>
                </table>
                    
                <div id="linha" style="width: 100%; border-bottom: 1px solid black;"></div>
                
                <table border="0">
                    <tr>
                        <td>
                            <b>Total:</b>
                        </td>
                        <td style="text-align: right;"> <p>R$ <%= total %></p> </td>
                    </tr>
                </table>
                
            </div>
            <% } %>
            
            <br><br><br><br><br><br>
            
        </div>
    </section>
</div>