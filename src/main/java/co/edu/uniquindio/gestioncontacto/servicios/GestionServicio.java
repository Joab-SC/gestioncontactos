package co.edu.uniquindio.gestioncontacto.servicios;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.modelo.TipoBusquedaContactos;
import co.edu.uniquindio.gestioncontacto.repositorios.IrepositorioContacto;
import javafx.collections.ObservableList;

import java.time.MonthDay;

public class GestionServicio {

    private final ContactoServicio contactoServicio;

    public GestionServicio(ContactoServicio contactoServicio) {
        this.contactoServicio = contactoServicio;
    }

    public void registrarContacto(String nombre, String apellido, String telefono, String correo, int dia, int mes) throws Exception {
        MonthDay cumple = MonthDay.of(dia, mes);
        contactoServicio.registrarContacto(nombre, apellido, telefono, correo, cumple);
    }

    public void eliminarContacto(String telefono) throws Exception {
        contactoServicio.eliminarContacto(telefono);
    }

    public void actualizarContacto(String telefonoActualizar, String nombre, String apellido, String telefonoNuevo, String correo, int dia, int mes) throws Exception {
        MonthDay cumple = MonthDay.of(dia, mes);
        contactoServicio.actualizarContacto(telefonoActualizar, nombre, apellido, telefonoNuevo, correo, cumple);
    }

    public Contacto buscarContactoTelefono(String telefono) throws Exception {
        return contactoServicio.buscarContactoTelefono(telefono);
    }

    public ObservableList<Contacto> filtrarContactosNombreTelefono(TipoBusquedaContactos tipoBusqueda, String parametro) throws Exception {
        return filtrarContactosNombreTelefono(tipoBusqueda, parametro);
    }

    public ObservableList<Contacto> filtrarContactosNombre(String nombre) throws Exception {
        return filtrarContactosNombre(nombre);
    }

    public ObservableList<Contacto> obtenerContactos(){
        return contactoServicio.obtenerContactos();
    }
}


