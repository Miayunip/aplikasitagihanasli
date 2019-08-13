/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Tagihan;

import java.sql.Connection;

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
    private String jJenis_Tagihan ;
    private String kKategori_Tagihan;
    private String sStatus;
    private String nNama_Tagihan;
    private String qJumlah_Tagihan;
    
    
}
