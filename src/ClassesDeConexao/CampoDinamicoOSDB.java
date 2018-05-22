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
public class CampoDinamicoOSDB {

    private String cmd;
    private PreparedStatement stmt;
    private MySqlConnect connect;
    public ArrayList<CHashMap> retorno;
    CHashMap singleRetorno;
    Utilidades util = new Utilidades();
    UtilSql utilsql = new UtilSql();

    public CampoDinamicoOSDB() {
        connect = MySqlConnect.getInstance();
    }

    public void adicionarCamposOS(ArrayList<CampoDinamico> camposImport) {
        SwingUtilities.invokeLater(() -> {
            CampoDinamicoOSDB campo = new CampoDinamicoOSDB();
            connect.open();
            for (CampoDinamico campoDinamico : camposImport) {
                new CampoDinamicoOSDB().cadastrarCampo(campoDinamico, true);
            };
            connect.executaComandoPadraoLote("call CRIA_CAMPOS_OS_DINAMICA()");
            connect.close();
        });
    }

    public void removerCamposOS(ArrayList<CampoDinamico> camposRemovidos) {
        SwingUtilities.invokeLater(() -> {
            CampoDinamicoOSDB campo = new CampoDinamicoOSDB();
            connect.open();
            for (CampoDinamico campoDinamico : camposRemovidos) {
                new CampoDinamicoOSDB().deleteRegistro(campoDinamico.getId(), true);
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

    public boolean cadastrarCampo(CampoDinamico campo, boolean lote) {
        if (campo.getId() > 0) {
            CHashMap hmCampos = new CHashMap();
            hmCampos.put("DS_DESCRICAO", campo.getDsDescricao());
            hmCampos.put("DS_LABEL", campo.getLabel());
            hmCampos.put("DS_TIPO", campo.getTipo());
            hmCampos.put("DS_TIPO_VALOR", campo.getTipoValor());
            hmCampos.put("POS_X", campo.getPosX());
            hmCampos.put("POS_Y", campo.getPosY());
            hmCampos.put("QTD_ALTURA", campo.getAltura());
            hmCampos.put("QTD_COMPRIMENTO", campo.getComprimento());
            hmCampos.put("DS_SQL", campo.getDsSQL());
            hmCampos.put("DS_ATRIBUTO", campo.getDsAtributo());
            hmCampos.put("IS_EDITAVEL", (campo.isIsEditavel()) ? "S" : "N");
            hmCampos.put("NM_USUARIO_ATUALIZACAO", "CSQUEIROZ");
            CHashMap restricao = new CHashMap();
            restricao.put("ID", campo.getId());
            return update(hmCampos, restricao, lote);
        } else {

            cmd = "INSERT INTO CAMPO_DINAMICO_OS (ID,"
                    + "DS_DESCRICAO,"
                    + "DS_LABEL,"
                    + "DS_TIPO,"
                    + "DS_TIPO_VALOR,"
                    + "POS_X,"
                    + "POS_Y,"
                    + "QTD_ALTURA,"
                    + "QTD_COMPRIMENTO,"
                    + "DS_SQL,"
                    + "IS_EDITAVEL,"
                    + "DS_ATRIBUTO,"
                    + "NM_USUARIO_RECORD,"
                    + "NM_USUARIO_ATUALIZACAO) values (";
            int id = getAutoIncrement(lote);
            cmd += id + ","
                    + utilsql.aplicarApostofo(campo.getDsDescricao()) + " , "
                    + utilsql.aplicarApostofo(campo.getLabel()) + " , "
                    + utilsql.aplicarApostofo(campo.getTipo()) + " , "
                    + utilsql.aplicarApostofo(Utilidades.validaString(campo.getTipoValor())) + " , "
                    + campo.getPosX() + " , "
                    + campo.getPosY() + " , "
                    + campo.getAltura() + " , "
                    + campo.getComprimento() + " , "
                    + utilsql.aplicarApostofo(campo.getDsSQL()) + " , "
                    + utilsql.aplicarApostofo("S") + " , "
                    + utilsql.aplicarApostofo(campo.getDsAtributo()) + " , "
                    + utilsql.aplicarApostofo("CSQUEIROZ") + " , "
                    + utilsql.aplicarApostofo("CSQUEIROZ") + ")";
            if (lote) {
                return connect.executaComandoPadraoLote(cmd);
            } else {
                return connect.executaComandoPadrao(cmd, true);
            }

        }
    }

    public ArrayList<CHashMap> getCampoDinamicos(CHashMap atributos, String orderby) {
        cmd = utilsql.montaQuery(atributos, "CAMPO_DINAMICO_OS", orderby);
        return connect.executaConsultaPadrao(cmd);
    }

    public ArrayList<CHashMap> getCampoDinamicosTodosCampos(String orderby) {
        cmd = "SELECT * FROM CAMPO_DINAMICO_OS ORDER BY " + orderby;
        return connect.executaConsultaPadrao(cmd);
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
