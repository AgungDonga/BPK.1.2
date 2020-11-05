/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Toko;
import com.example.buanaMekar.repositories.TokoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Insane
 */
@Service
public class TokoService {
    
    @Autowired
    TokoRepository repo;

    public List<Toko> listAll() {
        return repo.findAll();

    }

    public void save(Toko toko) {
        repo.save(toko);

    }

    public Toko get(Integer id) {
        return repo.findById(id).get();

    }

    public void delete(Integer id) {
        repo.deleteById(id);

    }
}
