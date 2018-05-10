/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import ClassesDeConexao.CampoDinamicoOSDB;
import HashMap.CHashMap;
import SGPNmodel.CampoDinamico;
import editordecampos.EditorDeCamposFXMLController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.CadastroOS.CadastroOrdemDeServicoFXMLController;
import view.Login.LoginFX;
import view.MainFrame.MainFrame;
import view.propriedades.PropriedadesFXMLController;

/**
 *
 * @author Cristian
 */
public class Main extends Application {

    private static Stage stage;
    private static ArrayList<Stage> stages;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stages = new ArrayList<Stage>();
        this.stage = stage;
        openWindow(LoginFX.class, "LoginFXML.fxml");
    }

    public static void openWindow(Class classe, String fxml) {
        try {
            if (stage != null) {
                stage.hide();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(classe.getResource(fxml));
            Parent root = (Parent) fxmlLoader.load();
            Stage stageNew = new Stage();
            stageNew.setScene(new Scene(root));
            if (!classe.equals(LoginFX.class)) {
                //stageNew.setFullScreen(true);
            }

            stageNew.setTitle("SGPN - Sistema de Gerenciamento de Pequenos negócios");
            /*stageNew.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Main.openLastStage();
                }
            });
            stages.add(stageNew);*/
            stage = stageNew;
            stage.show();
            stage.setOnCloseRequest((event) -> {
                Main.openWindow(MainFrame.class, "MainFrameFXML.fxml");
            });
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void openWindowEditorDeCampos() {
        try {
            if (stage != null) {
                stage.hide();
            }
            EditorDeCamposFXMLController editorDeCampos = new EditorDeCamposFXMLController();

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
            editorDeCampos.setCamposDinamicos(campos);

            FXMLLoader fxmlLoader = new FXMLLoader(EditorDeCamposFXMLController.class.getResource("EditorDeCamposFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stageNew = new Stage();
            stageNew.setScene(new Scene(root));
            stageNew.setTitle("SGPN - Sistema de Gerenciamento de Pequenos negócios");
            stage = stageNew;
            stage.show();
            stage.setOnCloseRequest((event) -> {
                System.out.println("Salvar");
                new CampoDinamicoOSDB().adicionarCamposOS(EditorDeCamposFXMLController.getCamposAlteradosDB());
                new CampoDinamicoOSDB().removerCamposOS(EditorDeCamposFXMLController.getCamposRemovidosDB());
                Main.openWindow(MainFrame.class, "MainFrameFXML.fxml");
            });
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void openCustomWindow(Class classe, String fxml) {
        if (stage != null) {
            stage.hide();

        }
        if (classe.equals(CadastroOrdemDeServicoFXMLController.class)) {

            stage.setScene(ConstrutorTela.getTela(new CampoDinamicoOSDB().getCampoDinamicosTodosCampos("ID")));
        }
        stage.getScene().getStylesheets().add("Main/ConstrutorTela.css");
        //stage.setFullScreenExitHint("");
        // stage.setFullScreen(true);
        stage.setTitle("SGPN - Sistema de Gerenciamento de Pequenos negócios");
        stage.show();
        stage.setOnCloseRequest((event) -> {
            /*            System.out.println("Salvar");
            new CampoDinamicoOSDB().adicionarCamposOS(EditorDeCamposFXMLController.getCamposAlteradosDB());
             */
            Main.openWindow(MainFrame.class, "MainFrameFXML.fxml");
        });

    }

    public static void fecharAplicacao() {
        Platform.exit();
        System.exit(0);
    }

}
