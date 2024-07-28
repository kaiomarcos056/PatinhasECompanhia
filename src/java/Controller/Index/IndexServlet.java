package Controller.Index;

import Model.CookieUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // OBTEM O COOKIE DE CARRINHO CASO EXISTA
        Cookie c = CookieUtils.obterCookie(request);
        
        // CASO O RETORNO SEJA null CRIA O COOKIE
        if (c == null) {
            // CRIA UM NOVO COOKIE (NOME DO COOKIE, VALOR DO COOKIE)
            c = new Cookie(CookieUtils.COOKIE_NOME, null);
            
            // ALTERANDO VALOR DE null PARA "" (vazio)
            c.setValue("");
        }
        
        // ATUALIZA A VALIDADE DO COOKIE PARA O MAXIMO DO VALOR INTEIRO
        c.setMaxAge(Integer.MAX_VALUE);
        
        // ADICIONA O COOKIE NO NAVEGADOR DO CLIENTE
        response.addCookie(c);
        
        // REDIRECIONA PARA index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
