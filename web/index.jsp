<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       
        <link rel="stylesheet" href="css/index.css"/>
        <script src="js/script.js"></script>
        
        <title>Patinhas & Companhia</title>
    </head>
    <body>
        <div id="teste"> AAAAAAA </div>
        <h1>Login</h1>
        <!--<form method="POST" action="logar">-->
        <form method="POST" action="/novo-usuario" id="formulario">
            <label>Email</label><br>
            <input type="text" name="email"><br>
            <label>Senha</label><br>
            <input type="password" name="senha"><br>
            <a href="cadastro.jsp">Cadastrar-se</a>
            <button type="submit" >Logar</button>
        </form>
    </body>
</html>
