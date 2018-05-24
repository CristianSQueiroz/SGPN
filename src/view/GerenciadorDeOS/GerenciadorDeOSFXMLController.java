/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.GerenciadorDeOS;

import ClassesDeConexao.AuxiliarMontagemTelaOSDB;
import ClassesDeConexao.OrdemDeServicoDB;
import HashMap.CHashMap;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ColunaGerenciadorOS;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class GerenciadorDeOSFXMLController implements Initializable {

    @FXML
    public TableView tableView;

    public ArrayList<CHashMap> vinculoColunas = new ArrayList<CHashMap>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaColunas();
        atualizaTabela();
    }

    public void carregaColunas() {
        vinculoColunas = new AuxiliarMontagemTelaOSDB().getColunasTableView(false);
        for (CHashMap cHM : vinculoColunas) {
            TableColumn<CHashMap, String> coluna = new TableColumn<>(cHM.getValorAsString("DS_LABEL").replace(":", ""));
            String atributo = cHM.getValorAsString("DS_ATRIBUTO");
            if (atributo.contains("DS_") || atributo.contains("NM_")) {
                coluna.setPrefWidth(200);
            }else{
                coluna.setPrefWidth(100);
            }
            tableView.getColumns().add(coluna);
        }

    }

    public void atualizaTabela(){
        CHashMap atributos = new CHashMap();
        for (CHashMap cHM : vinculoColunas) {
            atributos.put(cHM.getValorAsString("DS_ATRIBUTO"), null);
        }
        ArrayList<CHashMap> dados = new OrdemDeServicoDB().getOrdensDeServicos(atributos, "ID");
        
    }
}
