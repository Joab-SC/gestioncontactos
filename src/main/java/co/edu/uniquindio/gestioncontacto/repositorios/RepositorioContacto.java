package co.edu.uniquindio.gestioncontacto.repositorios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
@Setter
public class RepositorioContacto implements IrepositorioContacto {
    private ObservableList<Contacto> contactos;
    public RepositorioContacto() {
        this.contactos = FXCollections.observableArrayList();
    }

    public void agregarContacto(Contacto contacto){
        this.contactos.add(contacto);
    }

    public void eliminarContacto(Contacto contacto){
        this.contactos.remove(contacto);
    }

    public Contacto obtenerContactoTelefono(String telefono){
        return contactos.stream().filter(c -> c.getTelefono().equalsIgnoreCase(telefono)).findFirst().orElse(null);
    }

    public ObservableList<Contacto> obtenerContactosNombre(String nombre) {
        return FXCollections.observableArrayList(contactos.stream()
                .filter(c -> nombre.equalsIgnoreCase(c.getNombre()))
                .collect(Collectors.toList())
        );
    }

    public ObservableList<Contacto> obtenerContactos(){
        return contactos;
    }

}
