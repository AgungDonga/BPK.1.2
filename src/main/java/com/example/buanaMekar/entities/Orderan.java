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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BWP
 */
@Entity
@Table(name = "orderan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderan.findAll", query = "SELECT o FROM Orderan o")
    , @NamedQuery(name = "Orderan.findById", query = "SELECT o FROM Orderan o WHERE o.id = :id")
    , @NamedQuery(name = "Orderan.findByTotalHarga", query = "SELECT o FROM Orderan o WHERE o.totalHarga = :totalHarga")
    , @NamedQuery(name = "Orderan.findByQuantity", query = "SELECT o FROM Orderan o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "Orderan.findByStatus", query = "SELECT o FROM Orderan o WHERE o.status = :status")
    , @NamedQuery(name = "Orderan.findByTanggalOrder", query = "SELECT o FROM Orderan o WHERE o.tanggalOrder = :tanggalOrder")})
public class Orderan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "total_harga")
    private String totalHarga;
    @Size(max = 100)
    @Column(name = "quantity")
    private String quantity;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "tanggal_order")
    private String tanggalOrder;
    @JoinColumn(name = "toko", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Toko toko;
    @JoinColumn(name = "produk", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Produk produk;
    @OneToMany(mappedBy = "orderan", fetch = FetchType.LAZY)
    private List<SuratJalan> suratJalanList;

    public Orderan() {
    }

    public Orderan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggalOrder() {
        return tanggalOrder;
    }

    public void setTanggalOrder(String tanggalOrder) {
        this.tanggalOrder = tanggalOrder;
    }

    public Toko getToko() {
        return toko;
    }

    public void setToko(Toko toko) {
        this.toko = toko;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    @XmlTransient
    public List<SuratJalan> getSuratJalanList() {
        return suratJalanList;
    }

    public void setSuratJalanList(List<SuratJalan> suratJalanList) {
        this.suratJalanList = suratJalanList;
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
        if (!(object instanceof Orderan)) {
            return false;
        }
        Orderan other = (Orderan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Orderan[ id=" + id + " ]";
    }
    
}
