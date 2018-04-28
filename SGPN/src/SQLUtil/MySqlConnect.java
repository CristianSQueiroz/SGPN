/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLUtil;

import HashMap.CHashMap;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class MySqlConnect {

    Connection conn;

    String url = "cristianweb.com.br";
    String porta = "3306";
    String db = "crist609_SGOS";
    String driver = "com.mysql.jdbc.Driver";

    String user = "crist609_connect";
    String pass = "connect";

    String urlComposto = "jdbc:mysql://" + url + ":" + porta + "/" + db;

    private static MySqlConnect connect;

    public static MySqlConnect getInstance() {
        if (connect == null) {
            connect = new MySqlConnect();
        }
        return connect;
    }

    public void open() {
        try {
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(urlComposto, user, pass);

        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            if (e.getMessage().contains("")) {

            }
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println((e.getMessage()));
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public ArrayList<CHashMap> executaConsultaPadrao(String cmd) {
        ArrayList<CHashMap> retorno = new ArrayList<CHashMap>();
        PreparedStatement stmt = null;
        try {
            open();
            stmt = (PreparedStatement) getConn().prepareStatement(cmd);
            System.out.println(cmd);
            ResultSet rs = stmt.executeQuery();
            int colunas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                CHashMap row = new CHashMap();
                for (int x = 0; x < colunas; x++) {
                    row.put(rs.getMetaData().getColumnName(x + 1), rs.getObject(x + 1));
                }
                retorno.add(row);
            }
            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            close();
        }
        return retorno;
    }
    
    public ArrayList<CHashMap> executaConsultaPadraoLote(String cmd) {
        ArrayList<CHashMap> retorno = new ArrayList<CHashMap>();
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) getConn().prepareStatement(cmd);
            System.out.println(cmd);
            ResultSet rs = stmt.executeQuery();
            int colunas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                CHashMap row = new CHashMap();
                for (int x = 0; x < colunas; x++) {
                    row.put(rs.getMetaData().getColumnName(x + 1), rs.getObject(x + 1));
                }
                retorno.add(row);
            }
            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        return retorno;
    }

    public ArrayList<CHashMap> executaConsultaPadrao(String cmd, boolean console) {
        ArrayList<CHashMap> retorno = new ArrayList<CHashMap>();
        PreparedStatement stmt = null;
        try {
            open();
            stmt = (PreparedStatement) getConn().prepareStatement(cmd);
            if (console) {
                System.out.println(cmd);
            }
            ResultSet rs = stmt.executeQuery();
            int colunas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                CHashMap row = new CHashMap();
                for (int x = 0; x < colunas; x++) {
                    row.put(rs.getMetaData().getColumnName(x + 1), rs.getObject(x + 1));
                }
                retorno.add(row);
            }
            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            close();
        }
        return retorno;
    }

    public boolean executaComandoPadrao(String cmd) {
        ArrayList<CHashMap> retorno = new ArrayList<CHashMap>();
        CHashMap row = new CHashMap();
        PreparedStatement stmt = null;
        try {
            open();
            stmt = (PreparedStatement) getConn().prepareStatement(cmd);
            System.out.println(cmd);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            close();
        }
        return false;
    }

    public boolean executaComandoPadraoLote(String cmd) {
        ArrayList<HashMap> retorno = new ArrayList<HashMap>();
        HashMap row = new HashMap();
        PreparedStatement stmt = null;
        try {
            stmt = (PreparedStatement) getConn().prepareStatement(cmd);
            System.out.println(cmd);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        return false;
    }

    public boolean executaComandoPadrao(String cmd, boolean console) {
        if (console) {
            return executaComandoPadrao(cmd);
        }
        ArrayList<CHashMap> retorno = new ArrayList<CHashMap>();
        CHashMap row = new CHashMap();
        PreparedStatement stmt = null;
        try {
            open();
            stmt = (PreparedStatement) getConn().prepareStatement(cmd);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            close();
        }
        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static MySqlConnect getConnect() {
        return connect;
    }

    public static void setConnect(MySqlConnect connect) {
        MySqlConnect.connect = connect;
    }

}
