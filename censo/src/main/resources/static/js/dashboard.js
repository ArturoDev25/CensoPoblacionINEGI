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

    // Colores para gráfica de dona
    const coloresDona = [
        'rgba(0, 166, 166, 0.9)',
        'rgba(64, 207, 207, 0.9)',
        'rgba(0, 119, 119, 0.9)',
        'rgba(102, 219, 219, 0.9)',
        'rgba(51, 153, 153, 0.9)',
        'rgba(128, 230, 230, 0.9)',
        'rgba(0, 140, 140, 0.9)',
        'rgba(166, 246, 246, 0.9)',
        'rgba(0, 77, 77, 0.9)',
        'rgba(179, 242, 242, 0.9)'
    ];

    // Colores para gráfica de barras (edades)
    const coloresEdades = [
        'rgba(255, 99, 132, 0.8)',
        'rgba(54, 162, 235, 0.8)',
        'rgba(255, 206, 86, 0.8)',
        'rgba(75, 192, 192, 0.8)',
        'rgba(153, 102, 255, 0.8)'
    ];

    const coloresEdadesBorde = [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)'
    ];

    // ==================== GRÁFICA 1: POBLACIÓN POR MUNICIPIO (PASTEL) ====================
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
                                        size: 11,
                                        weight: '500'
                                    },
                                    color: '#053c3c',
                                    padding: 12,
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

    // ==================== GRÁFICA 2: LOCALIDADES CON MAYOR POBLACIÓN (LÍNEA) ====================
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
                            label: 'Habitantes',
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
                                        size: 12,
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
                                        size: 9,
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

    // ==================== GRÁFICA 3: ACTIVIDADES ECONÓMICAS (DONA) ====================
    fetch('/api/dashboard/actividades')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('graficoActividades');
            if (ctx) {
                new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        labels: data.labels,
                        datasets: [{
                            data: data.habitantes,
                            backgroundColor: coloresDona,
                            borderColor: '#fff',
                            borderWidth: 3,
                            hoverOffset: 15
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        cutout: '65%',
                        plugins: {
                            legend: {
                                position: 'right',
                                labels: {
                                    boxWidth: 18,
                                    font: {
                                        size: 11,
                                        weight: '500'
                                    },
                                    color: '#053c3c',
                                    padding: 10,
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
                                        return `${label}: ${value} personas (${percentage}%)`;
                                    }
                                }
                            }
                        }
                    }
                });
            }
        })
        .catch(error => console.error('Error al cargar datos de actividades:', error));

    // ==================== GRÁFICA 4: DISTRIBUCIÓN POR EDAD (BARRAS) ====================
    fetch('/api/dashboard/edades')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('graficoEdades');
            if (ctx) {
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: data.labels,
                        datasets: [{
                            label: 'Habitantes',
                            data: data.habitantes,
                            backgroundColor: coloresEdades,
                            borderColor: coloresEdadesBorde,
                            borderWidth: 2,
                            borderRadius: 8,
                            borderSkipped: false
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                display: false
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
                                    stepSize: 10,
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
                                        size: 11,
                                        weight: 'bold'
                                    }
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
        .catch(error => console.error('Error al cargar datos de edades:', error));

    // ==================== TABLA DE MUNICIPIOS ====================
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