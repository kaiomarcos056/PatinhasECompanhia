package Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class CookieUtils {
    // SETANDO NOME DO COOKIE
    public static final String COOKIE_NOME = "patinhas.carrinho";
    
    // CONSTRUTOR DO OBJETO
    private CookieUtils() {

    }
    
    // CASO JÁ EXISTA O COOKIE 'patinhas.carrinho', CASO NÃO RETORNA null
    public static Cookie obterCookie(HttpServletRequest request) {
        // PEGANDO TODOS OS COOKIES DO SITE
        Cookie[] cookies = request.getCookies();
        
        // UNICO COOKIE NULL POR PADRÃO
        Cookie c = null; 
        
        // PERCORRENDO LISTA DE COOKIES
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            // CASO ACHE UM COOKIE COM O NOME PASSADO 
            if (cookies[i].getName().equals(COOKIE_NOME)) {
                // ADICIONA ESSE COOKIE A MINHA VARIAVEL c
                c = cookies[i];
                
                // PARA O for
                break;
            }
        }
        return c;
    }
}
