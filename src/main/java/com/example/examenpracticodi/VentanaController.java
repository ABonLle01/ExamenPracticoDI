package com.example.examenpracticodi;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class VentanaController {

    @FXML
    private TextField txtMatricula;
    @FXML
    private ChoiceBox<String> cbModelo;
    @FXML
    private ChoiceBox<String> cbCliente;
    @FXML
    private RadioButton rbTarifa;
    @FXML
    private DatePicker dpEntrada;
    @FXML
    private DatePicker dpSalida;
    @FXML
    private Label lblCoste;

    @FXML
    private TableView<Entrada> tabla;
    @FXML
    private TableColumn<Entrada, String> cMatricula;
    @FXML
    private TableColumn<Entrada, String> cModelo;
    @FXML
    private TableColumn<Entrada, String> cFechaE;
    @FXML
    private TableColumn<Entrada, String> cFechaS;
    @FXML
    private TableColumn<Entrada, String> cCliente;
    @FXML
    private TableColumn<Entrada, String> cTarifa;
    @FXML
    private TableColumn<Entrada, String> cCoste;

    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnSalir;
    @FXML
    private ToggleGroup tarifa;

    private ObservableList<Entrada> entradas = FXCollections.observableArrayList();

    private boolean vacio=false;
    public void initialize(){


        if(Session.getEntradas().isEmpty()){
            ArrayList<Entrada> r = new ArrayList<>();
            r.add(new Entrada("ASDFAS","Ferrari",LocalDate.now(),LocalDate.now(),"Paco","Standard",2000));
            r.add(new Entrada("KHLASF","BMW",LocalDate.now(),LocalDate.now(),"Manolo","Larga duracion",1000));
            Session.setEntradas(r);
        }
        entradas.addAll(Session.getEntradas());
        
        
        cbModelo.getItems().add("Ferrari");
        cbModelo.getItems().add("Mercedes");
        cbModelo.getItems().add("Audi");
        cbModelo.getItems().add("BMW");

        cbCliente.getItems().add("Manolo");
        cbCliente.getItems().add("Paco");
        cbCliente.getItems().add("Fernando");

        cbModelo.getSelectionModel().selectFirst();
        cbCliente.getSelectionModel().selectFirst();


        cMatricula.setCellValueFactory((fila)->
            new SimpleStringProperty(fila.getValue().getMatricula())
        );

        cModelo.setCellValueFactory((fila)->
                new SimpleStringProperty(fila.getValue().getModelo())
        );

        cFechaE.setCellValueFactory((fila)->
                new SimpleStringProperty(fila.getValue().getFechaEntrada()+"")
        );

        cFechaS.setCellValueFactory((fila)->
                new SimpleStringProperty(fila.getValue().getFechaSalida()+"")
        );

        cCliente.setCellValueFactory((fila)->
                new SimpleStringProperty(fila.getValue().getCliente())
        );

        cTarifa.setCellValueFactory((fila)->
                new SimpleStringProperty(fila.getValue().getTarifa())
        );

        cCoste.setCellValueFactory((fila)->
                new SimpleStringProperty(fila.getValue().getCoste()+"")
        );



        tabla.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, entrada, t1) -> {
                    Session.setEntradaActual(t1);
                    System.out.println(t1);
                })
        );




        tabla.setItems(entradas);

    }


    @FXML
    public void add(ActionEvent actionEvent) {

        /*txtMatricula.setText("");
        cbModelo.setValue("");
        cbCliente.setValue("");*/

        String ma = txtMatricula.getText();
        String mo = cbModelo.getValue();
        String cli = cbCliente.getValue();
        String tar = rbTarifa.getToggleGroup().getSelectedToggle().toString();
        String e = String.valueOf(dpEntrada.getValue());
        String s = String.valueOf(dpSalida.getValue());
        String c = lblCoste.getText();
        LocalDate ldE = dpEntrada.getValue();
        LocalDate ldS = dpSalida.getValue();

        if(ma.isEmpty() || mo.isEmpty() || cli.isEmpty() || tar.isEmpty() || e.isEmpty() || s.isEmpty() || c.isEmpty()){
            vacio = true;
        }


        if(!vacio){
            Entrada entrada = new Entrada();
            entrada.setMatricula(ma.toUpperCase());
            entrada.setModelo(mo);
            entrada.setCliente(cli);
            entrada.setTarifa(tar);
            entrada.setFechaEntrada(ldE);
            entrada.setFechaSalida(ldS);
            entrada.setCoste(calcularPrecio(tar,ldE,ldS));


            Session.getEntradas().add(entrada);
            entradas.add(entrada);

            System.out.println(entrada);
        }else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos vacios!!");
            alerta.setContentText("Hay uno o varios campos vacios. Por favor revise los datos antes de enviar el formulario.");
            alerta.showAndWait();
        }




    }
    private int calcularPrecio(String t, LocalDate e, LocalDate s){

        int diferencia = (int) ChronoUnit.DAYS.between(e,s);

        int total=0;

        switch (t){
            case "Standard":
                total=diferencia*8;
                break;
            case "Oferta":
                total=diferencia*6;
                break;
            case "Larga duracion":
                total=diferencia*2;
                break;
        }

        return total;
    }


    @FXML
    public void close(ActionEvent actionEvent) {
        App.closeStage();
    }


}