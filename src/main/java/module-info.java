module co.edu.uniquindio.gestioncontacto {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.gestioncontacto to javafx.fxml;
    exports co.edu.uniquindio.gestioncontacto;
}