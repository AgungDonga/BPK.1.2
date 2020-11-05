/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "jenis_produk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JenisProduk.findAll", query = "SELECT j FROM JenisProduk j")
    , @NamedQuery(name = "JenisProduk.findById", query = "SELECT j FROM JenisProduk j WHERE j.id = :id")
    , @NamedQuery(name = "JenisProduk.findByJenisProduk", query = "SELECT j FROM JenisProduk j WHERE j.jenisProduk = :jenisProduk")})
public class JenisProduk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "jenis_produk")
    private String jenisProduk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jenisProduk", fetch = FetchType.LAZY)
    private List<Produk> produkList;

    public JenisProduk() {
    }

    public JenisProduk(Integer id) {
        this.id = id;
    }

    public JenisProduk(Integer id, String jenisProduk) {
        this.id = id;
        this.jenisProduk = jenisProduk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJenisProduk() {
        return jenisProduk;
    }

    public void setJenisProduk(String jenisProduk) {
        this.jenisProduk = jenisProduk;
    }

    @XmlTransient
    public List<Produk> getProdukList() {
        return produkList;
    }

    public void setProdukList(List<Produk> produkList) {
        this.produkList = produkList;
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
        if (!(object instanceof JenisProduk)) {
            return false;
        }
        JenisProduk other = (JenisProduk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.JenisProduk[ id=" + id + " ]";
    }
    
}
