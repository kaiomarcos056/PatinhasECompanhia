
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
    controlsContainer: '.tns-controls'
});

/*
var servicos = tns({
    container: '.promocoes',
    items: 2,
    slideBy: 1,
    mouseDrag: true,
    controlsContainer: '.tns-controls',
    nav: true,
    navPosition: 'bottom',
    gutter: 40
});
*/
