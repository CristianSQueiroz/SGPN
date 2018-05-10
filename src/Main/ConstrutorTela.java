/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import ClassesDeConexao.CampoDinamicoOSDB;
import HashMap.CHashMap;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import SGPNmodel.CampoDinamico;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Cristian
 */
public class ConstrutorTela {
    
    public static Scene getTelaCampoDinamico(ArrayList<CampoDinamico> campos) {
        AnchorPane anchorPane = new AnchorPane();

        for (CampoDinamico campo : campos) {
            switch (campo.getTipo().toUpperCase()) {
                case "TEXTFIELD": {
                    anchorPane.getChildren().add(montaTextField(campo));
                    break;
                }
                case "LABEL": {
                    anchorPane.getChildren().add(montaLabel(campo));
                    break;
                }
                case "BUTTON": {
                    anchorPane.getChildren().add(montaButton(campo));
                    break;
                }
                case "TEXTAREA": {
                    anchorPane.getChildren().add(montaTextArea(campo));
                    break;
                }
                case "CHECKBOX": {
                    anchorPane.getChildren().add(montaCheckBox(campo));
                    break;
                }
                case "COMBOBOX": {
                    anchorPane.getChildren().add(montaComboBox(campo));
                    break;
                }
                case "DATEPICKER": {
                    anchorPane.getChildren().add(montaDatePicker(campo));
                    break;
                }
                case "SEPARADOR": {
                    anchorPane.getChildren().add(montaSeparador(campo));
                    break;
                }
            }
        }
        Scene scene = new Scene(anchorPane);
        return scene;
    }
    
    public static AnchorPane getTelaCampoDinamicoAsAnchorPane(ArrayList<CampoDinamico> campos) {
        AnchorPane anchorPane = new AnchorPane();

        for (CampoDinamico campo : campos) {
            switch (campo.getTipo().toUpperCase()) {
                case "TEXTFIELD": {
                    anchorPane.getChildren().add(montaTextField(campo));
                    break;
                }
                case "LABEL": {
                    anchorPane.getChildren().add(montaLabel(campo));
                    break;
                }
                case "BUTTON": {
                    anchorPane.getChildren().add(montaButton(campo));
                    break;
                }
                case "TEXTAREA": {
                    anchorPane.getChildren().add(montaTextArea(campo));
                    break;
                }
                case "CHECKBOX": {
                    anchorPane.getChildren().add(montaCheckBox(campo));
                    break;
                }
                case "COMBOBOX": {
                    anchorPane.getChildren().add(montaComboBox(campo));
                    break;
                }
                case "DATEPICKER": {
                    anchorPane.getChildren().add(montaDatePicker(campo));
                    break;
                }
                case "SEPARADOR": {
                    anchorPane.getChildren().add(montaSeparador(campo));
                    break;
                }
            }
        }
        return anchorPane;
    }
    
    public static ArrayList<Node> getTelaCampoDinamicoAsNodo(ArrayList<CampoDinamico> campos) {
        ArrayList<Node> nodos = new ArrayList<Node>();

        for (CampoDinamico campo : campos) {
            switch (campo.getTipo().toUpperCase()) {
                case "TEXTFIELD": {
                    nodos.add(montaTextField(campo));
                    break;
                }
                case "LABEL": {
                    nodos.add(montaLabel(campo));
                    break;
                }
                case "BUTTON": {
                    nodos.add(montaButton(campo));
                    break;
                }
                case "TEXTAREA": {
                    nodos.add(montaTextArea(campo));
                    break;
                }
                case "CHECKBOX": {
                    nodos.add(montaCheckBox(campo));
                    break;
                }
                case "COMBOBOX": {
                    nodos.add(montaComboBox(campo));
                    break;
                }
                case "DATEPICKER": {
                    nodos.add(montaDatePicker(campo));
                    break;
                }
                case "SEPARADOR": {
                    nodos.add(montaSeparador(campo));
                    break;
                }
            }
        }
        return nodos;
    }

    public static Scene getTela(ArrayList<CHashMap> camposDB) {
        AnchorPane anchorPane = new AnchorPane();

        for (CampoDinamico campo : getCamposDB(camposDB)) {
            switch (campo.getTipo().toUpperCase()) {
                case "TEXTFIELD": {
                    anchorPane.getChildren().add(montaTextField(campo));
                    break;
                }
                case "LABEL": {
                    anchorPane.getChildren().add(montaLabel(campo));
                    break;
                }
                case "BUTTON": {
                    anchorPane.getChildren().add(montaButton(campo));
                    break;
                }
                case "TEXTAREA": {
                    anchorPane.getChildren().add(montaTextArea(campo));
                    break;
                }
                case "CHECKBOX": {
                    anchorPane.getChildren().add(montaCheckBox(campo));
                    break;
                }
                case "COMBOBOX": {
                    anchorPane.getChildren().add(montaComboBox(campo));
                    break;
                }
                case "DATEPICKER": {
                    anchorPane.getChildren().add(montaDatePicker(campo));
                    break;
                }
                case "SEPARADOR": {
                    anchorPane.getChildren().add(montaSeparador(campo));
                    break;
                }
            }
        }
        Scene scene = new Scene(anchorPane);
        
        return scene;
    }

    private static TextField montaTextField(CampoDinamico campo) {
        TextField textField = new TextField();
        textField.setMinWidth(campo.getComprimento());
        textField.setMinHeight(campo.getAltura());
        textField.setPrefSize(campo.getComprimento(), campo.getAltura());
        textField.setLayoutX(campo.getPosX());
        textField.setLayoutY(campo.getPosY());
        textField.setEditable(campo.isIsEditavel());
        return textField;
    }
    
