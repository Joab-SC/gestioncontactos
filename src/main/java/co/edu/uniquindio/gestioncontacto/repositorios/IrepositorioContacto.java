package co.edu.uniquindio.gestioncontacto.repositorios;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.modelo.TIpoBusquedaContactos;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface IrepositorioContacto {
    public void agregarContacto(Contacto contacto);
    public void eliminarContacto(Contacto contacto);
    public Contacto obtenerContactoTelefono(String telefono);
    public Contacto obtenerContactoNombre(String nombre);
    public ObservableList<Contacto> obtenerContactosTelefono(String telefono);
    public ObservableList<Contacto> obtenerContactosNombre(String nombre);
    public ObservableList<Contacto> obtenerContactos();
}
