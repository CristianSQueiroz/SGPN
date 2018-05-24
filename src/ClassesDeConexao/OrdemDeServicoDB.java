/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDeConexao;

import HashMap.CHashMap;
import SQLUtil.MySqlConnect;
import Util.UtilSql;
import Util.Utilidades;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import SGPNmodel.CampoDinamico;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cristian
 */
public class OrdemDeServicoDB {

    private String cmd;
    private PreparedStatement stmt;
    private MySqlConnect connect;
    public ArrayList<CHashMap> retorno;
    CHashMap singleRetorno;
    Utilidades util = new Utilidades();
    UtilSql utilsql = new UtilSql();

    public OrdemDeServicoDB() {
        connect = MySqlConnect.getInstance();
    }
    
    public ArrayList<CHashMap> getOrdensDeServicos(CHashMap atributos,String orderBy){
        cmd = UtilSql.montaQuery(atributos, "TB_OS_DINAMICA", orderBy);
        return connect.executaConsultaPadrao(cmd, true);
    }

    public boolean update(CHashMap atributos, CHashMap restricoes, boolean lote) {
        cmd = utilsql.montaQueryUpdate(atributos, restricoes, "CAMPO_DINAMICO_OS");
        if (lote) {
            return connect.executaComandoPadraoLote(cmd);
        } else {
            return connect.executaComandoPadrao(cmd);
        }
    }

    public void openConnection() {
        connect.open();
    }

    public void closeConnection() {
        connect.close();
    }
}