    private static TextArea montaTextArea(CampoDinamico campo) {
        TextArea AreaField = new TextArea();
        AreaField.setMinWidth(campo.getComprimento());
        AreaField.setMinHeight(campo.getAltura());
        AreaField.setPrefSize(campo.getComprimento(), campo.getAltura());
        AreaField.setLayoutX(campo.getPosX());
        AreaField.setLayoutY(campo.getPosY());
        AreaField.setEditable(campo.isIsEditavel());
        return AreaField;
    }
    
    private static CheckBox montaCheckBox(CampoDinamico campo) {
        CheckBox checkBox = new CheckBox(campo.getLabel());
        checkBox.setMinWidth(campo.getComprimento());
        checkBox.setMinHeight(campo.getAltura());
        checkBox.setPrefSize(campo.getComprimento(), campo.getAltura());
        checkBox.setLayoutX(campo.getPosX());
        checkBox.setLayoutY(campo.getPosY());
        return checkBox;
    }

    private static Label montaLabel(CampoDinamico campo) {
        Label label = new Label(campo.getLabel());
        label.setMinWidth(campo.getComprimento());
        label.setMinHeight(campo.getAltura());
        label.setPrefSize(campo.getComprimento(), campo.getAltura());
        label.setLayoutX(campo.getPosX()+40);
        label.setLayoutY(campo.getPosY());
        return label;
    }
    
    private static ComboBox montaComboBox(CampoDinamico campo) {
        ComboBox comboBox = new ComboBox();
        comboBox.setMinWidth(campo.getComprimento());
        comboBox.setMinHeight(campo.getAltura());
        comboBox.setPrefSize(campo.getComprimento(), campo.getAltura());
        comboBox.setLayoutX(campo.getPosX());
        comboBox.setLayoutY(campo.getPosY());
        return comboBox;
    }
    
    private static DatePicker montaDatePicker(CampoDinamico campo) {
        DatePicker datePicker = new DatePicker();
        datePicker.setMinWidth(campo.getComprimento());
        datePicker.setMinHeight(campo.getAltura());
        datePicker.setPrefSize(campo.getComprimento(), campo.getAltura());
        datePicker.setLayoutX(campo.getPosX());
        datePicker.setLayoutY(campo.getPosY());
        return datePicker;
    }
    
    private static Separator montaSeparador(CampoDinamico campo) {
        Separator separador = new Separator();
        separador.setMinWidth(campo.getComprimento());
        separador.setMinHeight(campo.getAltura());
        separador.setPrefSize(campo.getComprimento(), campo.getAltura());
        separador.setLayoutX(campo.getPosX());
        separador.setLayoutY(campo.getPosY());
        return separador;
    }
    
    private static ToggleButton montaToggleButton(CampoDinamico campo) {
        ToggleButton toggleButton = new ToggleButton(campo.getLabel());
        toggleButton.setMinWidth(campo.getComprimento());
        toggleButton.setMinHeight(campo.getAltura());
        toggleButton.setPrefSize(campo.getComprimento(), campo.getAltura());
        toggleButton.setLayoutX(campo.getPosX());
        toggleButton.setLayoutY(campo.getPosY());
        return toggleButton;
    }
    
    private static Button montaButton(CampoDinamico campo) {
        Button button = new Button(campo.getLabel());
        button.setMinWidth(campo.getComprimento());
        button.setMinHeight(campo.getAltura());
        button.setPrefSize(campo.getComprimento(), campo.getAltura());
        button.setLayoutX(campo.getPosX());
        button.setLayoutY(campo.getPosY());
        return button;
    }
    

    private static ArrayList<CampoDinamico> getCamposDB(ArrayList<CHashMap> camposDB) {
        ArrayList<CampoDinamico> campos = new ArrayList<CampoDinamico>();
        for (CHashMap cHashMap : camposDB) {
            CampoDinamico campoDinamico = new CampoDinamico();
            campoDinamico.setId(cHashMap.getValorAsInt("ID"));
            campoDinamico.setDsDescricao(cHashMap.getValorAsString("DS_DESCRICAO"));
            campoDinamico.setLabel(cHashMap.getValorAsString("DS_LABEL"));
            campoDinamico.setTipo(cHashMap.getValorAsString("DS_TIPO"));
            campoDinamico.setTipoValor(cHashMap.getValorAsString("DS_TIPO_VALOR"));
            campoDinamico.setPosX(cHashMap.getValorAsInt("POS_X"));
            campoDinamico.setPosY(cHashMap.getValorAsInt("POS_Y"));
            campoDinamico.setAltura(cHashMap.getValorAsInt("QTD_ALTURA"));
            campoDinamico.setComprimento(cHashMap.getValorAsInt("QTD_COMPRIMENTO"));
            campoDinamico.setDsSQL(cHashMap.getValorAsString("DS_SQL"));
            campoDinamico.setIsEditavel((cHashMap.getValorAsString("IS_EDITAVEL").equalsIgnoreCase("S")));
            campos.add(campoDinamico);
        }
        return campos;
    }

    public static void preview() {
        Stage stage = new Stage();
        stage.setScene(getTela(new CampoDinamicoOSDB().getCampoDinamicosTodosCampos("ID")));
        stage.show();
    }

    public static void preview(ArrayList<CampoDinamico> campos) {
        Stage stage = new Stage();
        stage.setScene(getTelaCampoDinamico(campos));
        stage.show();
    }
    
}
