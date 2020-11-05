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
@Table(name = "toko")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Toko.findAll", query = "SELECT t FROM Toko t")
    , @NamedQuery(name = "Toko.findById", query = "SELECT t FROM Toko t WHERE t.id = :id")
    , @NamedQuery(name = "Toko.findByNamaToko", query = "SELECT t FROM Toko t WHERE t.namaToko = :namaToko")
    , @NamedQuery(name = "Toko.findByAlamatToko", query = "SELECT t FROM Toko t WHERE t.alamatToko = :alamatToko")
    , @NamedQuery(name = "Toko.findByNoNpwp", query = "SELECT t FROM Toko t WHERE t.noNpwp = :noNpwp")
    , @NamedQuery(name = "Toko.findByNoHp", query = "SELECT t FROM Toko t WHERE t.noHp = :noHp")})
public class Toko implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nama_toko")
    private String namaToko;
    @Size(max = 50)
    @Column(name = "alamat_toko")
    private String alamatToko;
    @Size(max = 50)
    @Column(name = "no_npwp")
    private String noNpwp;
    @Size(max = 50)
    @Column(name = "no_hp")
    private String noHp;
    @OneToMany(mappedBy = "toko", fetch = FetchType.LAZY)
    private List<Orderan> orderanList;

    public Toko() {
    }

    public Toko(Integer id) {
        this.id = id;
    }

    public Toko(Integer id, String namaToko) {
        this.id = id;
        this.namaToko = namaToko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(String alamatToko) {
        this.alamatToko = alamatToko;
    }

    public String getNoNpwp() {
        return noNpwp;
    }

    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
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
        if (!(object instanceof Toko)) {
            return false;
        }
        Toko other = (Toko) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Toko[ id=" + id + " ]";
    }
    
}
