package co.edu.uniquindio.gestioncontacto.controller;
import co.edu.uniquindio.gestioncontacto.App;
import co.edu.uniquindio.gestioncontacto.modelo.Contacto;
import co.edu.uniquindio.gestioncontacto.modelo.TipoBusquedaContactos;
import co.edu.uniquindio.gestioncontacto.servicios.ContactoServicio;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.MonthDay;

public class GestionContactoController {

        @FXML
        private Button btnActualizar;

        @FXML
        private Button btnBuscar;

        @FXML
        private Button btnEliminiar;

        @FXML
        private Button btnFiltrar;

        @FXML
        private Button btnRegistrar;

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

        private ContactoServicio contactoServicio;

        @FXML
        public void initialize(){
            contactoServicio= App.getContactoServicio();
                tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
                tbcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
                tbcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
                tbcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
                tbcCumple.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerCumpleCadenas()));
        }
        @FXML
        void handleBtnActualizar(ActionEvent event) {
                System.out.println("Actualizando Contacto");
        }

        @FXML
        void handleBtnBuscar(ActionEvent event) {
                System.out.println("Actualizando Contacto");
        }

        @FXML
        void handleBtnEliminar(ActionEvent event) {
                System.out.println("Actualizando Contacto");
        }

        @FXML
        void handleBtnFiltrar(ActionEvent event) {
                System.out.println("Actualizando Contacto");
        }

        @FXML
        void handleBtnRegistrar(ActionEvent event) {
                System.out.println("Actualizando Contacto");
        }


    }

