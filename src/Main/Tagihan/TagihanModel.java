/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Tagihan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class TagihanModel {

    private Connection conn = null;

    private static final String DB_URL = "jdbc:mysql://localhost/penagihan";
    private static final String DB_USER = "root";
    private static final String DB_PASS = ""; // Password sql saya

    private String uNoSurat;
    private String tTanggal_Surat;
    private String jJenis_Tagihan;
    private String kKategori_Tagihan;
    private String sStatus;
    private String nNama_Tagihan;
    private String qJumlah_Tagihan;

    private void openConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection Success!");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    String[][] setTabelTagihan(int row, int col) {
        String id_tagihan;
        String no_surat;
        String tanggal_surat;
        String jenis_tagihan;
        String kategori_tagihan;
        String status;
        String nama_tagihan;
        String jumlah_tagihan;

        String query = "SELECT * FROM `tagihan`";
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
                datas[Row][0] = rs.getString("id_tagihan");
                datas[Row][1] = rs.getString("no_surat");
                datas[Row][2] = rs.getString("tanggal_surat");
                datas[Row][3] = rs.getString("jenis_tagihan");
                datas[Row][4] = rs.getString("kategori_tagihan");
                datas[Row][5] = rs.getString("status");
                datas[Row][6] = rs.getString("nama_tagihan");
                datas[Row][7] = rs.getString("jumlah_tagihan");
                Row++;

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return datas;
    }

    public boolean store(boolean isNew, String noSurat, Date tanggalSurat, String jenisTagihan, String kategoriTagihan, String status, String namaTagihan, String jumlahTagihan, int id) {
        String create = "INSERT INTO `tagihan` (no_surat, tanggal_surat, jenis_tagihan, kategori_tagihan, status, nama_tagihan, jumlah_tagihan) values(?,?,?,?,?,?,?)";
        String update = "UPDATE `tagihan` SET no_surat=?, tanggal_surat=?, jenis_tagihan=?, kategori_tagihan=?, status=?, nama_tagihan=?, jumlah_tagihan=? WHERE id_tagihan='" + id + "'";

        openConnection();

        try {
            PreparedStatement st;

            if (isNew) {
                st = conn.prepareStatement(create);
                st.setString(1, noSurat);
                st.setDate(2, tanggalSurat);
                st.setString(3, jenisTagihan);
                st.setString(4, kategoriTagihan);
                st.setString(5, status);
                st.setString(6, namaTagihan);
                st.setString(7, jumlahTagihan);
            } else {
                st = conn.prepareStatement(update);
                st.setString(1, noSurat);
                st.setDate(2, tanggalSurat);
                st.setString(3, jenisTagihan);
                st.setString(4, kategoriTagihan);
                st.setString(5, status);
                st.setString(6, namaTagihan);
                st.setString(7, jumlahTagihan);
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
