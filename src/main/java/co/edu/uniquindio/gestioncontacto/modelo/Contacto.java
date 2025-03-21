package co.edu.uniquindio.gestioncontacto.modelo;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;


import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class Contacto {
    private String nombre, apellido, telefono, correo;
    private LocalDate cumpleanos;
}
