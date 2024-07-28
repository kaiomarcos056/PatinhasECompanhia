<%@page import="Model.Servico"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String msg = (String) session.getAttribute("msg");
    List<Servico> servicos = (List<Servico>) request.getAttribute("servicos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Serviço</title>
    </head>
    <body>
        <a href="<%=request.getContextPath()%>/dashboard/">Voltar</a>
        <h1>Serviços</h1>
        
        <a href="new">Novo Serviço</a>
        <br><br>
        
        <form action="list" method="GET">
            <input type="text" name="servico" placeholder="serviço">
            <button type="submit" class="">Pesquisar</button>
        </form>
        <br>
        
        <% if(msg != null){ %>
            <p><%= msg %></p>
        <%      
            session.removeAttribute("msg"); 
            }
        %>
        
        <section style="width: 1186px; display: flex; gap: 20px; flex-wrap: wrap;">
            <% if (servicos == null) { %>
            <p>Nenhum serviço cadastrado.</p>
            <% } else { %>
                <% for (Servico servico : servicos) { %>
                <table border="1" style="width: 262px; max-height: 380px;">
                <tr>
                    <td colspan="4">
                        <img src="${pageContext.request.contextPath}/assets/servicos/<%= servico.getFoto() %>" width="100%">
                    </td>
                </tr>
                <tr>
                    <td><b>Nome</b></td>
                    <td><%= servico.getId() %></td>
                    <td colspan="2" ><%= servico.getNome()%></td>
                </tr>
                <tr>
                    <td colspan="4"><b>Descrição</b></td>
                </tr>
                <tr>
                    <td colspan="4"><%= servico.getDescricao() %></td>
                </tr>
                <tr>
                    <td><b>Especie</b></td>
                    <td><%= servico.getIdEspecie()%></td>
                    <td colspan="2"><%= servico.getEspecie()%></td>
                </tr>
                <tr>
                    <td colspan="1"><b>Tamanho</b></td>
                    <td colspan="3"><%= servico.getTamanho()%></td>
                </tr>
                <tr>
                    <td colspan="1"><b>Valor</b></td>
                    <td colspan="3"><%= servico.getValor()%></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <a href="edit?id=<%= servico.getId()%>" class="edit"> Editar </a> 
                    </td>
                    <td colspan="2"> 
                        <a href="delete?id=<%= servico.getId()%>" class="remove"> Excluir </a> 
                    </td>
                </tr>
                </table>
                <% } %>
            <% } %>
        </section>
    </body>
</html>
