package co.edu.uniquindio.gestioncontacto;

import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.repositorios.RepositorioContacto;
import co.edu.uniquindio.gestioncontacto.servicios.ContactoServicio;
import co.edu.uniquindio.gestioncontacto.servicios.GestionServicio;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.File;
import java.util.List;

public class App extends Application {
    private static RepositorioContacto repositorioContacto;
    private static ContactoServicio contactoServicio;
    @Getter
    private static GestionServicio gestionServicio;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/ViewContactos.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent, 1000, 360);
        stage.setScene(scene);
        stage.setTitle("UQ Notas");
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        repositorioContacto = new RepositorioContacto();
        contactoServicio= new ContactoServicio(repositorioContacto);
        gestionServicio = new GestionServicio(contactoServicio);
        launch(App.class, args);
    }

    public static void mostrarMensaje(String titulo, ObservableList<Contacto> contactos) {
            for (Contacto contacto : contactos) {
                File archivo = new File(contacto.getRutaFoto());

                ImageView imageView = null;

                if (archivo.exists()) {
                    Image imagen = new Image(archivo.toURI().toString());
                    imageView = new ImageView(imagen);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    imageView.setPreserveRatio(true);
                }

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle(titulo);
                alerta.setHeaderText(null);
                alerta.setContentText(contacto.toString());

                if (imageView != null) {
                    alerta.setGraphic(imageView);
                }

                alerta.showAndWait(); // <-- esto pausa hasta que el usuario cierre la alerta
            }
        }



    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setMinHeight(300); // Aumenta el alto

        alert.showAndWait();
    }

}
