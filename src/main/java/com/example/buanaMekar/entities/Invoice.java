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
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
    , @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id")
    , @NamedQuery(name = "Invoice.findByInvoice", query = "SELECT i FROM Invoice i WHERE i.invoice = :invoice")
    , @NamedQuery(name = "Invoice.findByPpn", query = "SELECT i FROM Invoice i WHERE i.ppn = :ppn")
    , @NamedQuery(name = "Invoice.findByTotalHarga", query = "SELECT i FROM Invoice i WHERE i.totalHarga = :totalHarga")
    , @NamedQuery(name = "Invoice.findByTglJatuhTempo", query = "SELECT i FROM Invoice i WHERE i.tglJatuhTempo = :tglJatuhTempo")
    , @NamedQuery(name = "Invoice.findByStatus", query = "SELECT i FROM Invoice i WHERE i.status = :status")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "invoice")
    private String invoice;
    @Size(max = 20)
    @Column(name = "ppn")
    private String ppn;
    @Size(max = 20)
    @Column(name = "total_harga")
    private String totalHarga;
    @Size(max = 20)
    @Column(name = "tgl_jatuh_tempo")
    private String tglJatuhTempo;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "surat_jalan", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SuratJalan suratJalan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInvoice", fetch = FetchType.LAZY)
    private List<Penagihan> penagihanList;

    public Invoice() {
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(String tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SuratJalan getSuratJalan() {
        return suratJalan;
    }

    public void setSuratJalan(SuratJalan suratJalan) {
        this.suratJalan = suratJalan;
    }

    @XmlTransient
    public List<Penagihan> getPenagihanList() {
        return penagihanList;
    }

    public void setPenagihanList(List<Penagihan> penagihanList) {
        this.penagihanList = penagihanList;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.buanaMekar.entities.Invoice[ id=" + id + " ]";
    }
    
}
