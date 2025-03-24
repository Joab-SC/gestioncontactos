package co.edu.uniquindio.gestioncontacto.servicios;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.modelo.TipoBusquedaContactos;
import co.edu.uniquindio.gestioncontacto.repositorios.IrepositorioContacto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.MonthDay;

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
            throw new Exception("El telefono debe tener 10 digitos e iniciar en 3");
        }
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new Exception("El correo no es valido");
        }

        for (Contacto contacto : repositorioContacto.obtenerContactos()) {
            if (contacto.getTelefono().equals(telefono)) {
                throw new Exception("El numero telefónico ya existe en la lista de contactos");
            }
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

        Contacto contactoActulizar = buscarContactoTelefono(telefonoActualizar);

        for (Contacto contacto : repositorioContacto.obtenerContactos()) {
            if (contacto.getTelefono().equals(telefonoNuevo) && contacto != contactoActulizar) {
                throw new Exception("El numero telefónico ya existe en la lista de contactos");
            }
        }

        contactoActulizar.setNombre(nombre);
        contactoActulizar.setApellido(apellido);
        contactoActulizar.setTelefono(telefonoNuevo);
        contactoActulizar.setCorreo(correo);
        contactoActulizar.setCumpleanos(cumpleanos);
    }

    public Contacto buscarContactoTelefono(String telefono) throws Exception {
        if (telefono.isBlank()){
            throw new Exception("Debe ingresar el telefono a buscar");
        }
        Contacto contactoBuscado = repositorioContacto.obtenerContactoTelefono(telefono);
        if (contactoBuscado == null) {
            throw new Exception("El contacto no fue encontrado");
        }
        return contactoBuscado;
    }


    public ObservableList<Contacto> filtrarContactosNombreTelefono(TipoBusquedaContactos tipoBusqueda, String parametro) throws Exception {
        ObservableList<Contacto> contactosFiltrados = FXCollections.observableArrayList();;
        if (parametro.isBlank()){
            throw new Exception("Debe ingresar el valor a buscar");
        }

        if (tipoBusqueda  == null){
            throw new Exception("Debe seleccionar el tipo de filtro");
        }

        switch (tipoBusqueda) {
            case NOMBRE -> contactosFiltrados = filtrarContactosNombre(parametro);
            case TELEFONO -> contactosFiltrados.add(buscarContactoTelefono(parametro));
        }
        return contactosFiltrados;
    }


    public ObservableList<Contacto> filtrarContactosNombre(String nombre) throws Exception {
        if (nombre.isBlank()){
            throw new Exception("Debe ingresar el nombre a buscar");
        }
        ObservableList<Contacto> contactosNombre = repositorioContacto.obtenerContactosNombre(nombre);
        if(contactosNombre.isEmpty()) {throw new Exception("No existen contactos con ese nombre");}
        return contactosNombre;
    }

    public ObservableList<Contacto> obtenerContactos(){
        return repositorioContacto.obtenerContactos();
    }

    public static String obtenerContactosCadena(ObservableList<Contacto> contactos) {
        String cadena = "Contactos:\n";
        for (Contacto contacto : contactos) {
            cadena += contacto.toString() + "\n";
        }
        return cadena;
    }
}
