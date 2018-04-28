/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDeConexao;

import Core.EReader;
import HashMap.CHashMap;
import SQLUtil.MySqlConnect;
import Util.UtilSql;
import Util.Utilidades;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author Cristian
 */
public class UsuarioDB {

    private String cmd;
    private PreparedStatement stmt;
    private MySqlConnect connect;
    public ArrayList<CHashMap> retorno;
    CHashMap singleRetorno;
    Utilidades util = new Utilidades();
    UtilSql utilsql = new UtilSql();

    public UsuarioDB() {
        connect = MySqlConnect.getInstance();
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        cmd = "SELECT COUNT(*) FROM TB_USUARIO WHERE NM_USUARIO = '" + usuario.getNmUsuario() + "'";
        retorno = connect.executaConsultaPadrao(cmd);
        if (retorno.get(0).getValorAsInt("COUNT(*)") > 0) {
            JOptionPane.showMessageDialog(null, "Já existe um usuário com esse nome");
            return false;
        }
        String osValue = "SELECT Auto_increment FROM information_schema.tables WHERE table_name='TB_USUARIO'";
        retorno = connect.executaConsultaPadrao(osValue);

        cmd = "INSERT INTO TB_USUARIO (ID_USUARIO,NM_USUARIO,DS_SENHA) values ("
                + retorno.get(0).getValorAsInt("AUTO_INCREMENT") + " , "
                + utilsql.aplicarApostofo(usuario.getNmUsuario()) + " , "
                + utilsql.aplicarApostofo(usuario.getDsSenha()) + ")";
        return connect.executaComandoPadrao(cmd, false);
    }

    public boolean loginUsuario(Usuario usuario) {
        cmd = "SELECT COUNT(*) FROM TB_USUARIO WHERE NM_USUARIO = '" + usuario.getNmUsuario() + "' AND DS_SENHA='" + usuario.getDsSenha() + "'";
        retorno = connect.executaConsultaPadrao(cmd, false);
        return (retorno.get(0).getValorAsInt("COUNT(*)") > 0);
    }

    public ArrayList<CHashMap> getUsuarios(CHashMap atributos, String orderby) {
        cmd = utilsql.montaQuery(atributos, "TB_USUARIO", orderby);
        return connect.executaConsultaPadrao(cmd);
    }

    public ArrayList<CHashMap> getUsuarios(CHashMap atributos, CHashMap restricoes, String orderby) {
        cmd = utilsql.montaQuery(atributos, restricoes, "TB_USUARIO", orderby);
        return connect.executaConsultaPadrao(cmd);
    }

    public boolean update(CHashMap atributos, CHashMap restricoes) {
        cmd = utilsql.montaQueryUpdate(atributos, restricoes, "TB_USUARIO");
        return connect.executaComandoPadrao(cmd);
    }
}
