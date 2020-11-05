/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BWP
 */
@Entity
@Table(name = "produk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produk.findAll", query = "SELECT p FROM Produk p")
    , @NamedQuery(name = "Produk.findById", query = "SELECT p FROM Produk p WHERE p.id = :id")
    , @NamedQuery(name = "Produk.findByMerkProduk", query = "SELECT p FROM Produk p WHERE p.merkProduk = :merkProduk")
    , @NamedQuery(name = "Produk.findByHarga", query = "SELECT p FROM Produk p WHERE p.harga = :harga")
    , @NamedQuery(name = "Produk.findByStok", query = "SELECT p FROM Produk p WHERE p.stok = :stok")
    , @NamedQuery(name = "Produk.findByCatatan", query = "SELECT p FROM Produk p WHERE p.catatan = :catatan")})
public class Produk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Size(max = 50)
    @Column(name = "merk_produk")
    private String merkProduk;
    @Size(max = 100)
    @Column(name = "harga")
    private String harga;
    @Size(max = 100)
    @Column(name = "stok")
    private String stok;
    @Size(max = 50)
    @Column(name = "catatan")
    private String catatan;
    @JoinColumn(name = "jenis_produk", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private JenisProduk jenisProduk;
    @OneToMany(mappedBy = "produk", fetch = FetchType.LAZY)
    private List<Orderan> orderanList;

    public Produk() {
    }

    public Produk(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerkProduk() {
        return merkProduk;
    }

    public void setMerkProduk(String merkProduk) {
        this.merkProduk = merkProduk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public JenisProduk getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(JenisProduk jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    @XmlTransient
    public List<Orderan> getOrderanList() {
        return orderanList;
    }

    public void setOrderanList(List<Orderan> orderanList) {
        this.orderanList = orderanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produk)) {
            return false;
        }
        Produk other = (Produk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Produk[ id=" + id + " ]";
    }
    
}
