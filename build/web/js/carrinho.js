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

function openCity(cityName){
    var i;
    var x = document.getElementsByClassName("city");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(cityName).style.display = "block";
}
