/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Penagihan;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface PenagihanRepository extends CrudRepository<Penagihan, Integer>{
   
//    @Query("SELECT p FROM Penagihan p GROUP BY p.invoice ")
    List<Penagihan> findAll();
    
    
    
}
