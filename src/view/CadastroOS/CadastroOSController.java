/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.CadastroOS;

import ClassesDeConexao.CampoDinamicoOSDB;
import HashMap.CHashMap;
import Main.ConstrutorTela;
import editordecampos.CampoDinamico;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class CadastroOSController implements Initializable {

    @FXML
    private Button cadastrarOS;

    @FXML
    private Button limparCampos;

    @FXML
    private AnchorPane osPane;

    private static ArrayList<CampoDinamico> campos = new ArrayList<CampoDinamico>();
    private static HashMap<CampoDinamico, Object> map = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createComponents();
    }

    public ArrayList<CampoDinamico> getCampos() {
        ArrayList<CHashMap> camposDB = new CampoDinamicoOSDB().getCampoDinamicosTodosCampos("ID");
        ArrayList<CampoDinamico> campos = new ArrayList<CampoDinamico>();
        for (CHashMap campoDB : camposDB) {
            CampoDinamico campoDinamicoTemp = new CampoDinamico();
            campoDinamicoTemp.setId(campoDB.getValorAsInt("ID"));
            campoDinamicoTemp.setLabel(campoDB.getValorAsString("DS_LABEL"));
            campoDinamicoTemp.setDsDescricao(campoDB.getValorAsString("DS_DESCRICAO"));
            campoDinamicoTemp.setTipo(campoDB.getValorAsString("DS_TIPO"));
            campoDinamicoTemp.setIsEditavel(campoDB.getValorAsString("IS_EDITAVEL").equalsIgnoreCase("S"));
            campoDinamicoTemp.setPosX(campoDB.getValorAsInt("POS_X"));
            campoDinamicoTemp.setPosY(campoDB.getValorAsInt("POS_Y"));
            campoDinamicoTemp.setAltura(campoDB.getValorAsInt("QTD_ALTURA"));
            campoDinamicoTemp.setComprimento(campoDB.getValorAsInt("QTD_COMPRIMENTO"));
            campoDinamicoTemp.setDsSQL(campoDB.getValorAsString("DS_SQL"));
            campoDinamicoTemp.setDsAtributo(campoDB.getValorAsString("DS_ATRIBUTO"));
            campoDinamicoTemp.setTipoValor(campoDB.getValorAsString("DS_TIPO_VALOR"));
            campos.add(campoDinamicoTemp);
        }
        return campos;
    }

    public void limparCampos() {
        for (Node ol : osPane.getChildren()) {
            if (ol instanceof TextField) {
                ((TextField) ol).setText("");
            } else if (ol instanceof DatePicker) {
                ((DatePicker) ol).setValue(null);
            } else if (ol instanceof ComboBox) {
                ((ComboBox) ol).getSelectionModel().select(null);
            } else if (ol instanceof ToggleButton) {
                ((ToggleButton) ol).setSelected(false);
            }
        }
    }

    public void cadastrarOS() {
        CHashMap dBColumnValues = new CHashMap();
        for (CampoDinamico campoDinamico : map.keySet()) {
            if (!campoDinamico.getDsAtributo().equalsIgnoreCase("")) {
                for (Node ol : osPane.getChildren()) {
                    if (map.get(campoDinamico).equals(ol)) {
                        String values = "";
                        if (ol instanceof TextField) {
                            values = ((TextField) ol).getText();
                        } else if (ol instanceof DatePicker) {
                            values = ((DatePicker) ol).getEditor().getText();
                        } else if (ol instanceof ComboBox) {
                            values = ((ComboBox) ol).getSelectionModel().getSelectedItem().toString();
                        }
                        if (!values.equalsIgnoreCase("")) {
                            dBColumnValues.put(campoDinamico.getDsAtributo(), values);
                        }
                    }
                }
            }
        }
        System.out.println(dBColumnValues);

    }

    public void createComponents() {
        campos = new ArrayList<>();
        for (CampoDinamico campo : getCampos()) {
            try {
                switch (campo.getTipo().toUpperCase()) {
                    case "TEXTFIELD": {
                        montaTextField(campo);
                        break;
                    }
                    case "LABEL": {
                        montaLabel(campo);
                        break;
                    }
                    case "TEXTAREA": {
                        montaTextArea(campo);
                        break;
                    }
                    case "CHECKBOX": {
                        montaCheckBox(campo);
                        break;
                    }
                    case "COMBOBOX": {
                        montaComboBox(campo);
                        break;
                    }
                    case "DATEPICKER": {
                        montaDatePicker(campo);
                        break;
                    }
                    case "SEPARADOR": {
                        montaSeparador(campo);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createComponente(Object component, CampoDinamico campo) {
        CampoDinamico campoDinamico = new CampoDinamico();
        if (campo != null) {
            campoDinamico.setId(campo.getId());
            campoDinamico.setIsEditavel(true);
            campoDinamico.setDsSQL(campo.getDsSQL());
            campoDinamico.setDsDescricao(campo.getDsDescricao());
            campoDinamico.setLabel(campo.getLabel());
            campoDinamico.setTipoValor(campo.getTipoValor());
            campoDinamico.setDsAtributo(campo.getDsAtributo());
        } else {
            campoDinamico.setId(0);
            campoDinamico.setIsEditavel(true);
            campoDinamico.setDsSQL("");
            campoDinamico.setDsDescricao("");
            campoDinamico.setLabel("");
            campoDinamico.setTipoValor("");
            campoDinamico.setDsAtributo("");
        }

        if (component instanceof Button) {
            Button button = (Button) component;
            campoDinamico.setPosX((int) button.getLayoutX());
            campoDinamico.setPosY((int) button.getLayoutY());
            campoDinamico.setAltura((int) button.getPrefHeight());
            campoDinamico.setComprimento((int) button.getPrefWidth());
            campoDinamico.setLabel(button.getText());
            campoDinamico.setTipo("Label");
        } else if (component instanceof TextField) {
            TextField textField = (TextField) component;
            campoDinamico.setPosX((int) textField.getLayoutX());
            campoDinamico.setPosY((int) textField.getLayoutY());
            campoDinamico.setAltura((int) textField.getPrefHeight());
            campoDinamico.setComprimento((int) textField.getPrefWidth());
            campoDinamico.setTipo("TextField");
        } else if (component instanceof TextArea) {
            TextArea textArea = (TextArea) component;
            campoDinamico.setPosX((int) textArea.getLayoutX());
            campoDinamico.setPosY((int) textArea.getLayoutY());
            campoDinamico.setAltura((int) textArea.getPrefHeight());
            campoDinamico.setComprimento((int) textArea.getPrefWidth());
            campoDinamico.setTipo("TextArea");
        } else if (component instanceof CheckBox) {
            CheckBox checkBox = (CheckBox) component;
            campoDinamico.setPosX((int) checkBox.getLayoutX());
            campoDinamico.setPosY((int) checkBox.getLayoutY());
            campoDinamico.setAltura((int) checkBox.getPrefHeight());
            campoDinamico.setComprimento((int) checkBox.getPrefWidth());
            campoDinamico.setLabel(checkBox.getText());
            campoDinamico.setTipo("CheckBox");
        } else if (component instanceof ComboBox) {
            ComboBox comboBox = (ComboBox) component;
            campoDinamico.setPosX((int) comboBox.getLayoutX());
            campoDinamico.setPosY((int) comboBox.getLayoutY());
            campoDinamico.setAltura((int) comboBox.getPrefHeight());
            campoDinamico.setComprimento((int) comboBox.getPrefWidth());
            campoDinamico.setTipo("ComboBox");
        } else if (component instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) component;
            campoDinamico.setPosX((int) datePicker.getLayoutX());
            campoDinamico.setPosY((int) datePicker.getLayoutY());
            campoDinamico.setAltura((int) datePicker.getPrefHeight());
            campoDinamico.setComprimento((int) datePicker.getPrefWidth());
            campoDinamico.setTipo("DatePicker");
        } else if (component instanceof ToggleButton) {
            ToggleButton toggleButton = (ToggleButton) component;
            campoDinamico.setPosX((int) toggleButton.getLayoutX());
            campoDinamico.setPosY((int) toggleButton.getLayoutY());
            campoDinamico.setAltura((int) toggleButton.getPrefHeight());
            campoDinamico.setComprimento((int) toggleButton.getPrefWidth());
            campoDinamico.setLabel(toggleButton.getText());
            campoDinamico.setTipo("ToggleButton");
        } else if (component instanceof Separator) {
            Separator separador = (Separator) component;
            campoDinamico.setPosX((int) separador.getLayoutX());
            campoDinamico.setPosY((int) separador.getLayoutY());
            campoDinamico.setAltura((int) separador.getPrefHeight());
            campoDinamico.setComprimento((int) separador.getPrefWidth());
            campoDinamico.setTipo("Separador");
        }
        map.put(campoDinamico, component);
        campos.add(campoDinamico);
    }

    private void montaLabel(CampoDinamico campo) {
        Button label = new Button(campo.getLabel());
        label.setMinWidth(campo.getComprimento());
        label.setMinHeight(campo.getAltura());
        label.setPrefSize(campo.getComprimento(), campo.getAltura());
        label.setLayoutX(campo.getPosX());
        label.setLayoutY(campo.getPosY());
        osPane.getChildren().add(label);
        createComponente(label, campo);

    }

    private void montaTextField(CampoDinamico campo) {
        TextField textField = new TextField();
        textField.setMinWidth(campo.getComprimento());
        textField.setMinHeight(campo.getAltura());
        textField.setPrefSize(campo.getComprimento(), campo.getAltura());
        textField.setLayoutX(campo.getPosX());
        textField.setLayoutY(campo.getPosY());
        osPane.getChildren().add(textField);
        createComponente(textField, campo);
    }

    private void montaTextArea(CampoDinamico campo) {
        TextArea textArea = new TextArea();
        textArea.setMinWidth(campo.getComprimento());
        textArea.setMinHeight(campo.getAltura());
        textArea.setPrefSize(campo.getComprimento(), campo.getAltura());
        textArea.setLayoutX(campo.getPosX());
        textArea.setLayoutY(campo.getPosY());
        textArea.setEditable(campo.isIsEditavel());
        osPane.getChildren().add(textArea);
        createComponente(textArea, campo);
    }

    private void montaDatePicker(CampoDinamico campo) {
        DatePicker datePicker = new DatePicker();
        datePicker.setMinWidth(campo.getComprimento());
        datePicker.setMinHeight(campo.getAltura());
        datePicker.setPrefSize(campo.getComprimento(), campo.getAltura());
        datePicker.setLayoutX(campo.getPosX());
        datePicker.setLayoutY(campo.getPosY());
        osPane.getChildren().add(datePicker);
        createComponente(datePicker, campo);
    }

    private void montaComboBox(CampoDinamico campo) {
        ComboBox comboBox = new ComboBox();
        comboBox.setMinWidth(campo.getComprimento());
        comboBox.setMinHeight(campo.getAltura());
        comboBox.setPrefSize(campo.getComprimento(), campo.getAltura());
        comboBox.setLayoutX(campo.getPosX());
        comboBox.setLayoutY(campo.getPosY());
        osPane.getChildren().add(comboBox);
        createComponente(comboBox, campo);
    }

    private void montaToggleButton(CampoDinamico campo) {
        ToggleButton toggleButton = new ToggleButton(campo.getLabel());
        toggleButton.setMinWidth(campo.getComprimento());
        toggleButton.setMinHeight(campo.getAltura());
        toggleButton.setPrefSize(campo.getComprimento(), campo.getAltura());
        toggleButton.setLayoutX(campo.getPosX());
        toggleButton.setLayoutY(campo.getPosY());
        osPane.getChildren().add(toggleButton);
        createComponente(toggleButton, campo);
    }

    private void montaSeparador(CampoDinamico campo) {
        Separator separador = new Separator();
        separador.setMinWidth(campo.getComprimento());
        separador.setMinHeight(campo.getAltura());
        separador.setPrefSize(campo.getComprimento(), campo.getAltura());
        separador.setLayoutX(campo.getPosX());
        separador.setLayoutY(campo.getPosY());
        osPane.getChildren().add(separador);
        createComponente(separador, campo);
    }

    private void montaCheckBox(CampoDinamico campo) {
        CheckBox checkBox = new CheckBox(campo.getLabel());
        checkBox.setMinWidth(campo.getComprimento());
        checkBox.setMinHeight(campo.getAltura());
        checkBox.setPrefSize(campo.getComprimento(), campo.getAltura());
        checkBox.setLayoutX(campo.getPosX());
        checkBox.setLayoutY(campo.getPosY());
        osPane.getChildren().add(checkBox);
        createComponente(checkBox, campo);
    }

}
