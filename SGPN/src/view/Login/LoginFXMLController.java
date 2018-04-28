/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Login;

import ClassesDeConexao.UsuarioDB;
import Main.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Usuario;
import view.MainFrame.MainFrame;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class LoginFXMLController {

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    public void logar() {
        verificar();
    }

    @FXML
    public void fechar() {
        Main.fecharAplicacao();
    }

    public void conectaBanco() {
        if (new UsuarioDB().loginUsuario(new Usuario(0, usernameTF.getText(), passwordPF.getText()))) {
            Main.openWindow(MainFrame.class, "MainFrameFXML.fxml");
        } else {
            JOptionPane.showMessageDialog(null, "Usuário/Senha inválido.");
        }
    }

    public void verificar() {
        if (usernameTF.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Look, a Warning Dialog");
            alert.setContentText("É necessario ter um nome de usúario");
            alert.showAndWait();
            JOptionPane.showMessageDialog(null, "É necessario ter um nome de usúario");
        } else if (passwordPF.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "É necessario tem uma senha");
        } else {
            conectaBanco();
        }
    }

    public void showPropriedades(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
