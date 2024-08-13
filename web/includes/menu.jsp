<%@page import="Model.Usuario"%>
<% 
    Usuario usu = (Usuario) session.getAttribute("usuario_logado");
    
    String caminho = request.getContextPath()+"/auth/login";
    String favorito = request.getContextPath()+"/auth/login";
    
    if (usu != null) {
        caminho = request.getContextPath()+"/dashboard/";
        favorito = request.getContextPath()+"/dashboard/favorito/list";
    }
%>
<script src="${pageContext.request.contextPath}/js/side-nav.js"></script>

<br><br>

<nav>
    <ul class="menu">
        <li onclick="openNav()">
            <i class="fa-solid fa-bars" style="font-size: 40px;"></i>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/">
                <img src="${pageContext.request.contextPath}/assets/logo-white.svg" alt="alt"/>
            </a>
        </li>
        <li>
            <form action="search" class="menu-pesquisa">
                <input type="text" name="pesquisa" class="btn-pesquisa">
                <button>
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
        </li>
        <li>
            <div style="display: flex; justify-content: center; align-items: center; gap: 10px; width: 180px;">
                <i class="fa-solid fa-user"></i>
                <a href="<%= caminho %>" style="font-size: 20px; font-weight: 700; line-height: 20px;">
                    entre ou
                    cadastre-se
                </a>
            </div>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/carrinho">
                <i class="fa-solid fa-cart-shopping"></i>
            </a>
        </li>
        <li>
            <a href="<%= favorito %>">
                <i class="fa-solid fa-heart"></i>
            </a>
        </li>
    </ul>
</nav>
                
<%@ include file="/includes/sidebar.jsp" %>