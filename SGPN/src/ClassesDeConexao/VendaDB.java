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

    public boolean cadastrarFicha(FichaCadastro fc) {
        String osValue = "SELECT Auto_increment FROM information_schema.tables WHERE table_name='TB_OS'";
        retorno = connect.executaConsultaPadrao(osValue);
        java.sql.Timestamp dateAgendamento = new java.sql.Timestamp(fc.getDtAgendamento());
        java.sql.Timestamp dateVenda = new java.sql.Timestamp(fc.getDtVenda());
        java.sql.Timestamp dateNascimento = new java.sql.Timestamp(fc.getDtNascimento());

        cmd = "INSERT INTO TB_OS (NR_SEQUENCIA,"
                + "DT_VENDA,"
                + "DS_SUPERVISORA,"
                + "DS_VENDEDORA,"
                + "DT_AGENDAMENTO,"
                + "DS_PROTOCOLO1,"
                + "DS_PROTOCOLO2,"
                + "NR_STATUS,"
                + "DS_PLANO,"
                + "NM_CLIENTE,"
                + "CPF,"
                + "RG,"
                + "NR_ORGAO,"
                + "NR_EXPEDITOR,"
                + "DT_NASCIMENTO,"
                + "NM_MAE,"
                + "DS_EMAIL,"
                + "TEL_CONTATO1,"
                + "NM_CONTATO1,"
                + "TEL_CONTATO2,"
                + "NM_CONTATO2,"
                + "TEL_CONTATO3,"
                + "NM_CONTATO3,"
                + "DS_ENDERECO,"
                + "NR_ENDERECO,"
                + "DS_BAIRRO,"
                + "DS_COMPLEMENTO,"
                + "DS_ESTADO,"
                + "DS_CIDADE,"
                + "DS_CEP,"
                + "DS_PONTO_REFERENCIA,"
                + "DS_VENDEDOR) values ("
                + retorno.get(0).getValorAsInt("AUTO_INCREMENT") + " , "
                /*+ "("+osValue+"),"*/
                + utilsql.aplicarApostofo(dateVenda) + " , "
                + utilsql.aplicarApostofo(fc.getSupervisora()) + " , "
                + utilsql.aplicarApostofo(fc.getVendedora()) + " , "
                + utilsql.aplicarApostofo(dateAgendamento) + " , "
                + utilsql.aplicarApostofo(fc.getProtocolo1()) + " , "
                + utilsql.aplicarApostofo(fc.getProtocolo2()) + " , "
                + utilsql.aplicarApostofo(fc.getNrStatus()) + " , "
                + utilsql.aplicarApostofo(fc.getPlano()) + " , "
                + utilsql.aplicarApostofo(fc.getNmCliente()) + " , "
                + utilsql.aplicarApostofo(fc.getCPF()) + " , "
                + utilsql.aplicarApostofo(fc.getRG()) + " , "
                + utilsql.aplicarApostofo(fc.getNrOrgao()) + " , "
                + utilsql.aplicarApostofo(fc.getNrExpeditor()) + " , "
                + utilsql.aplicarApostofo(dateNascimento) + " , "
                + utilsql.aplicarApostofo(fc.getNmMae()) + " , "
                + utilsql.aplicarApostofo(Utilidades.validaString(fc.getDsEmail())) + " , "
                + utilsql.aplicarApostofo(fc.getTelContato1()) + " , "
                + utilsql.aplicarApostofo(fc.getNmContato1()) + " , "
                + utilsql.aplicarApostofo(fc.getTelContato2()) + " , "
                + utilsql.aplicarApostofo(fc.getNmContato2()) + " , "
                + utilsql.aplicarApostofo(fc.getTelContato3()) + " , "
                + utilsql.aplicarApostofo(fc.getNmContato3()) + " , "
                + utilsql.aplicarApostofo(fc.getEndereco()) + " , "
                + utilsql.aplicarApostofo(fc.getNumeroEndereco()) + " , "
                + utilsql.aplicarApostofo(fc.getBairro()) + " , "
                + utilsql.aplicarApostofo(fc.getComplemento()) + " , "
                + utilsql.aplicarApostofo(fc.getEstado()) + " , "
                + utilsql.aplicarApostofo(fc.getCidade()) + " , "
                + utilsql.aplicarApostofo(fc.getCEP()) + " , "
                + utilsql.aplicarApostofo(fc.getPontoReferencia()) + " , "
                + utilsql.aplicarApostofo("CSQUEIROZ") + ")";
        return connect.executaComandoPadrao(cmd, true);
    }

    public ArrayList<CHashMap> getVendas(CHashMap atributos, String orderby) {
        cmd = utilsql.montaQuery(atributos, "TB_OS", orderby);
        return connect.executaConsultaPadrao(cmd);
    }

    public ArrayList<CHashMap> getVendas(CHashMap atributos, CHashMap restricoes, String orderby) {
        cmd = utilsql.montaQuery(atributos, restricoes, "TB_OS", orderby);
        return connect.executaConsultaPadrao(cmd);
    }

    public boolean update(CHashMap atributos, CHashMap restricoes) {
        cmd = utilsql.montaQueryUpdate(atributos, restricoes, "TB_OS");
        return connect.executaComandoPadrao(cmd);
    }
}
