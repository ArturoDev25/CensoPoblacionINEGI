document.addEventListener('DOMContentLoaded', () => {
    const municipioSelect = document.querySelector('select[name="municipio"]');
    const localidadSelect = document.querySelector('select[name="localidad"]');

    if (municipioSelect && localidadSelect) {
        municipioSelect.addEventListener('change', async () => {
            const municipioId = municipioSelect.value;
            localidadSelect.innerHTML = '<option>Cargando...</option>';

            try {
                const response = await fetch(`/api/localidades/por-municipio/${municipioId}`);
                if (!response.ok) throw new Error('Error al cargar localidades');

                const localidades = await response.json();
                localidadSelect.innerHTML = '<option value="">Seleccione una localidad</option>';
                localidades.forEach(l => {
                    const option = document.createElement('option');
                    option.value = l.id;
                    option.textContent = l.nombre;
                    localidadSelect.appendChild(option);
                });
            } catch (error) {
                console.error('Error:', error);
                localidadSelect.innerHTML = '<option>Error al cargar</option>';
            }
        });
    }
});
