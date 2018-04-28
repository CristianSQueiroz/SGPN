/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.propriedades;

import ClassesDeConexao.CampoDinamicoOSDB;
import HashMap.CHashMap;
import Main.ConstrutorTela;
import Util.Utilidades;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.SwingUtilities;
import editordecampos.CampoDinamico;
import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class PropriedadesFXMLController implements Initializable {

    ArrayList<CampoDinamico> campos = new ArrayList<CampoDinamico>();

    @FXML
    private TextField idOS;
    @FXML
    private TextField seqTelaOS;
    @FXML
    private TextField dsDescricaoOS;
    @FXML
    private TextField dsLabelOS;
    @FXML
    private ComboBox<String> dsTipoOS;
    @FXML
    private ComboBox<String> dsTipoValorOS;
    @FXML
    private Button adicionarOS;
    @FXML
    private TextField posXOS;
    @FXML
    private TextField posYOS;
    @FXML
    private TextField qtdAlturaOS;
    @FXML
    private TextField qtdComprimentoOS;
    @FXML
    private TextArea dsSQl;
    @FXML
    private CheckBox isEditavelOS;

    @FXML
    private TreeView treeView;

    @FXML
    private TableView<CampoDinamico> tableOS;

    @FXML
    private TableColumn<CampoDinamico, Integer> columnIDOS;
    @FXML
    private TableColumn<CampoDinamico, Integer> columnSeqTelaOS;
    @FXML
    private TableColumn<CampoDinamico, String> columnDsDescricaoOS;
    @FXML
    private TableColumn<CampoDinamico, String> columnDsLabelOS;
    @FXML
    private TableColumn<CampoDinamico, String> columnDsTipoOS;
    @FXML
    private TableColumn<CampoDinamico, String> columnIsEditavelOS;
    @FXML
    private TableColumn<CampoDinamico, Integer> columnPosXOS;
    @FXML
    private TableColumn<CampoDinamico, Integer> columnPosYOS;
    @FXML
    private TableColumn<CampoDinamico, Integer> columnAlturaOS;
    @FXML
    private TableColumn<CampoDinamico, Integer> columnComprimentoOS;
    @FXML
    private TableColumn<CampoDinamico, String> columnSQLOS;

    @FXML
    private AnchorPane anchorPaneOS;
    @FXML
    private ScrollPane scrollPaneOS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campos = getCamposTeste();
        carregaTelaOS();
    }

    public void carregaTelaOS() {
        carregaTable();
        carregaComboBox();
    }

    public void carregaTable() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                columnIDOS.setCellValueFactory(new PropertyValueFactory<>("id"));
                columnSeqTelaOS.setCellValueFactory(new PropertyValueFactory<>("nrSeqTela"));
                columnDsDescricaoOS.setCellValueFactory(new PropertyValueFactory<>("dsDescricao"));
                columnDsLabelOS.setCellValueFactory(new PropertyValueFactory<>("label"));
                columnDsTipoOS.setCellValueFactory(new PropertyValueFactory<>("tipo"));
                columnIsEditavelOS.setCellValueFactory(new PropertyValueFactory<>("isEditavel"));
                columnPosXOS.setCellValueFactory(new PropertyValueFactory<>("posX"));
                columnPosYOS.setCellValueFactory(new PropertyValueFactory<>("posY"));
                columnAlturaOS.setCellValueFactory(new PropertyValueFactory<>("altura"));
                columnComprimentoOS.setCellValueFactory(new PropertyValueFactory<>("comprimento"));
                columnSQLOS.setCellValueFactory(new PropertyValueFactory<>("dsSQL"));
                tableOS.setItems(getCamposOS());
            }
        });
        previewOS();
    }

    public void carregaComboBox() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dsTipoOS.getItems().addAll("TextField", "Label", "ComboBox", "TextArea");
                dsTipoValorOS.getItems().addAll("Letras", "Numérico", "Letras e numéricos");
            }
        });
    }

    public void adicionarCampoOS() {
        CampoDinamico campoDinamico = new CampoDinamico();
        campoDinamico.setId(new CampoDinamicoOSDB().getAutoIncrement(false));
        //campoDinamico.setNrSeqTela(Utilidades.validaInt(seqTelaOS.getText()));
        campoDinamico.setDsSQL(dsSQl.getText());
        campoDinamico.setAltura(Utilidades.validaInt(qtdAlturaOS.getText()));
        campoDinamico.setComprimento(Utilidades.validaInt(qtdComprimentoOS.getText()));
        campoDinamico.setDsDescricao(dsDescricaoOS.getText());
        campoDinamico.setIsEditavel(isEditavelOS.isSelected());
        campoDinamico.setLabel(dsLabelOS.getText());
        campoDinamico.setPosX(Utilidades.validaInt(posXOS.getText()));
        campoDinamico.setPosY(Utilidades.validaInt(posYOS.getText()));
        campoDinamico.setTipo(dsTipoOS.getValue());
        campoDinamico.setTipoValor(dsTipoValorOS.getValue());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CampoDinamicoOSDB().cadastrarCampo(campoDinamico,false);
            }
        });
        campos.add(campoDinamico);
        carregaTable();
    }

    public ObservableList<CampoDinamico> getCamposOS() {
        ObservableList<CampoDinamico> camposObservable = FXCollections.observableArrayList();
        for (CampoDinamico campo : campos) {
            camposObservable.add(campo);
        };
        return camposObservable;
    }

    public static ArrayList<CampoDinamico> getCamposTeste() {
        ArrayList<CHashMap> camposDB = new CampoDinamicoOSDB().getCampoDinamicosTodosCampos("ID");
        ArrayList<CampoDinamico> campos = new ArrayList<CampoDinamico>();
        for (CHashMap campoDB : camposDB) {
            CampoDinamico campoDinamicoTemp = new CampoDinamico();
            campoDinamicoTemp.setId(campoDB.getValorAsInt("ID"));
            campoDinamicoTemp.setLabel(campoDB.getValorAsString("DS_LABEL"));
            campoDinamicoTemp.setDsDescricao(campoDB.getValorAsString("DS_DESCRICAO"));
            campoDinamicoTemp.setTipo(campoDB.getValorAsString("DS_TIPO"));
            campoDinamicoTemp.setIsEditavel(campoDB.getValorAsString("IS_EDITAVEL").equalsIgnoreCase("S"));
            campoDinamicoTemp.setPosX(50);
            campoDinamicoTemp.setPosY(50);
            campos.add(campoDinamicoTemp);
        }
        return campos;
    }

    public void previewOS() {
        scrollPaneOS.setContent(ConstrutorTela.getTelaCampoDinamicoAsAnchorPane(campos));
    }

    public void removerRegistro() {
        campos.remove(tableOS.getSelectionModel().getSelectedItem());
        new CampoDinamicoOSDB().deleteRegistro(tableOS.getSelectionModel().getSelectedItem().getId(),false);
        carregaTable();
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void stop(ActionEvent event) {
        System.out.println("Stage is closing");
    }
}
