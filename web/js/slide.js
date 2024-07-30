
var banners = tns({
    container: '.banner',
    items: 1,
    slideBy: 'page',
    autoplay: true,
    autoplayButtonOutput: false,
    controls: false,
    nav: true,
    navPosition: 'bottom',
    autoplayTimeout: 3000,
    autoplayHoverPause: true
});

var slider = tns({
    container: '.promocoes',
    items: 2.5, // Número de itens visíveis no slide
    slideBy: 'page',
    //autoplay: false,
    //controls: false, // Adiciona os controles de navegação
    mouseDrag: true,
    nav: false, // Adiciona a navegação por pontos
    autoplayTimeout: 3000, // Tempo em milissegundos entre as transições
    gutter: 40,
    controlsContainer: '.ctl-prom'
});

var slider = tns({
    container: '.produtos',
    items: 4,
    slideBy: 'page',
    mouseDrag: true,
    nav: false,
    autoplayTimeout: 3000,
    gutter: 40,
    controlsContainer: '.ctl-prod'
});

var slider = tns({
    container: '.brinquedos',
    items: 4,
    slideBy: 'page',
    mouseDrag: true,
    nav: false,
    autoplayTimeout: 3000,
    gutter: 40,
    controlsContainer: '.ctl-brin'
});
