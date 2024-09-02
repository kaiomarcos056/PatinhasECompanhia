<%@page import="Model.Produto"%>
<%@page import="Model.Especie"%>
<%@page import="Model.Categoria"%>
<%@page import="Model.Marca"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Marca> marcas = (List<Marca>) request.getAttribute("marcas");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    List<Especie> especies = (List<Especie>) request.getAttribute("especies");

    Produto produto = (Produto) request.getAttribute("produto");

    String nome = "";
    String foto = "default.png";
    String caminhoFoto = "";
    Double preco = 0.0;
    Integer quantidade = 0;
    Integer idcategoria = 0;
    Integer idmarca = 0;
    Integer idespecie = 0;

    String rota = (String) request.getAttribute("rota");

    String label = "Cadastrar";
    String action = "insert";

    if ("/edit".equals(rota)) {
        label = "Alterar";
    }

    if (produto != null) {
        action = "update?id=" + produto.getId();
        nome = produto.getNome();
        foto = produto.getFoto();
        caminhoFoto = "/assets/produtos/"+foto;
        preco = produto.getValor();
        quantidade = produto.getQuantidade();
        idcategoria = produto.getIdCategoria();
        idmarca = produto.getIdMarca();
        idespecie = produto.getIdEspecie();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/header.jsp" %>
        <%@ include file="/includes/valida-formulario.jsp" %>
        <title>Produto</title>
    </head>
    <body>
        <!-- MENU -->
        <%@ include file="/includes/menu.jsp" %>
        
        <!-- BEM-VINDO -->
        <%@ include file="/includes/bem-vindo.jsp" %>
        
        <!-- SEÇÃO -->
        <section class="home-section">
            <!-- MENU LATERAL -->
            <%@ include file="/includes/sidebar-home-adm.jsp" %>

            <!-- CONTEUDO -->
            <div class="home-content">
                <!-- TITULO -->
                <div class="home-titulo">
                    <h1><%= label%> Produto</h1>
                </div>

                <!-- FORMULARIO -->
                <form id="formProduto" action="<%= action%>" method="POST" enctype="multipart/form-data">
                    <div id="imagePreview">
                        <img src="${pageContext.request.contextPath}/assets/produtos/<%= foto%>">
                    </div>
                    <input type="file" name="foto" accept="image/*" id="fileInput" value="${pageContext.request.contextPath}<%= caminhoFoto %>"><br><br>

                    <label>Nome Produto</label><br>
                    <input type="text" name="nome" placeholder="nome do produto" value="<%= nome%>" ><br><br>

                    <label>Valor</label><br>
                    <input type="number" name="valor" step="0.01" min="0" placeholder="25,50"" value="<%= preco%>" ><br><br>

                    <label>Quantidade:</label><br>
                    <input type="number" name="quantidade" min="0" value="<%= quantidade%>" ><br><br>

                    <label>Categoria:</label><br>
                    <select name="categoria">
                        <% if (categorias != null) {
                                for (Categoria categoria : categorias) {
                                    if (idcategoria == categoria.getId()) {
                        %>
                        <option value="<%= categoria.getId()%>" selected><%= categoria.getDescricao()%></option>
                        <% } else {%>
                        <option value="<%= categoria.getId()%>"><%= categoria.getDescricao()%></option>
                        <% }
                        }
                    } %>
                    </select><br><br>

                    <label>Marca</label><br>
                    <select name="marca">
                        <% if (marcas != null) {
                                for (Marca marca : marcas) {
                                    if (idmarca == marca.getId()) {
                        %>
                        <option value="<%= marca.getId()%>" selected><%= marca.getDescricao()%></option>
                        <% } else {%>
                        <option value="<%= marca.getId()%>"><%= marca.getDescricao()%></option>
                        <% }
                        }
                    } %>
                    </select><br><br>

                    <label>Especie</label><br>
                    <select name="especie">
                        <% if (especies != null) {
                                for (Especie especie : especies) {
                                    if (idespecie == especie.getId()) {
                        %>
                        <option value="<%= especie.getId()%>" selected><%= especie.getDescricao()%></option>
                        <% } else {%>
                        <option value="<%= especie.getId()%>"><%= especie.getDescricao()%></option>
                        <% }
                        }
                    }%>
                    </select><br><br>

                    <input type="submit" value="<%= label%> Produto">
                </form>
            </div>
        </section>

        <!-- ESPAÇAMENTO FINAL -->
        <br><br><br><br>

        <script>
            document.getElementById('imagePreview').addEventListener('click', function () {
                document.getElementById('fileInput').click();
            });

            document.getElementById('fileInput').addEventListener('change', function (event) {
                var file = event.target.files[0];
                if (file) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
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
