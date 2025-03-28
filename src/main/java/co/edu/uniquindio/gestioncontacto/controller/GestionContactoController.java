package co.edu.uniquindio.gestioncontacto.controller;
import co.edu.uniquindio.gestioncontacto.App;
import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.modelo.TipoBusquedaContactos;
import co.edu.uniquindio.gestioncontacto.servicios.ContactoServicio;
import co.edu.uniquindio.gestioncontacto.servicios.GestionServicio;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.time.MonthDay;

public class GestionContactoController {

        @FXML
        private Button btnActualizar;

        @FXML
        private Button btnFotoPerfil;

        @FXML
        private Button btnBuscar;

        @FXML
        private Button btnEliminiar;

        @FXML
        private Button btnFiltrar;

        @FXML
        private Button btnRegistrar;

        @FXML
        private Button btnLimpiarCampos;

        @FXML
        private ComboBox<TipoBusquedaContactos> choiseTipoBusquedaContacto;

        @FXML
        private Button ima;

        @FXML
        private TableColumn<Contacto,String> tbcApellido;

        @FXML
        private TableColumn<Contacto, String> tbcCorreo;

        @FXML
        private TableColumn<Contacto, String> tbcCumple;

        @FXML
        private TableColumn<Contacto, String> tbcNombre;

        @FXML
        private TableColumn<Contacto, String> tbcTelefono;

        @FXML
        private TableView<Contacto> tblListContactos;

        @FXML
        private TextField txtApellido;

        @FXML
        private TextField txtCorreo;

        @FXML
        private TextField txtNombre;

        @FXML
        private TextField txtTelefono;

        @FXML
        private TextField txtDia;

        @FXML
        private TextField txtMes;

        @FXML
        private TextField txtBusquedaPorNombre;

        @FXML
        private TextField txtFiltro;

        @FXML
        private ImageView imgFotoPerfil;



        private GestionServicio gestionServicio;

        private String rutaImagenContacto;

