/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.GerenciadorDeUsuarios;

import ClassesDeConexao.UsuarioDB;
import HashMap.CHashMap;
import SGPNmodel.CampoDinamico;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class GerenciadorDeUsuariosFXMLController implements Initializable {

    @FXML
    public Button novoBTN;
    public Button editarBTN;
    public Button salvarBTN;
    public Button excluirBTN;
    public Button formTabelaBTN;
    public StackPane stackPane;
    public ScrollPane scrollPaneForm;
    public ScrollPane scrollPaneTabela;
    public TextField idTF;
    public TextField nmUsuarioTF;
    public PasswordField senhaPF;
    @FXML
    private TableView<Usuario> tableUsuario;
    @FXML
    private TableColumn<Usuario, Integer> columnIDUsuario;
    @FXML
    private TableColumn<Usuario, String> columnDsUsuario;

    public boolean isPaneFormInFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaTable();
    }

    public void carregaTable() {
        columnIDUsuario.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDsUsuario.setCellValueFactory(new PropertyValueFactory<>("nmUsuario"));
        CHashMap cHashMap = new CHashMap();
        cHashMap.put("ID_USUARIO", null);
        cHashMap.put("NM_USUARIO", null);
        cHashMap.put("DS_SENHA", null);

        ArrayList<CHashMap> usuarios = new UsuarioDB().getUsuarios(cHashMap, "ID_USUARIO");
        ObservableList<Usuario> usuariosObservable = FXCollections.observableArrayList();
        usuarios.forEach((usuario) -> {
            Usuario usuarioLoop = new Usuario(usuario.getValorAsInt("ID_USUARIO"), usuario.getValorAsString("NM_USUARIO"), usuario.getValorAsString("DS_SENHA"));
            usuariosObservable.add(usuarioLoop);
        });
        tableUsuario.setItems(usuariosObservable);

    }

    public void novoBTN() {

    }

    public void salvarBTN() {

    }

    public void editarBTN() {

    }

    public void excluirBTN() {

    }

    public void trocarPanelBTN() {
        if (tableUsuario.getSelectionModel().getSelectedItem() != null) {
            if (isPaneFormInFront) {
                scrollPaneForm.toBack();
            } else {
                Usuario usuario = tableUsuario.getSelectionModel().getSelectedItem();
                idTF.setText(String.valueOf(usuario.getId()));
                nmUsuarioTF.setText(usuario.getNmUsuario());
                senhaPF.setText(usuario.getDsSenha());
                scrollPaneTabela.toBack();
                
            }
            isPaneFormInFront = !isPaneFormInFront;
        }
    }
}

