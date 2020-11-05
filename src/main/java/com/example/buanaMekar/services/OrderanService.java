/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.entities.Produk;
import com.example.buanaMekar.entities.Toko;
import com.example.buanaMekar.repositories.OrderanRepository;
import com.example.buanaMekar.repositories.ProdukRepository;
import com.example.buanaMekar.repositories.TokoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Insane
 */
@Service
public class OrderanService {
    
    @Autowired
    OrderanRepository repo;
    
    @Autowired
    ProdukRepository ProdukRepo;
    
    @Autowired
    TokoRepository TokoRepo;
    
    public List<Orderan> listAll() {
        return repo.findAll();

    }
    
    public List<Orderan> getAllOrder(){
        return repo.getAllOrder();
    }

    public void save(Orderan orderan) {
        repo.save(orderan);

    }

    public Orderan get(Integer integer) {
        return repo.findById(integer).get();

    }

    public void delete(Integer integer) {
        repo.deleteById(integer);

    }
    
    public List<Produk> getAllProduk() {
        return ProdukRepo.findAll();
    }
    
    public List<Toko> getAllToko() {
        return TokoRepo.findAll();
    }
    
}
