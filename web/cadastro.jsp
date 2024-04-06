<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro</h1>
        
        <form method="POST" action="cadastrar">
            <label>Nome</label><br>
            <input type="text" name="nome"><br>
            <label>Email</label><br>
            <input type="text" name="email"><br>
            <label>Senha</label><br>
            <input type="password" name="senha"><br>
            <label>Endereco</label><br>
            <input type="text" name="endereco"><br>
            <a href="index.html">Inicio</a>
            <button type="submit" >Cadastrar-se</button>
        </form>
    </body>
</html>
