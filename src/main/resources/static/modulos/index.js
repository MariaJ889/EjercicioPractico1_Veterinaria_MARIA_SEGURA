document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('modalConfirmarEliminacion');
    if (!modal) {
        return;
    }

    const form = document.getElementById('formularioConfirmarEliminacion');
    const id = document.getElementById('modalConfirmarEliminacionId');
    const mensaje = document.getElementById('modalConfirmarEliminacionMensaje');

    if (!form || !id || !mensaje) {
        return;
    }

    modal.addEventListener('show.bs.modal', function (event) {
        const boton = event.relatedTarget;
        if (!boton) {
            return;
        }

        const action = boton.getAttribute('data-action');
        const registroId = boton.getAttribute('data-id');
        const nombre = boton.getAttribute('data-nombre') || '';
        const entidad = boton.getAttribute('data-entidad') || 'este registro';

        if (!action || !registroId) {
            event.preventDefault();
            return;
        }

        form.reset();
        form.action = action;
        id.value = registroId;
        mensaje.textContent = '¿Desea eliminar ' + entidad + ' «' + nombre + '»?';
    });

    modal.addEventListener('hidden.bs.modal', function () {
        form.reset();
        form.action = '#';
        id.value = '';
        mensaje.textContent = '¿Desea eliminar este registro?';
    });
});
