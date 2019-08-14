/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Tertagih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TertagihModel {

    private Connection conn = null;

    private static final String DB_URL = "jdbc:mysql://localhost/penagihan";
    private static final String DB_USER = "root";
    private static final String DB_PASS = ""; // Password sql saya

    private String wNama_Tertagih;
    private String hKategori_Tertagih;
    private String eKeterangan;
    private String aStatus;
    private String yNo_telephone;
    private String jPenanggung_jawab;

    private void openConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection Success!");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    public void closeConnection() {
        try { conn.close(); } catch(SQLException ex) {}
    }

    String[][] setTabelTertagih(int row, int col) {
        String id_tertagih;
        String nama_tertagih;
        String kategori_tertagih;
        String keterangan;
        String status_tertagih;
        String no_tlp;
        String penanggung_jawab;

        String query = "SELECT * FROM `tertagih` ORDER BY id_tertagih DESC";
        String[][] datas = new String[row][col];
        int Row = 0;
        openConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (Row == 10) {
                    break;
                }
                int id = rs.getInt("id_tertagih");
                datas[Row][0] = Integer.toString(id);
                datas[Row][1] = rs.getString("nama_tertagih");
                datas[Row][2] = rs.getString("kategori_tertagih");
                datas[Row][3] = rs.getString("keterangan");
                datas[Row][4] = rs.getString("status_tertagih");
                long noTelpon = rs.getLong("no_tlp");
                datas[Row][5] = Long.toString(noTelpon);
                datas[Row][6] = rs.getString("penanggung_jawab");
                Row++;

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return datas;
    }

    public boolean store(boolean isNew, String nama, String kategori, String keterangan, String status, long noTelp, String penanggungJawab, int id) {
        String create = "INSERT INTO `tertagih` (nama_tertagih, kategori_tertagih, keterangan, status_tertagih, no_tlp, penanggung_jawab) values(?,?,?,?,?,?)";
        String update = "UPDATE `tertagih` SET nama_tertagih=?, kategori_tertagih=?, keterangan=?, status_tertagih=?, no_tlp=?, penanggung_jawab=? WHERE id_tertagih='" + id + "'";

        openConnection();

        try {
            PreparedStatement st;

            if (isNew) {
                st = conn.prepareStatement(create);
                st.setString(1, nama);
                st.setString(2, kategori);
                st.setString(3, keterangan);
                st.setString(4, status);
                st.setLong(5, noTelp);
                st.setString(6, penanggungJawab);
            } else {
                st = conn.prepareStatement(update);
                st.setString(1, nama);
                st.setString(2, kategori);
                st.setString(3, keterangan);
                st.setString(4, status);
                st.setLong(5, noTelp);
                st.setString(6, penanggungJawab);
            }

            st.execute();
            System.out.println("Success");
            
            closeConnection();
            
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    // delete method, for deeting item from database
    public boolean delete(int id) {
        String delete = "DELETE FROM `tertagih` WHERE id_tertagih='" + id + "'";
        
        openConnection();
        
        try {
            PreparedStatement st = conn.prepareStatement(delete);
            
            
            st.execute();
            closeConnection();
            return true;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            closeConnection();
            return false;
        }
    }

}

/**
 *
 * @author Aziz Fikri
 */
