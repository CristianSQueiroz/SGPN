/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.CadastroOS;

import Main.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class CadastroOrdemDeServicoFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static void montaTelaCadastro() {
        Main.openCustomWindow(CadastroOrdemDeServicoFXMLController.class, "CadastroOrdemDeServicoFXML.fxml");
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
