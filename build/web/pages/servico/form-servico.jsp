<%@page import="Model.Servico"%>
<%@page import="Model.Especie"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Especie> especies = (List<Especie>) request.getAttribute("especies");
    
    Servico servico = (Servico) request.getAttribute("servico");
    
    String nome = "";
    String descricao = "";
    String foto = "default.png";
    Double preco = 0.0;
    Integer idespecie = 0;
    
    String rota = (String) request.getAttribute("rota");
    
    String label = "Cadastrar";
    String action = "insert";
    
    if("/edit".equals(rota)){ label = "Alterar"; }
    
    if (servico != null) {
        action = "update?id="+servico.getId();
        nome = servico.getNome();
        descricao = servico.getDescricao();
        foto = servico.getFoto();
        preco = servico.getValor();
        idespecie = servico.getIdEspecie();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Serviço</title>
        <style>
            #imagePreview {
                width: 252px;
                height: auto;
                cursor: pointer;
            }
            
            #imagePreview :hover{ border: 2px dashed orange; }
            
            #imagePreview img {
                width: 252px;
                height: auto;
            }
            
            #fileInput { display: none; }
        </style>
    </head>
    <body>
        <a href="list">Voltar</a>
        <h1><%= label %> Serviço</h1>
        <form action="<%= action %>" method="POST" enctype="multipart/form-data">
            
            <div id="imagePreview">
                <img src="${pageContext.request.contextPath}/assets/servicos/<%= foto %>">
            </div>
            <input type="file" name="foto" accept="image/*" id="fileInput"><br><br>
            
            <label>Nome Serviço</label><br>
            <input type="text" name="nome" placeholder="nome do serviço" value="<%= nome %>" required><br><br>
            
            <label>Descrição:</label><br>
            <textarea id="descricao" name="descricao" rows="4" cols="50" ><%= descricao %></textarea><br><br>
            
            <label>Tamanho</label>
            <br>
            <select name="tamanho">
                <option value="XP">XP</option>
                <option value="P">P</option>
                <option value="M">M</option>
                <option value="G">G</option>
                <option value="XG">XG</option>
                <option value="XXG">XXG</option>
            </select>
            <br>
            <br>
            
            <label>Valor</label><br>
            <input type="number" name="valor" step="0.01" min="0" placeholder="25,50"" value="<%= preco %>" required><br><br>
            
            <label>Especie</label><br>
            <select name="especie">
                <% if(especies != null){ 
                        for (Especie especie : especies) {
                        if(idespecie == especie.getId()){
                %>
                <option value="<%= especie.getId() %>" selected><%= especie.getDescricao() %></option>
                <% } else {%>
                <option value="<%= especie.getId() %>"><%= especie.getDescricao() %></option>
                <% } } } %>
            </select><br><br>
            
            <input type="submit" value="<%= label %> Serviço">
        </form>
        
        
        <script>
        document.getElementById('imagePreview').addEventListener('click', function() {
            document.getElementById('fileInput').click();
        });
        
        document.getElementById('fileInput').addEventListener('change', function(event) {
            var file = event.target.files[0];
            if (file) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    var img = document.createElement('img');
                    img.src = e.target.result;
                    var imagePreview = document.getElementById('imagePreview');
                    imagePreview.innerHTML = '';
                    imagePreview.appendChild(img);
                };
                reader.readAsDataURL(file);
            }
        });
    </script>
    </body>
</html>