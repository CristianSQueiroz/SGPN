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
import SGPNmodel.CampoDinamico;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class PropriedadesFXMLController implements Initializable {

    @FXML
    public ListView filtrosDiponiveis;
    public ListView filtrosEscolhidos;
    public ListView colunasDiponiveis;
    public ListView colunasEscolhidas;
    public Button escolheFiltro;
    public Button removeFiltro;
    public Button escolheTodosFiltros;
    public Button removeTodosFiltros;
    public Button escolheColuna;
    public Button removeColuna;
    public Button escolheTodosColunas;
    public Button removeTodosColunas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaListas();
    }
    
    public void carregaListas(){
        filtrosDiponiveis.getItems().addAll("TESTE","TESTE1","TESTE2");
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
