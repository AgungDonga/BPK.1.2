/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;

import com.example.buanaMekar.entities.Toko;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface TokoRepository extends CrudRepository<Toko, Integer>{
   
    List<Toko> findAll();
    
}
