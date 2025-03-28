package co.edu.uniquindio.gestioncontacto.repositorios;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import javafx.collections.ObservableList;

public interface IrepositorioContacto {
    public void agregarContacto(Contacto contacto);
    public void eliminarContacto(Contacto contacto);
    public Contacto obtenerContactoTelefono(String telefono);
    public ObservableList<Contacto> obtenerContactosNombre(String nombre);
    public ObservableList<Contacto> obtenerContactos();
}
