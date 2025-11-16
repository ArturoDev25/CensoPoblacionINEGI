document.addEventListener('DOMContentLoaded', () => {

    // Definición de colores para los gráficos
    const coloresPrimarios = [
        'rgba(0, 166, 166, 0.85)',
        'rgba(64, 207, 207, 0.85)',
        'rgba(0, 119, 119, 0.85)',
        'rgba(102, 219, 219, 0.85)',
        'rgba(51, 153, 153, 0.85)',
        'rgba(128, 230, 230, 0.85)',
        'rgba(0, 140, 140, 0.85)',
        'rgba(166, 246, 246, 0.85)',
        'rgba(0, 77, 77, 0.85)',
        'rgba(179, 242, 242, 0.85)'
    ];

    const coloresBordes = [
        'rgba(0, 166, 166, 1)',
        'rgba(64, 207, 207, 1)',
        'rgba(0, 119, 119, 1)',
        'rgba(102, 219, 219, 1)',
        'rgba(51, 153, 153, 1)',
        'rgba(128, 230, 230, 1)',
        'rgba(0, 140, 140, 1)',
        'rgba(166, 246, 246, 1)',
        'rgba(0, 77, 77, 1)',
        'rgba(179, 242, 242, 1)'
    ];

    //GRÁFICO DE PASTEL DE MUNICIPIOS
    fetch('/api/dashboard/municipios')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('graficoMunicipios');
            if (ctx) {
                new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: data.labels,
                        datasets: [{
                            data: data.habitantes,
                            backgroundColor: coloresPrimarios,
                            borderColor: coloresBordes,
                            borderWidth: 2
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                position: 'right',
                                labels: {
                                    boxWidth: 20,
                                    font: {
                                        size: 12,
                                        weight: '500'
                                    },
                                    color: '#053c3c',
                                    padding: 15,
                                    usePointStyle: true,
                                    pointStyle: 'circle'
                                }
                            },
                            tooltip: {
                                backgroundColor: 'rgba(0, 77, 77, 0.95)',
                                titleColor: '#fff',
                                bodyColor: '#fff',
                                borderColor: '#00a6a6',
                                borderWidth: 2,
                                padding: 12,
                                cornerRadius: 8,
                                displayColors: true,
                                callbacks: {
                                    label: function(context) {
                                        const label = context.label || '';
                                        const value = context.parsed || 0;
                                        const total = context.dataset.data.reduce((a, b) => a + b, 0);
                                        const percentage = ((value / total) * 100).toFixed(1);
                                        return `${label}: ${value} habitantes (${percentage}%)`;
                                    }
                                }
                            }
                        }
                    }
                });
            }
        })
        .catch(error => console.error('Error al cargar datos de municipios:', error));

    // Grafico de líneas de localidades
    fetch('/api/dashboard/localidades')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('graficoLocalidades');
            if (ctx) {
                new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: data.labels,
                        datasets: [{
                            label: 'Viviendas',
                            data: data.habitantes,
                            backgroundColor: 'rgba(0, 166, 166, 0.1)',
                            borderColor: 'rgba(0, 166, 166, 1)',
                            borderWidth: 3,
                            fill: true,
                            tension: 0.4,
                            pointBackgroundColor: 'rgba(0, 166, 166, 1)',
                            pointBorderColor: '#fff',
                            pointBorderWidth: 2,
                            pointRadius: 5,
                            pointHoverRadius: 7
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                display: true,
                                position: 'top',
                                labels: {
                                    font: {
                                        size: 13,
                                        weight: 'bold'
                                    },
                                    color: '#053c3c',
                                    padding: 15,
                                    usePointStyle: true
                                }
                            },
                            tooltip: {
                                backgroundColor: 'rgba(0, 77, 77, 0.95)',
                                titleColor: '#fff',
                                bodyColor: '#fff',
                                borderColor: '#00a6a6',
                                borderWidth: 2,
                                padding: 12,
                                cornerRadius: 8,
                                displayColors: false,
                                callbacks: {
                                    label: function(context) {
                                        return `Habitantes: ${context.parsed.y}`;
                                    }
                                }
                            }
                        },
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: {
                                    stepSize: 1,
                                    color: '#007777',
                                    font: {
                                        size: 11,
                                        weight: '500'
                                    }
                                },
                                grid: {
                                    color: 'rgba(0, 166, 166, 0.1)',
                                    drawBorder: false
                                },
                                border: {
                                    display: false
                                }
                            },
                            x: {
                                ticks: {
                                    color: '#007777',
                                    font: {
                                        size: 10,
                                        weight: '500'
                                    },
                                    maxRotation: 45,
                                    minRotation: 45
                                },
                                grid: {
                                    display: false
                                },
                                border: {
                                    display: false
                                }
                            }
                        }
                    }
                });
            }
        })
        .catch(error => console.error('Error al cargar datos de localidades:', error));

    // tabla de municipios
    fetch('/api/dashboard/tabla-municipios')
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#tablaMunicipios tbody');
            if (tbody) {
                tbody.innerHTML = '';
                data.forEach((municipio, index) => {
                    const promedio = municipio.totalViviendas > 0
                        ? (municipio.totalHabitantes / municipio.totalViviendas).toFixed(2)
                        : '0.00';

                    const row = `
                        <tr>
                            <td><strong>${index + 1}</strong></td>
                            <td><strong>${municipio.nombreMunicipio}</strong></td>
                            <td>${municipio.totalHabitantes}</td>
                            <td>${municipio.totalViviendas}</td>
                            <td>${municipio.totalLocalidades}</td>
                            <td>
                                <span class="badge-promedio">${promedio}</span>
                            </td>
                        </tr>
                    `;
                    tbody.innerHTML += row;
                });
            }
        })
        .catch(error => console.error('Error al cargar tabla de municipios:', error));
});