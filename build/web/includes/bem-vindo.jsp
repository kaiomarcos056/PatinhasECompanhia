<%@page import="Model.Usuario"%>

<% Usuario usuarioLogado = (Usuario) session.getAttribute("usuario_logado"); %>

<% if (usuarioLogado != null) { %>
<section>
    <br>
    <label style="margin-left: 20px;">Bem-Vindo <%= usuarioLogado.getNome() %></label>
    <br><br>
</section>
<% } else { %>
    <br>
<% } %>