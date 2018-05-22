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
public class AuxiliarMontagemTelaOSDB {

    private String cmd;
    private PreparedStatement stmt;
    private MySqlConnect connect;
    public ArrayList<CHashMap> retorno;
    CHashMap singleRetorno;
    Utilidades util = new Utilidades();
    UtilSql utilsql = new UtilSql();

    public AuxiliarMontagemTelaOSDB() {
        connect = MySqlConnect.getInstance();
    }

    public void adicionarColunasEscolhidas() {
        SwingUtilities.invokeLater(() -> {
            AuxiliarMontagemTelaOSDB campo = new AuxiliarMontagemTelaOSDB();
            connect.open();
            connect.executaComandoPadraoLote("call CRIA_CAMPOS_OS_DINAMICA()");
            connect.close();
        });
    }
    
    public void adicionarFiltrosEscolhidos() {
        SwingUtilities.invokeLater(() -> {
            AuxiliarMontagemTelaOSDB campo = new AuxiliarMontagemTelaOSDB();
            connect.open();
            connect.executaComandoPadraoLote("call CRIA_CAMPOS_OS_DINAMICA()");
            connect.close();
        });
    }

    public void removerCamposOS(ArrayList<CampoDinamico> camposRemovidos) {
        SwingUtilities.invokeLater(() -> {
            AuxiliarMontagemTelaOSDB campo = new AuxiliarMontagemTelaOSDB();
            connect.open();
            for (CampoDinamico campoDinamico : camposRemovidos) {
                new AuxiliarMontagemTelaOSDB().deleteRegistro(campoDinamico.getId(), true);
            };
            connect.close();
        });
    }

    public ArrayList<CHashMap> getLabelCamposComAtributo() {
        cmd = "SELECT a.ID, a.DS_LABEL FROM CAMPO_DINAMICO_OS a WHERE a.DS_ATRIBUTO <> '' ORDER BY ID";
        ArrayList<CHashMap> lista = new ArrayList<CHashMap>();
        retorno = connect.executaConsultaPadrao(cmd);
        for (int x = 0; x < retorno.size(); x++) {
            CHashMap cHashMap = new CHashMap();
            cHashMap.put("ID", retorno.get(x).getValorAsInt("ID"));
            cHashMap.put("DS_LABEL", retorno.get(x).getValorAsString("DS_LABEL"));
            lista.add(cHashMap);
        }
        return lista;
    }

    public int getAutoIncrement(boolean lote) {
        String osValue = "SELECT Auto_increment FROM information_schema.tables WHERE table_name='CAMPO_DINAMICO_OS'";
        if (lote) {
            retorno = connect.executaConsultaPadraoLote(osValue);
        } else {
            retorno = connect.executaConsultaPadrao(osValue);
        }
        return retorno.get(0).getValorAsInt("AUTO_INCREMENT");
    }

    public boolean deleteRegistro(int id, boolean lote) {
        cmd = "DELETE FROM CAMPO_DINAMICO_OS WHERE ID=" + id;
        if (lote) {
            return connect.executaComandoPadraoLote(cmd);
        } else {
            return connect.executaComandoPadrao(cmd);
        }

    }

    public boolean update(CHashMap atributos, CHashMap restricoes, boolean lote) {
        cmd = utilsql.montaQueryUpdate(atributos, restricoes, "CAMPO_DINAMICO_OS");
        if (lote) {
            return connect.executaComandoPadraoLote(cmd);
        } else {
            return connect.executaComandoPadrao(cmd);
        }
    }
}
