document.addEventListener("DOMContentLoaded", function () {

    const buscador = document.getElementById("buscador");

    buscador.addEventListener("keyup", function () {
        let filtro = buscador.value.toLowerCase();

        let filas = document.querySelectorAll("#tablaLibros tbody tr");

        filas.forEach(function (fila) {
            let textoFila = fila.textContent.toLowerCase();

            fila.style.display = textoFila.includes(filtro) ? "" : "none";
        });
    });

});