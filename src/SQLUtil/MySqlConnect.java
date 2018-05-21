/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLUtil;

import HashMap.CHashMap;
import com.mysql.jdbc.PreparedStatement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MySqlConnect {

    Connection conn;
    static String driver = "com.mysql.jdbc.Driver";

    static String user = "crist609_connect";
    static String pass = "connect";

    static String urlComposto;

    private static MySqlConnect connect;

    public static MySqlConnect getInstance() {
        if (connect == null) {
            connect = new MySqlConnect();
            readTxt();
        }
        return connect;
    }

    private static void readTxt() {
        String line = null;
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(new File("src/SQLUtil/Config.txt"));
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String urlTxt = line.replace("serverip##", "");
                urlComposto = "jdbc:mysql://" + urlTxt;
                System.out.println(line);
                break;
            }
            bufferedReader.close();

        } catch (IOException ex) {
            System.out.println("Error reading file Config.txt");
        }
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
