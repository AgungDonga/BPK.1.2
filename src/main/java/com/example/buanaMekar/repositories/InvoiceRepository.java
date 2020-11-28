/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;

import com.example.buanaMekar.entities.Invoice;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String>{
    
    List<Invoice> findAll();
    
    @Query("SELECT i FROM Invoice i GROUP BY i.invoice ")
    List<Invoice> getAllInvoice();
    
    @Query("SELECT i FROM Invoice i WHERE i.invoice = ?1 ")
    List<Invoice> getInvoiceById(String key1);
    
    @Query("SELECT i FROM Invoice i WHERE i.tglJatuhTempo LIKE ?1 AND i.status = 1 GROUP BY i.invoice")
    List<Invoice> findBulanan(String key1);
    
    @Query("SELECT SUM(o.totalHarga) / COUNT(o.totalHarga) FROM Orderan o WHERE o.tanggalOrder LIKE ?1")
    String findBulananOmset (String key1);
    
    @Query("SELECT SUM(i.totalHarga) / COUNT(i.totalHarga) FROM Invoice i WHERE i.tglJatuhTempo LIKE ?1 AND i.status = 1 GROUP BY i.invoice")
    String totalBulanan (String key1);
    
}
