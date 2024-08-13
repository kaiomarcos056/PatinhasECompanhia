function changeBorder(clickedButton) {
    var buttons = document.querySelectorAll("button");
    buttons.forEach(function (button) {
        button.classList.remove("selected");
    });
    clickedButton.classList.add("selected");
}


document.addEventListener("DOMContentLoaded", function () {
    const accordionItems = document.querySelectorAll(".accordion-item");

    accordionItems.forEach((item) => {
        const header = item.querySelector(".accordion-header");

        header.addEventListener("click", () => {
            item.classList.toggle("active");
        });
    });
});

function abrirAba(nomeAba){
    var i;
    var x = document.getElementsByClassName("aba");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(nomeAba).style.display = "block";
}
