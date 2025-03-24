package co.edu.uniquindio.gestioncontacto.modelo;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;


import java.time.LocalDate;
import java.time.MonthDay;


@Getter
@Setter
@AllArgsConstructor
public class Contacto {
    private String nombre, apellido, telefono, correo;
    private MonthDay cumpleanos;

    public String obtenerCumpleCadenas() {
        return String.format("%02d/%02d", cumpleanos.getDayOfMonth(), cumpleanos.getMonthValue());
    }


    @Override
    public String toString() {
        return  "Nombre:'" + nombre +
                " - Apellido: " + apellido +
                " - Telefono: " + telefono +
                " - Correo: " + correo +
                " - Cumpleanos: " + cumpleanos;
    }
}
