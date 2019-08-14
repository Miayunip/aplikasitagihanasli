/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Pembayaran;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aziz Fikri
 */
public class PembayaranModel {
    String sql;
    private Connection conn = null;
    
    private static final String DB_URL = "jdbc:mysql://localhost/penagihan";
    private static final String DB_USER = "root";
    private static final String DB_PASS = ""; // Password sql saya
    
    private String gNo_kwitansi;
    private String zTanggal_Pembayaran;
    private String qJumlah_bayar;
    
    private void openConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection Success!");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    String[][] setTabelPembayaran(int row, int col) {
        String id_pembayaran;
        String no_kwitansi;
        String tanggal_pembayaran;
        String jumlah_pembayaran;

        String query = "SELECT * FROM `pembayaran`";
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
                int id = rs.getInt("id_pembayaran");
                datas[Row][0] = Integer.toString(id);
                datas[Row][2] = rs.getString("no_kwitansi");
                datas[Row][3] = rs.getString("tanggal_pembayaran");
                datas[Row][4] = rs.getString("jumlah_pembayaran");
                Row++;

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        } 
        return datas;
}
    public boolean store(boolean isNew,  String noKwitansi, String tanggalPembayaran, String jumlahPembayaran, int id) {
        String create = "INSERT INTO `pembayaran` (no_kwitansi, tanggal_pembayaran, jumlah_pembayaran) values(?,?,?)";
        String update = "UPDATE `pembayaran` SET no_kwitansi=?, tanggal_pembayaran=?, jumlah_pembayaran=?, WHERE id_pembayaran='"+ id + "'";

        openConnection();
        
        try {
            PreparedStatement st;

            if (isNew) {
                st = conn.prepareStatement(create);
                st.setString(1, noKwitansi);
                st.setString(2, tanggalPembayaran);
                st.setString(3, jumlahPembayaran);
            } else {
                st = conn.prepareStatement(update);
                st.setString(1, noKwitansi);
                st.setString(2, tanggalPembayaran);
                st.setString(3, jumlahPembayaran);
            }
            st.execute();
            System.out.println("Success");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    
}

    
    

