/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.MainFrame;

import java.io.IOException;
import javafx.fxml.FXML;
import Main.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import view.CadastroOS.CadastroOSController;
import view.CadastroOS.CadastroOrdemDeServicoFXMLController;
import view.GerenciadorDeOS.GerenciadorDeOSFXMLController;
import view.GerenciadorDeUsuarios.GerenciadorDeUsuariosFXMLController;
import view.propriedades.PropriedadesFXMLController;

/**
 *
 * @author Cristian
 */
public class MainFrameController {

    @FXML
    public void fecharAplicacao() {
        Main.fecharAplicacao();
    }

    @FXML
    public void openEditorDeCampos() throws IOException {
        Main.openWindowEditorDeCampos();
    }

    @FXML
    public void openConfiguracoes() throws IOException {
        Main.openWindow(PropriedadesFXMLController.class, "PropriedadesFXML.fxml");
    }

    @FXML
    public void openGerenciadorDeUsuarios() throws IOException {
        Main.openWindow(GerenciadorDeUsuariosFXMLController.class, "GerenciadorDeUsuariosFXML.fxml");
    }

    @FXML
    public void openGerenciadorDeOS() throws IOException {
        Main.openWindow(GerenciadorDeOSFXMLController.class, "GerenciadorDeOSFXML.fxml");
    }

    @FXML
    public void openCadastroOS() throws IOException {
        Main.openWindow(CadastroOSController.class, "CadastroOS.fxml");
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
