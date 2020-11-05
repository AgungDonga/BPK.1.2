/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;

import com.example.buanaMekar.entities.JenisProduk;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface JenisProdukRepository extends CrudRepository<JenisProduk, Integer>{
   
    List<JenisProduk> findAll();
    
    @Query(nativeQuery = true,value = "select jp.* from orderan o, surat_jalan sj, toko t, produk p, invoice i , jenis_produk jp where o.toko = t.id AND sj.orderan = o.id AND p.id = o.produk AND i.surat_jalan = sj.id AND t.nama_toko like :namaToko AND sj.tgl_kirim like :tanggalL AND jp.id = p.jenis_produk")   // call store procedure with arguments
    List<JenisProduk> findAllPesanan(@Param("namaToko")String namaToko, @Param("tanggalL")String tanggalL);
}
