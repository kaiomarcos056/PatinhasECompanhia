package Controller.Dashboard;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DashBoardFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        boolean isLogado = (session != null && session.getAttribute("usuario_logado") != null);
        
        if (!isLogado) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth/login");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() { }    
}
