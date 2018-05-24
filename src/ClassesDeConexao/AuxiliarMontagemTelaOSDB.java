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

    public void adicionarFiltrosColunasEscolhidos(int id, boolean lote, String nmTabela) {
        cmd = "INSERT INTO " + nmTabela + " VALUES(NULL," + id + ")";
        if (lote) {
            connect.executaComandoPadraoLote(cmd);
        } else {
            connect.open();
            connect.executaComandoPadrao(cmd);
            connect.close();
        }
    }

    public CHashMap getFiltrosColunas() {
        CHashMap retorno = new CHashMap();
        connect.open();
        retorno.put("FILTROS_DISPONIVEIS", getLabelFiltrosDisponiveis(true));
        retorno.put("FILTROS_ESCOLHIDOS", getLabelFiltrosEscolhidos(true));
        retorno.put("COLUNAS_DISPONIVEIS", getLabelColunasDisponiveis(true));
        retorno.put("COLUNAS_ESCOLHIDAS", getLabelColunasEscolhidas(true));
        connect.close();
        return retorno;
    }

    public void setFiltrosColunasAlterados(CHashMap filtrosColunas) {
        CHashMap retorno = new CHashMap();
        connect.open();
        ArrayList<Integer> filtrosRemovidos = (ArrayList<Integer>) filtrosColunas.get("FILTROS_REMOVIDOS");
        ArrayList<Integer> filtrosAdicionados = (ArrayList<Integer>) filtrosColunas.get("FILTROS_ADICIONADOS");
        ArrayList<Integer> colunasRemovidas = (ArrayList<Integer>) filtrosColunas.get("COLUNAS_REMOVIDAS");
        ArrayList<Integer> colunasAdicionadas = (ArrayList<Integer>) filtrosColunas.get("COLUNAS_ADICIONADAS");
        if (filtrosRemovidos != null) {
            for (Integer filtroID : filtrosRemovidos) {
                deleteRegistro(filtroID, true, "TB_FILTROS_ESCOLHIDOS_OS");
            }
        }
        if (colunasRemovidas != null) {
            for (Integer colunasID : colunasRemovidas) {
                deleteRegistro(colunasID, true, "TB_COLUNAS_ESCOLHIDAS_OS");
            }
        }
        if (filtrosAdicionados != null) {
            for (Integer filtroID : filtrosAdicionados) {
                adicionarFiltrosColunasEscolhidos(filtroID, true, "TB_FILTROS_ESCOLHIDOS_OS");
            }
        }
        if (colunasAdicionadas != null) {
            for (Integer colunasID : colunasAdicionadas) {
                adicionarFiltrosColunasEscolhidos(colunasID, true, "TB_COLUNAS_ESCOLHIDAS_OS");
            }
        }
        connect.close();
    }

    public ArrayList<CHashMap> getLabelFiltrosDisponiveis(boolean lote) {
        cmd = "SELECT a.ID, a.DS_LABEL "
                + "FROM CAMPO_DINAMICO_OS a "
                + "WHERE a.DS_ATRIBUTO <> '' "
                + "AND (SELECT COUNT(*) "
                + "FROM TB_FILTROS_ESCOLHIDOS_OS tfeo "
                + "WHERE tfeo.ID_CAMPO = a.ID) = 0 "
                + "ORDER BY ID";
        ArrayList<CHashMap> lista = new ArrayList<CHashMap>();
        if (lote) {
            retorno = connect.executaConsultaPadraoLote(cmd);
        } else {
            retorno = connect.executaConsultaPadrao(cmd);
        }
        for (int x = 0; x < retorno.size(); x++) {
            CHashMap cHashMap = new CHashMap();
            cHashMap.put("ID", retorno.get(x).getValorAsInt("ID"));
            cHashMap.put("DS_LABEL", retorno.get(x).getValorAsString("DS_LABEL"));
            lista.add(cHashMap);
        }
        return lista;
    }

    public ArrayList<CHashMap> getLabelFiltrosEscolhidos(boolean lote) {
        cmd = "SELECT a.ID, a.DS_LABEL "
                + "FROM CAMPO_DINAMICO_OS a "
                + "WHERE a.DS_ATRIBUTO <> '' "
                + "AND (SELECT COUNT(*) "
                + "FROM TB_FILTROS_ESCOLHIDOS_OS tfeo "
                + "WHERE tfeo.ID_CAMPO = a.ID) = 1 "
                + "ORDER BY ID";
        ArrayList<CHashMap> lista = new ArrayList<CHashMap>();
        if (lote) {
            retorno = connect.executaConsultaPadraoLote(cmd);
        } else {
            retorno = connect.executaConsultaPadrao(cmd);
        }
        for (int x = 0; x < retorno.size(); x++) {
            CHashMap cHashMap = new CHashMap();
            cHashMap.put("ID", retorno.get(x).getValorAsInt("ID"));
            cHashMap.put("DS_LABEL", retorno.get(x).getValorAsString("DS_LABEL"));
            lista.add(cHashMap);
        }
        return lista;
    }

    public ArrayList<CHashMap> getLabelColunasDisponiveis(boolean lote) {
        cmd = "SELECT a.ID, a.DS_LABEL "
                + "FROM CAMPO_DINAMICO_OS a "
                + "WHERE a.DS_ATRIBUTO <> '' "
                + "AND (SELECT COUNT(*) "
                + "FROM TB_COLUNAS_ESCOLHIDAS_OS tceo "
                + "WHERE tceo.ID_CAMPO = a.ID) = 0 "
                + "ORDER BY ID";
        ArrayList<CHashMap> lista = new ArrayList<CHashMap>();
        if (lote) {
            retorno = connect.executaConsultaPadraoLote(cmd);
        } else {
            retorno = connect.executaConsultaPadrao(cmd);
        }
        for (int x = 0; x < retorno.size(); x++) {
            CHashMap cHashMap = new CHashMap();
            cHashMap.put("ID", retorno.get(x).getValorAsInt("ID"));
            cHashMap.put("DS_LABEL", retorno.get(x).getValorAsString("DS_LABEL"));
            lista.add(cHashMap);
        }
        return lista;
    }

    public ArrayList<CHashMap> getLabelColunasEscolhidas(boolean lote) {
        cmd = "SELECT a.ID, a.DS_LABEL "
                + "FROM CAMPO_DINAMICO_OS a "
                + "WHERE a.DS_ATRIBUTO <> '' "
                + "AND (SELECT COUNT(*) "
                + "FROM TB_COLUNAS_ESCOLHIDAS_OS tceo "
                + "WHERE tceo.ID_CAMPO = a.ID) = 1 "
                + "ORDER BY ID";
        ArrayList<CHashMap> lista = new ArrayList<CHashMap>();
        if (lote) {
            retorno = connect.executaConsultaPadraoLote(cmd);
        } else {
            retorno = connect.executaConsultaPadrao(cmd);
        }
        for (int x = 0; x < retorno.size(); x++) {
            CHashMap cHashMap = new CHashMap();
            cHashMap.put("ID", retorno.get(x).getValorAsInt("ID"));
            cHashMap.put("DS_LABEL", retorno.get(x).getValorAsString("DS_LABEL"));
            lista.add(cHashMap);
        }
        return lista;
    }
    
    public ArrayList<CHashMap> getColunasTableView(boolean lote) {
        cmd = "SELECT a.DS_LABEL,a.DS_ATRIBUTO "
                + "FROM CAMPO_DINAMICO_OS a "
                + "WHERE a.DS_ATRIBUTO <> '' "
                + "AND (SELECT COUNT(*) "
                + "FROM TB_COLUNAS_ESCOLHIDAS_OS tceo "
                + "WHERE tceo.ID_CAMPO = a.ID) = 1 "
                + "ORDER BY ID";
        ArrayList<CHashMap> lista = new ArrayList<CHashMap>();
        if (lote) {
            retorno = connect.executaConsultaPadraoLote(cmd);
        } else {
            retorno = connect.executaConsultaPadrao(cmd);
        }
        for (int x = 0; x < retorno.size(); x++) {
            CHashMap cHashMap = new CHashMap();
            cHashMap.put("DS_LABEL", retorno.get(x).getValorAsString("DS_LABEL"));
            cHashMap.put("DS_ATRIBUTO", retorno.get(x).getValorAsString("DS_ATRIBUTO"));
            lista.add(cHashMap);
        }
        return lista;
    }

    public boolean deleteRegistro(int id, boolean lote, String nmTabela) {
        cmd = "DELETE FROM " + nmTabela + " WHERE ID_CAMPO=" + id;
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

    public void openConnection() {
        connect.open();
    }

    public void closeConnection() {
        connect.close();
    }
}
