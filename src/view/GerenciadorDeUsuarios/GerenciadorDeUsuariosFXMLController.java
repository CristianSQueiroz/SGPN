/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.GerenciadorDeUsuarios;

import ClassesDeConexao.UsuarioDB;
import HashMap.CHashMap;
import SGPNmodel.CampoDinamico;
import Util.Utilidades;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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
        setOnMouseBehavior();
        controleBotoes(true, true, false, true, true);
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

    public void setOnMouseBehavior() {
        tableUsuario.setOnMouseClicked((javafx.scene.input.MouseEvent mouseEvent) -> {
            if (mouseEvent.getClickCount() == 2) {
                editarBTN();
            }
        });
    }

    public void novoBTN() {
        trocarPanelBTN(true);
        controleBotoes(false, false, true, false, true);
    }

    public void salvarBTN() {
        int id = 0;
        if (!idTF.getText().equals("")) {
            id = Utilidades.validaInt(idTF.getText());
        }
        Usuario usuario = new Usuario(id, nmUsuarioTF.getText(), senhaPF.getText());
        if (new UsuarioDB().cadastrarUsuario(usuario)) {
            idTF.setText("");
            nmUsuarioTF.setText("");
            senhaPF.setText("");
            carregaTable();
            trocarPanelBTN();
            controleBotoes(true, true, false, true, true);
        };

    }

    public void editarBTN() {
        if (tableUsuario.getSelectionModel().getSelectedItem() != null) {
            if (new UsuarioDB().cadastrarUsuario(tableUsuario.getSelectionModel().getSelectedItem())) {
                trocarPanelBTN();
                controleBotoes(false, false, true, false, true);
            }
        }
    }

    public void excluirBTN() {
        if (tableUsuario.getSelectionModel().getSelectedItem() != null) {
            Usuario usuario = tableUsuario.getSelectionModel().getSelectedItem();
            new UsuarioDB().excluirRegistro(usuario.getId());
            carregaTable();
            if (isPaneFormInFront) {
                trocarPanelBTN();
            }
            controleBotoes(true, true, false, true, true);

        }

    }

    public void trocarPanelBTN() {
        trocarPanelBTN(false);
    }

    public void trocarPanelBTN(boolean novoRegistro) {
        if (novoRegistro) {
            if (!isPaneFormInFront) {
                isPaneFormInFront = !isPaneFormInFront;
                scrollPaneTabela.toBack();
            }
            idTF.setText("");
            nmUsuarioTF.setText("");
            senhaPF.setText("");
        } else {
            if (isPaneFormInFront) {
                scrollPaneForm.toBack();
            } else {
                if (tableUsuario.getSelectionModel().getSelectedItem() != null) {
                    Usuario usuario = tableUsuario.getSelectionModel().getSelectedItem();
                    idTF.setText(String.valueOf(usuario.getId()));
                    nmUsuarioTF.setText(usuario.getNmUsuario());
                    senhaPF.setText(usuario.getDsSenha());
                }

                scrollPaneTabela.toBack();

            }
            isPaneFormInFront = !isPaneFormInFront;
        }
    }

    public void controleBotoes(boolean novo, boolean trocaPanel, boolean salvar, boolean editar, boolean excluir) {
        novoBTN.setDisable(!novo);
        formTabelaBTN.setDisable(!trocaPanel);
        salvarBTN.setDisable(!salvar);
        editarBTN.setDisable(!editar);
        excluirBTN.setDisable(!excluir);
    }
}
