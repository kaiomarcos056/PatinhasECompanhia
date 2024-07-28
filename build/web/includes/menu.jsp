<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<script src="${pageContext.request.contextPath}/js/side-nav.js"></script>
<br><br>
<nav>
    <ul class="menu">
        <li onclick="openNav()"><a href="#">menu</a></li>
        <li><a href="<%=request.getContextPath()%>/">logo</a></li>
        <li>
            <form action="search">
                <input type="text" name="pesquisa">
                <input type="submit" value="pesquisar">
            </form>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/auth/login">entre ou cadastre-se</a>
        </li>
        <li><a href="carrinho">carrinho</a></li>
        <li><a href="favorito">favoritos</a></li>
    </ul>
</nav>
<br><br>
<%@ include file="/includes/sidebar.jsp" %>