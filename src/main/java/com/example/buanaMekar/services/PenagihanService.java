/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Penagihan;
import com.example.buanaMekar.repositories.InvoiceRepository;
import com.example.buanaMekar.repositories.PenagihanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Insane
 */
@Service
public class PenagihanService {

    @Autowired
    PenagihanRepository repo;
    
    @Autowired
    InvoiceRepository invoiceRepo;

    public List<Penagihan> listAll() {
        return repo.findAll();

    }
    
    public void save(Penagihan penagihan) {
        repo.save(penagihan);

    }

    public Penagihan get(Integer id) {
        return repo.findById(id).get();

    }

    public void delete(Integer id) {
        repo.deleteById(id);

    }
    
    public List<Invoice> getAllInvoice() {
        return invoiceRepo.findAll();
    }
   
}
