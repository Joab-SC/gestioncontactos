package co.edu.uniquindio.gestioncontacto;

import co.edu.uniquindio.gestioncontacto.repositorios.RepositorioContacto;
import co.edu.uniquindio.gestioncontacto.servicios.ContactoServicio;
import co.edu.uniquindio.gestioncontacto.servicios.GestionServicio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static RepositorioContacto repositorioContacto;
    private static ContactoServicio contactoServicio;
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
    public static GestionServicio getGestionServicio(){
        return gestionServicio;
    }

}
