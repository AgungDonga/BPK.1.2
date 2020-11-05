/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.entities;

/**
* Bean
*
* @author Ankur Gupta
*/
public class Beannya {

    private String namaToko = "";
    private String tanggalKirim = "";
    private String kodeBarang = "";
    private String namaBarang = "";
    private String quantity = "";
    private String hargaSatuan = "";
    private String subHarga = "";
    private String ppn = "";
    private String totalHarga = "";

    public Beannya(String namaToko, String tanggalKirim, String kodeBarang, String namaBarang, String quantity, String hargaSatuan, String subHarga, String ppn, String totalHarga) {
        this.namaToko = namaToko;
        this.tanggalKirim = tanggalKirim;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.quantity = quantity;
        this.hargaSatuan = hargaSatuan;
        this.subHarga = subHarga;
        this.ppn = ppn;
        this.totalHarga = totalHarga;
    }
    

    public String getNamaToko() {
        return namaToko;
    }

    public String getTanggalKirim() {
        return tanggalKirim;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getHargaSatuan() {
        return hargaSatuan;
    }

    public String getSubHarga() {
        return subHarga;
    }

    public String getPpn() {
        return ppn;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public void setTanggalKirim(String tanggalKirim) {
        this.tanggalKirim = tanggalKirim;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setHargaSatuan(String hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public void setSubHarga(String subHarga) {
        this.subHarga = subHarga;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }
    
    

}