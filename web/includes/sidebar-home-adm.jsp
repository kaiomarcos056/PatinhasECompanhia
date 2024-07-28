<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar-home-adm.css">
<div id="sidebar-home-adm" style="z-index: 10;">
    <div class="sidebar-home-logo">
        <img src="${pageContext.request.contextPath}/assets/logo-white.svg">
    </div>
    <div class="sidebar-home-menu">
        <ul>
            
            <li><a href="<%= request.getContextPath() %>/">&nbsp;&nbsp;<i class="fa-solid fa-house"></i>&nbsp;&nbsp; Inicio</a></li>
            <li><a href="<%= request.getContextPath() %>/dashboard/conta/">&nbsp;&nbsp;<i class="fa-solid fa-house"></i>&nbsp;&nbsp; Minha Conta</a></li>
            <li><a href="<%= request.getContextPath() %>/dashboard/usuario/list" >&nbsp;&nbsp;<i class="fa-solid fa-layer-group"></i>&nbsp;&nbsp; Usuarios</a></li>
            <li><a href="<%= request.getContextPath() %>/dashboard/marca/list" >&nbsp;&nbsp;<i class="fa-solid fa-layer-group"></i>&nbsp;&nbsp; Marcas</a></li>
            <li><a href="<%= request.getContextPath() %>/dashboard/categoria/list" >&nbsp;&nbsp;<i class="fa-solid fa-layer-group"></i>&nbsp;&nbsp; Categorias</a></li>
            <li><a href="<%= request.getContextPath() %>/dashboard/especie/list">&nbsp;&nbsp;<i class="fa-solid fa-layer-group"></i>&nbsp;&nbsp; Especies</a></li>
            <li><a href="<%= request.getContextPath() %>/dashboard/produto/list" >&nbsp;&nbsp;<i class="fa-solid fa-layer-group"></i>&nbsp;&nbsp; Produtos</a></li>
            <li><a href="<%= request.getContextPath() %>/dashboard/servico/list" >&nbsp;&nbsp;<i class="fa-solid fa-layer-group"></i>&nbsp;&nbsp; Serviços</a></li>
            
        </ul>
    </div>
    <div class="sidebar-home-logout">
        <ul>
            <li><a href="<%= request.getContextPath() %>/auth/logout" >&nbsp;&nbsp;<i class="fa-solid fa-right-from-bracket"></i>&nbsp;&nbsp; Sair</a></li>
        </ul>
    </div>
</div>