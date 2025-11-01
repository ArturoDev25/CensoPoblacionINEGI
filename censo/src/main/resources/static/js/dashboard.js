document.addEventListener('DOMContentLoaded', () => {
    const ctxPie = document.getElementById('graficaMunicipio');
    const ctxLine = document.getElementById('graficaViviendas');

    if (ctxPie) {
        new Chart(ctxPie, {
            type: 'pie',
            data: {
                labels: ['Ejemplo 1', 'Ejemplo 2', 'Ejemplo 3'],
                datasets: [{
                    data: [30, 45, 25],
                    backgroundColor: ['#00a6a6', '#40cfcf', '#a6f6f6']
                }]
            }
        });
    }

    if (ctxLine) {
        new Chart(ctxLine, {
            type: 'line',
            data: {
                labels: ['Enero', 'Febrero', 'Marzo'],
                datasets: [{
                    label: 'Viviendas',
                    data: [100, 120, 150],
                    fill: true,
                    borderColor: '#00a6a6',
                    tension: 0.3
                }]
            }
        });
    }
});
