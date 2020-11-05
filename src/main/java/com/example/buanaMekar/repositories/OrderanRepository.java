/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;

import com.example.buanaMekar.entities.Orderan;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface OrderanRepository extends CrudRepository<Orderan, Integer>{
    
    List<Orderan> findAll();
    
    @Query("SELECT o FROM Orderan o WHERE o.status = 0")
    List<Orderan> getAllOrder();
    
}
