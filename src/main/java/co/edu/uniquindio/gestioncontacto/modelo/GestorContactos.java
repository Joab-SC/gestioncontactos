package co.edu.uniquindio.gestioncontacto.modelo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;

@Getter
@Setter
public class GestorContactos {
    ArrayList<Contacto> contactos;

    public GestorContactos() {
        contactos = new ArrayList<>();
    }

    public Contacto agregarContacto (String nombre, String apellido,String telefono,String correo,
    MonthDay cumpleanos) throws Exception{
        if(nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty() || cumpleanos == null){
            throw new Exception("Todos los campos son obligatorios");
        }
        if(telefono.length() != 10){
            throw new Exception("El telefono debe tener 10 digitos");
        }
        if(!correo.contains("@")){
            throw new Exception("El correo no es valido");
        }
        return new Contacto(nombre, apellido, telefono, correo, cumpleanos);
    }

    public void eliminarContacto(String telefono) throws Exception{
        Contacto contactoEliminar=obtenerContacto(telefono);
        if(contactoEliminar == null){
            throw new Exception("Contacto no encontrado");
        }
        contactos.remove(contactoEliminar);
    }

    public void actualizarContacto(String telfonoActualizar, String nombre, String apellido, String telefonoNuevo, String correo,MonthDay cumpleanos)throws Exception{
        if(nombre.isEmpty()||apellido.isEmpty()||telefonoNuevo.isEmpty()||correo.isEmpty()||cumpleanos == null ){
            throw new Exception("Los campos son obligatorios");
        }
        for (Contacto contacto: contactos) {
            if(contacto.getTelefono().equals(telefonoNuevo)){
                throw new Exception("El numero telefonico ya existe en la lista de contactos");
            }
        }

        Contacto contactoActulizar=obtenerContacto(telfonoActualizar);
        if(contactoActulizar == null){
            throw new Exception("Contacto no encontrado");
        }
        contactoActulizar.setNombre(nombre);
        contactoActulizar.setApellido(apellido);
        contactoActulizar.setTelefono(telefonoNuevo);
        contactoActulizar.setCorreo(correo);
        contactoActulizar.setCumpleanos(cumpleanos);


    }

    private Contacto obtenerContacto(String telefono){
        Contacto contactoBuscado = null;
        for (Contacto contacto : contactos) {
            if( contacto.getTelefono().equals(telefono)){
                contactoBuscado = contacto;
            }
        }
        return contactoBuscado;
    }

}
