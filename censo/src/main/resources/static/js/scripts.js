document.addEventListener('DOMContentLoaded', () => {
  const ctxPie = document.getElementById('graficaMunicipio');
  const ctxLine = document.getElementById('graficaViviendas');

  if (ctxPie) {
    new Chart(ctxPie, { type:'pie', data:{ labels:[], datasets:[{ data:[] }] } });
  }
  if (ctxLine) {
    new Chart(ctxLine, { type:'line', data:{ labels:[], datasets:[{ label:'Viviendas', data:[], fill:true }] } });
  }
});
