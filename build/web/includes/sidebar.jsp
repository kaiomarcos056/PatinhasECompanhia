<link rel="stylesheet" href="${pageContext.request.contextPath}/css/side-nav.css">
<div id="sidenav" class="sidenav">
    <div class="sidenav-header">
        <h1 class="fa-solid fa-circle-user"></h1>
        <div>
            <p>Bem vindos à Patinhas & Companhia</p>
            <p>Entre ou cadastre-se</p>
        </div>
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">
            <h2>X</h2>
        </a>
    </div>
    <div class="sidenav-body">
        <ul>
            <li class="sidenav-titulo">Espécies</li>
            <li>
                <a href="${pageContext.request.contextPath}/list/produto?especie=3&categoria=3">
                    Cachorros
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/list/produto?especie=2&categoria=3">
                Gatos
                </a>
            </li>
            
        </ul>
        <div class="sidenav-line"></div>
        <ul>
            <li class="sidenav-titulo">Serviços</li>
            <li>
                <a href="${pageContext.request.contextPath}/list/servico?&categoria=10">
                Serviços Pet
                </a>                       
            </li>
        </ul>
    </div>
</div>