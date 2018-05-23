/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.propriedades;

import ClassesDeConexao.AuxiliarMontagemTelaOSDB;
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

    public ArrayList<CHashMap> camposComAtributos;
    public CHashMap dados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaListas();
    }

    public void carregaListas() {
        dados = new AuxiliarMontagemTelaOSDB().getFiltrosColunas();
        for (Object object : dados.keySet()) {
            switch (object.toString()) {
                case "FILTROS_DISPONIVEIS": {
                    preencheLista(filtrosDiponiveis, (ArrayList<CHashMap>) dados.get(object));
                    break;
                }
                case "FILTROS_ESCOLHIDOS": {
                    preencheLista(filtrosEscolhidos, (ArrayList<CHashMap>) dados.get(object));
                    break;
                }
                case "COLUNAS_DISPONIVEIS": {
                    preencheLista(colunasDiponiveis, (ArrayList<CHashMap>) dados.get(object));
                    break;
                }
                case "COLUNAS_ESCOLHIDAS": {
                    preencheLista(colunasEscolhidas, (ArrayList<CHashMap>) dados.get(object));
                    break;
                }
            }
        }
    }

    public void preencheLista(ListView lista, ArrayList<CHashMap> valores) {
        valores.forEach((cHashMap) -> {
            lista.getItems().add(cHashMap);
        });
    }

    public void escolheFiltro() {
        if (filtrosDiponiveis.getSelectionModel().getSelectedItem() != null) {
            filtrosEscolhidos.getItems().add(filtrosDiponiveis.getSelectionModel().getSelectedItem());
            filtrosDiponiveis.getItems().remove(filtrosDiponiveis.getSelectionModel().getSelectedItem());
        }
    }

    public void escolheTodosFiltros() {
        filtrosDiponiveis.getItems().forEach((object) -> {
            filtrosEscolhidos.getItems().add(object);
        });
        filtrosDiponiveis.getItems().removeAll(filtrosDiponiveis.getItems());
    }

    public void removeFiltro() {
        if (filtrosEscolhidos.getSelectionModel().getSelectedItem() != null) {
            filtrosDiponiveis.getItems().add(filtrosEscolhidos.getSelectionModel().getSelectedItem());
            filtrosEscolhidos.getItems().remove(filtrosEscolhidos.getSelectionModel().getSelectedItem());
        }
    }

    public void removeTodosFiltros() {
        filtrosEscolhidos.getItems().forEach((object) -> {
            filtrosDiponiveis.getItems().add(object);
        });
        filtrosEscolhidos.getItems().removeAll(filtrosEscolhidos.getItems());
    }

    public void escolheColuna() {
        if (colunasDiponiveis.getSelectionModel().getSelectedItem() != null) {
            colunasEscolhidas.getItems().add(colunasDiponiveis.getSelectionModel().getSelectedItem());
            colunasDiponiveis.getItems().remove(colunasDiponiveis.getSelectionModel().getSelectedItem());
        }
    }

    public void escolheTodasColunas() {
        colunasDiponiveis.getItems().forEach((object) -> {
            colunasEscolhidas.getItems().add(object);
        });
        colunasDiponiveis.getItems().removeAll(colunasDiponiveis.getItems());
    }

    public void removeColuna() {
        if (colunasEscolhidas.getSelectionModel().getSelectedItem() != null) {
            colunasDiponiveis.getItems().add(colunasEscolhidas.getSelectionModel().getSelectedItem());
            colunasEscolhidas.getItems().remove(colunasEscolhidas.getSelectionModel().getSelectedItem());
        }
    }

    public void removeTodasColunas() {
        colunasEscolhidas.getItems().forEach((object) -> {
            colunasDiponiveis.getItems().add(object);
        });
        colunasEscolhidas.getItems().removeAll(colunasEscolhidas.getItems());
    }

    public void getCamposAlterados() {
        ArrayList<Integer> filtrosEscolhidosRemovidos = new ArrayList<Integer>();
        ArrayList<Integer> filtrosEscolhidosAdicionados = new ArrayList<Integer>();
        ArrayList<Integer> colunasEscolhidasRemovidas = new ArrayList<Integer>();
        ArrayList<Integer> colunasEscolhidasAdicionadas = new ArrayList<Integer>();

        ArrayList<CHashMap> filtrosEscolhidosAL = (ArrayList<CHashMap>) dados.get("FILTROS_ESCOLHIDOS");
        ArrayList<CHashMap> colunasEscolhidasAL = (ArrayList<CHashMap>) dados.get("COLUNAS_ESCOLHIDAS");

        for (CHashMap cHM : filtrosEscolhidosAL) {
            int id = cHM.getValorAsInt("ID");
            boolean encontrou = false;
            for (Object object : this.filtrosEscolhidos.getItems()) {
                CHashMap cHM2 = (CHashMap) object;
                if (id == cHM2.getValorAsInt("ID")) {
                    encontrou = true;
                    break;
                }
            }
            if (!encontrou) {
                filtrosEscolhidosRemovidos.add(id);
            }
        }

        for (Object object : this.filtrosEscolhidos.getItems()) {
            CHashMap cHM2 = (CHashMap) object;
            int id = cHM2.getValorAsInt("ID");
            boolean encontrou = false;

            for (CHashMap cHM : filtrosEscolhidosAL) {
                if (id == cHM.getValorAsInt("ID")) {
                    encontrou = true;
                    break;
                }

            }
            if (!encontrou) {
                filtrosEscolhidosAdicionados.add(id);
            }
        }

        //Colunas
        for (CHashMap cHM : colunasEscolhidasAL) {
            int id = cHM.getValorAsInt("ID");
            boolean encontrou = false;
            for (Object object : this.colunasEscolhidas.getItems()) {
                CHashMap cHM2 = (CHashMap) object;
                if (id == cHM2.getValorAsInt("ID")) {
                    encontrou = true;
                    break;
                }
            }
            if (!encontrou) {
                colunasEscolhidasRemovidas.add(id);
            }
        }

        for (Object object : this.colunasEscolhidas.getItems()) {
            CHashMap cHM2 = (CHashMap) object;
            int id = cHM2.getValorAsInt("ID");
            boolean encontrou = false;

            for (CHashMap cHM : colunasEscolhidasAL) {
                if (id == cHM.getValorAsInt("ID")) {
                    encontrou = true;
                    break;
                }

            }
            if (!encontrou) {
                colunasEscolhidasAdicionadas.add(id);
            }
        }
        CHashMap alterados = new CHashMap();
        alterados.put("FILTROS_REMOVIDOS", filtrosEscolhidosRemovidos);
        alterados.put("FILTROS_ADICIONADOS", filtrosEscolhidosAdicionados);
        alterados.put("COLUNAS_REMOVIDAS", colunasEscolhidasRemovidas);
        alterados.put("COLUNAS_ADICIONADAS", colunasEscolhidasAdicionadas);
        new AuxiliarMontagemTelaOSDB().setFiltrosColunasAlterados(alterados);
        //System.out.println("Filtros Removidos" + filtrosEscolhidosRemovidos);;
        //System.out.println("Filtros Adicionados" + filtrosEscolhidosAdicionados);
        //System.out.println("Colunas Removidas" + colunasEscolhidasRemovidas);
        //System.out.println("Colunas Adicionadas" + colunasEscolhidasAdicionadas);
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
