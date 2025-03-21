package co.edu.uniquindio.gestioncontacto.modelo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
public class GestorContactos {
    ArrayList<Contacto> contactos;

    public GestorContactos() {
        contactos = new ArrayList<>();
    }

    public Contacto agregarContacto (String nombre, String apellido,String telefono,String correo,
    LocalDate cumpleanos) throws Exception{
        if(nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty() || cumpleanos == null){
            throw new Exception("Todos los campos son obligatorios");
        }
        return new Contacto(nombre, apellido, telefono, correo, cumpleanos);

    }


}
