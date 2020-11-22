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
@Table(name = "surat_jalan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuratJalan.findAll", query = "SELECT s FROM SuratJalan s")
    , @NamedQuery(name = "SuratJalan.findById", query = "SELECT s FROM SuratJalan s WHERE s.id = :id")
    , @NamedQuery(name = "SuratJalan.findByTglKirim", query = "SELECT s FROM SuratJalan s WHERE s.tglKirim = :tglKirim")
    , @NamedQuery(name = "SuratJalan.findByTglTerima", query = "SELECT s FROM SuratJalan s WHERE s.tglTerima = :tglTerima")
    , @NamedQuery(name = "SuratJalan.findByStatus", query = "SELECT s FROM SuratJalan s WHERE s.status = :status")
    , @NamedQuery(name = "SuratJalan.findByIsTax", query = "SELECT s FROM SuratJalan s WHERE s.isTax = :isTax")})
public class SuratJalan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "tgl_kirim")
    private String tglKirim;
    @Size(max = 100)
    @Column(name = "tgl_terima")
    private String tglTerima;
    @Size(max = 100)
    @Column(name = "status")
    private String status;
    @Size(max = 100)
    @Column(name = "is_tax")
    private String isTax;
    @OneToMany(mappedBy = "suratJalan", fetch = FetchType.LAZY)
    private List<Invoice> invoiceList;
    @JoinColumn(name = "orderan", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Orderan orderan;

    public SuratJalan() {
    }

    public SuratJalan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTglKirim() {
        return tglKirim;
    }

    public void setTglKirim(String tglKirim) {
        this.tglKirim = tglKirim;
    }

    public String getTglTerima() {
        return tglTerima;
    }

    public void setTglTerima(String tglTerima) {
        this.tglTerima = tglTerima;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsTax() {
        return isTax;
    }

    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public Orderan getOrderan() {
        return orderan;
    }

    public void setOrderan(Orderan orderan) {
        this.orderan = orderan;
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
        if (!(object instanceof SuratJalan)) {
            return false;
        }
        SuratJalan other = (SuratJalan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.SuratJalan[ id=" + id + " ]";
    }
    
}