        private Contacto contactoSeleccionado;
        @FXML
        public void initialize(){
            gestionServicio= App.getGestionServicio();
            tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            tbcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
            tbcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
            tbcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
            tbcCumple.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerCumpleCadenas()));
            choiseTipoBusquedaContacto.setItems(FXCollections.observableArrayList(TipoBusquedaContactos.values()));
            agregarListener();
            setContactos();
        }

        public void mostrarInformacionContacto(Contacto contacto){
                txtNombre.setText(contacto.getNombre());
                txtApellido.setText(contacto.getApellido());
                txtTelefono.setText(contacto.getTelefono());
                txtCorreo.setText(contacto.getCorreo());
                txtDia.setText(String.valueOf(contacto.getCumpleanos().getDayOfMonth()));
                txtMes.setText(String.valueOf(contacto.getCumpleanos().getMonthValue()));

                if (contacto.getRutaFoto() != null && !contacto.getRutaFoto().isEmpty()) {
                        try {
                                FileInputStream input = new FileInputStream(contacto.getRutaFoto());
                                Image imagen = new Image(input);
                                imgFotoPerfil.setImage(imagen);
                        } catch (FileNotFoundException e) {
                                System.out.println("No se encontró la imagen: " + contacto.getRutaFoto());
                        }
                } else {
                        imgFotoPerfil.setImage(null); // O puedes poner una imagen por defecto
                }

        }

        // Agregar listener para detectar selección de cliente
        private void agregarListener() {
                tblListContactos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        contactoSeleccionado = newValue;
                        mostrarInformacionContacto(contactoSeleccionado);
                });
        }

        public void setContactos(){
                tblListContactos.setItems(gestionServicio.obtenerContactos());
                tblListContactos.refresh();
        }


        @FXML
        void handleBtnActualizar(ActionEvent event) {
                String telefonoActualizar=contactoSeleccionado.getTelefono().trim();
                String nombre= txtNombre.getText().trim();
                String apellido= txtApellido.getText().trim();
                String telefono= txtTelefono.getText().trim();
                String correo= txtCorreo.getText().trim();
                int dia= Integer.parseInt(txtDia.getText().trim());
                int mes= Integer.parseInt(txtMes.getText().trim());
                try {
                        gestionServicio.actualizarContacto(telefonoActualizar,nombre, apellido,telefono, correo, rutaImagenContacto, dia, mes);
                        handleBtnLimpiarCampos();
                        setContactos();
                } catch (
                        Exception e) {
                        App.mostrarAlerta("Error",e.getMessage());
                }
        }

        @FXML
        void handleBtnBuscar(ActionEvent event) {
                String nombre = txtBusquedaPorNombre.getText().trim();
                try{
                        App.mostrarMensaje("Contactos encontrados", gestionServicio.filtrarContactosNombre(nombre));
                }
                catch (Exception e) {
                        App.mostrarAlerta("Error",e.getMessage());
                }
        }

        @FXML
        void handleBtnEliminar(ActionEvent event) {
                if (contactoSeleccionado==null){
                        App.mostrarAlerta("Error","No se seleccina ningun contacto");
                        return;
                }
                String telefonoEliminar=contactoSeleccionado.getTelefono();
                try{
                        gestionServicio.eliminarContacto(telefonoEliminar);
                        handleBtnLimpiarCampos();
                        setContactos();
                }
                catch (Exception e){
                        App.mostrarAlerta("Error",e.getMessage());
                }
        }

        @FXML
        void handleBtnFiltrar(ActionEvent event) {
                TipoBusquedaContactos tipoBusqueda = choiseTipoBusquedaContacto.getValue();
                String parametro = txtFiltro.getText().trim();

                try{
                        tblListContactos.setItems(gestionServicio.filtrarContactosNombreTelefono(tipoBusqueda, parametro));
                }
                catch (Exception e) {
                        App.mostrarAlerta("Error",e.getMessage());
                }

        }

        @FXML
        void handleBtnLimpiarCampos() {
                txtNombre.clear();
                txtApellido.clear();
                txtTelefono.clear();
                txtCorreo.clear();
                txtDia.clear();
                txtMes.clear();
                txtBusquedaPorNombre.clear();
                txtFiltro.clear();
                choiseTipoBusquedaContacto.setValue(null);
                imgFotoPerfil.setImage(null);
                setContactos();
                limpiarSeleccion();
        }

        private void limpiarSeleccion() {
                contactoSeleccionado = null;
                tblListContactos.getSelectionModel().clearSelection();
}


        @FXML
        void handleBtnRegistrar(ActionEvent event) {
                String nombre= txtNombre.getText().trim();
                String apellido= txtApellido.getText().trim();
                String telefono= txtTelefono.getText().trim();
                String correo= txtCorreo.getText().trim();
                int dia= Integer.parseInt(txtDia.getText().trim());
                int mes= Integer.parseInt(txtMes.getText().trim());

                try {
                        gestionServicio.registrarContacto(nombre, apellido,telefono, correo,dia, rutaImagenContacto, mes);
                        handleBtnLimpiarCampos();
                        setContactos();
                } catch (
                        Exception e) {
                    App.mostrarAlerta("Error",e.getMessage());
                }
        }

        @FXML
        void handleBtnAgregarFoto(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleccionar Imagen");

                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
                );

                File imagenSeleccionada = fileChooser.showOpenDialog(btnFotoPerfil.getScene().getWindow());

                if (imagenSeleccionada != null) {
                        try {
                                // Crear carpeta si no existe
                                Path carpetaImagenes = Paths.get("imagenes");
                                Files.createDirectories(carpetaImagenes);

                                Path destino = carpetaImagenes.resolve(imagenSeleccionada.getName());
                                Files.copy(imagenSeleccionada.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

                                rutaImagenContacto = destino.toString();

                                Image image = new Image(new FileInputStream(rutaImagenContacto));
                                imgFotoPerfil.setImage(image);

                                System.out.println("Imagen almacenada: " + rutaImagenContacto);
                        } catch (IOException e) {
                                App.mostrarAlerta("Error", "No se pudo guardar la imagen.");
                        }
                }
        }

}

