<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <% 
            String label = "Cadastrar";
            String action = "insert";
            
            String nome = "";
            String endereco = "";
            String email = "";
            String senha = "";
            
            boolean isAdm = false;
            
            String rota = (String) request.getAttribute("rota");
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            
            if("/edit".equals(rota)){ label = "Alterar"; }
            if(usuario != null){
                action = "update?id="+usuario.getId();
                nome = usuario.getNome();
                endereco = usuario.getEndereco();
                email = usuario.getEmail();
                senha = usuario.getSenha();
                isAdm = usuario.isAdministrador();
            }
        %>
    </head>
    <body>
        <a href="list">Voltar</a>
        <h1><%= label %> Usuario</h1>
        <form action="<%= action %>" method="POST">
            <label>Nome</label><br>
            <input type="type" name="nome" value="<%= nome %>" required><br>
            
            <label>Administrador</label><br>
            <select name="adm">
                <% if(isAdm){ %>
                <option value="false">não</option>
                <option value="true" selected>sim</option>
                <% } else { %>
                <option value="false" selected>não</option>
                <option value="true">sim</option>
                <% } %>
            </select><br>
            
            <label>Endereço</label><br>
            <input type="type" name="endereco" value="<%= endereco %>"><br>
            
            <label>E-mail</label><br>
            <input type="type" name="email" value="<%= email %>" required><br>
            
            <label>Senha</label><br>
            <input type="password" name="senha" value="<%= senha %>" required><br>
            
            <label>Confirmar Senha</label><br>
            <input type="password" name="confirm-senha" required><br>
            
            <label>Padrão de Senha</label>
            <ul>
                <li>Mínimo 8 caracteres</li>
                <li>Letra Minúscula</li>
                <li>Letra Maiúscula</li>
                <li>Números</li>
            </ul>
            
            <input type="submit" value="<%= label %> Usuario">
        </form>
    </body>
</html>