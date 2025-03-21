package co.edu.uniquindio.gestioncontacto.servicios;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.modelo.TIpoBusquedaContactos;
import co.edu.uniquindio.gestioncontacto.repositorios.IrepositorioContacto;
import co.edu.uniquindio.gestioncontacto.repositorios.RepositorioContacto;
import javafx.collections.ObservableList;

import java.time.MonthDay;
import java.util.ArrayList;

public class ContactoServicio {
    private final IrepositorioContacto repositorioContacto;

    public ContactoServicio(IrepositorioContacto repositorioContacto) {
        this.repositorioContacto = repositorioContacto;
    }

    public void registrarContacto(String nombre, String apellido, String telefono, String correo,
                                  MonthDay cumpleanos) throws Exception {
        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty() || cumpleanos == null) {
            throw new Exception("Todos los campos son obligatorios");
        }
        if (!telefono.trim().matches("^3\\d{9}$")) {
            throw new Exception("El telefono debe tener 10 digitos e inicial en 3");
        }
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new Exception("El correo no es valido");
        }
        Contacto contacto = new Contacto(nombre, apellido, telefono, correo, cumpleanos);
        repositorioContacto.agregarContacto(contacto);
    }

    public void eliminarContacto(String telefono) throws Exception {
        Contacto contactoEliminar = repositorioContacto.obtenerContactoTelefono(telefono);
        if (contactoEliminar == null) {
            throw new Exception("Contacto no encontrado");
        }
        repositorioContacto.eliminarContacto(contactoEliminar);
    }

    public void actualizarContacto(String telefonoActualizar, String nombre, String apellido, String telefonoNuevo, String correo, MonthDay cumpleanos) throws Exception {
        if (nombre.isEmpty() || apellido.isEmpty() || telefonoNuevo.isEmpty() || correo.isEmpty() || cumpleanos == null) {
            throw new Exception("Los campos son obligatorios");
        }
        for (Contacto contacto : repositorioContacto.obtenerContactos()) {
            if (contacto.getTelefono().equals(telefonoNuevo)) {
                throw new Exception("El numero telefónico ya existe en la lista de contactos");
            }
        }

        Contacto contactoActulizar = buscarContactoTelefono(telefonoActualizar);

        contactoActulizar.setNombre(nombre);
        contactoActulizar.setApellido(apellido);
        contactoActulizar.setTelefono(telefonoNuevo);
        contactoActulizar.setCorreo(correo);
        contactoActulizar.setCumpleanos(cumpleanos);
    }

    private Contacto buscarContactoTelefono(String telefono) throws Exception {
        Contacto contactoBuscado = repositorioContacto.obtenerContactoTelefono(telefono);
        if (contactoBuscado == null) {
            throw new Exception("El contacto no fue encontrado");
        }
        return contactoBuscado;
    }

    private Contacto buscarContactoNombre(String nombre) throws Exception {
        Contacto contactoBuscado = repositorioContacto.obtenerContactoNombre(nombre);
        if (contactoBuscado == null) {
            throw new Exception("El contacto no fue encontrado");
        }
        return contactoBuscado;
    }



    private ObservableList<Contacto> filtrarContactosNombreTelefono(TIpoBusquedaContactos tipoBusqueda, String parametro) throws Exception {
        ObservableList<Contacto> contactosFiltrados = null;
        switch (tipoBusqueda) {
            case NOMBRE -> contactosFiltrados = filtrarContactosNombre(parametro);
            case TELEFONO -> contactosFiltrados = filtrarContactosTelefono(parametro);
        }
        return contactosFiltrados;
    }

    private ObservableList<Contacto> filtrarContactosTelefono(String telefono) throws Exception {
        ObservableList<Contacto> contactosTelefono = repositorioContacto.obtenerContactosTelefono(telefono);
        if(contactosTelefono.isEmpty()) {throw new Exception("No existen contactos con ese numero de telefono");}
        return contactosTelefono;
    }

    private ObservableList<Contacto> filtrarContactosNombre(String nombre) throws Exception {
        ObservableList<Contacto> contactosNombre = repositorioContacto.obtenerContactosNombre(nombre);
        if(contactosNombre.isEmpty()) {throw new Exception("No existen contactos con ese nombre");}
        return contactosNombre;
    }
}
