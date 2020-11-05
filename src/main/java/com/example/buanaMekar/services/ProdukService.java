/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.JenisProduk;
import com.example.buanaMekar.entities.Produk;
import com.example.buanaMekar.repositories.JenisProdukRepository;
import com.example.buanaMekar.repositories.ProdukRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Insane
 */
@Service
public class ProdukService {
    
    @Autowired
    ProdukRepository repo;
    
    @Autowired
    JenisProdukRepository JenisProdukRepo;
    
    

    public List<Produk> listAll() {
        return repo.findAll();

    }

    public void save(Produk produk) {
        repo.save(produk);

    }

    public Produk get(String id) {
        return repo.findById(id).get();

    }

    public void delete(String id) {
        repo.deleteById(id);

    }
    
    public List<JenisProduk> getAllJenisProduk() {
        return JenisProdukRepo.findAll();
    }
}
