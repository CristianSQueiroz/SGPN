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
import model.FichaCadastro;

/**
 *
 * @author Cristian
 */
public class VendaDB {

    private String cmd;
    private PreparedStatement stmt;
    private MySqlConnect connect;
    public ArrayList<CHashMap> retorno;
    CHashMap singleRetorno;
    Utilidades util = new Utilidades();
    UtilSql utilsql = new UtilSql();

    public VendaDB() {
        connect = MySqlConnect.getInstance();
    }

    public boolean cadastrarFicha(CHashMap Atributos) {
        String osValue = "SELECT Auto_increment FROM information_schema.tables WHERE table_name='TB_OS_DINAMICA'";
        retorno = connect.executaConsultaPadrao(osValue);
        Atributos.put("NR_ID_PADRAO", retorno.get(0).getValorAsInt("AUTO_INCREMENT"));
        cmd = UtilSql.montaQueryInsert(Atributos, "TB_OS_DINAMICA");
        return connect.executaComandoPadrao(cmd, true);
    }

    public ArrayList<CHashMap> getVendas(CHashMap atributos, String orderby) {
        cmd = utilsql.montaQuery(atributos, "TB_OS_DINAMICA", orderby);
        return connect.executaConsultaPadrao(cmd);
    }

    public ArrayList<CHashMap> getVendas(CHashMap atributos, CHashMap restricoes, String orderby) {
        cmd = utilsql.montaQuery(atributos, restricoes, "TB_OS_DINAMICA", orderby);
        return connect.executaConsultaPadrao(cmd);
    }

    public boolean update(CHashMap atributos, CHashMap restricoes) {
        cmd = utilsql.montaQueryUpdate(atributos, restricoes, "TB_OS_DINAMICA");
        return connect.executaComandoPadrao(cmd);
    }
}
