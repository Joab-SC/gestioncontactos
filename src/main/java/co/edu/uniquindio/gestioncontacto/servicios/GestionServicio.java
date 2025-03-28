package co.edu.uniquindio.gestioncontacto.servicios;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.modelo.TipoBusquedaContactos;
import javafx.collections.ObservableList;

import java.time.DateTimeException;
import java.time.MonthDay;

public class GestionServicio {

    private final ContactoServicio contactoServicio;

    public GestionServicio(ContactoServicio contactoServicio) {
        this.contactoServicio = contactoServicio;
    }

    public void registrarContacto(String nombre, String apellido, String telefono, String correo, int dia, String ruta, int mes) throws Exception {
        MonthDay cumple;
        try {
            cumple = MonthDay.of(mes, dia);
        } catch (DateTimeException e) {
            throw new Exception("Fecha de nacimiento inválida: el día o mes ingresado no es válido.");
        }
        contactoServicio.registrarContacto(nombre, apellido, telefono, correo, ruta, cumple);
    }

    public void eliminarContacto(String telefono) throws Exception {
        contactoServicio.eliminarContacto(telefono);
    }

    public void actualizarContacto(String telefonoActualizar, String nombre, String apellido, String telefonoNuevo, String correo, String ruta, int dia, int mes) throws Exception {
        MonthDay cumple;
        try {
            cumple = MonthDay.of(mes, dia);
        } catch (DateTimeException e) {
            throw new Exception("Fecha de nacimiento inválida: el día o mes ingresado no es válido.");
        }
        contactoServicio.actualizarContacto(telefonoActualizar, nombre, apellido, telefonoNuevo, correo, ruta, cumple);
    }

    public Contacto buscarContactoTelefono(String telefono) throws Exception {
        return contactoServicio.buscarContactoTelefono(telefono);
    }

    public ObservableList<Contacto> filtrarContactosNombreTelefono(TipoBusquedaContactos tipoBusqueda, String parametro) throws Exception {
        return contactoServicio.filtrarContactosNombreTelefono(tipoBusqueda, parametro);
    }

    public ObservableList<Contacto> filtrarContactosNombre(String nombre) throws Exception {
        return contactoServicio.filtrarContactosNombre(nombre);
    }

    public ObservableList<Contacto> obtenerContactos(){
        return contactoServicio.obtenerContactos();
    }

}


