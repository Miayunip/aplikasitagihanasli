/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Pembayaran;

import java.sql.Connection;

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
    
    
}
