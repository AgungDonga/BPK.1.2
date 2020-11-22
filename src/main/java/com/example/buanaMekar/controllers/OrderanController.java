/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.entities.Produk;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.services.OrderanService;
import com.example.buanaMekar.services.ProdukService;
import com.example.buanaMekar.services.SuratJalanService;
import com.example.buanaMekar.services.TokoService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Insane
 */
@Controller
public class OrderanController {

    @Autowired
    OrderanService service;

    @Autowired
    TokoService tokoService;

    @Autowired
    SuratJalanService suratJalanService;

    @Autowired
    ProdukService produkService;

    @RequestMapping("/orderan/createOrderan")
    public String createOrderan() {
        return "createOrderan";
    }

    @RequestMapping("/orderan")
    public String viewOrderanPage(Model model) {
        List<Orderan> listOrderans = service.getAllOrder();
        model.addAttribute("produks", service.getAllProduk());
        model.addAttribute("tokos", service.getAllToko());
        model.addAttribute("listOrderans", listOrderans);
        return "listOrderan";
    }

    @RequestMapping("/generateSuratJalan")
    public String cetakSuratJalan(Model model) {
        List<Orderan> listOrderans = service.listAll();
        Orderan orderan = new Orderan();

        for (int i = 0; i < listOrderans.size(); i++) {
            if (listOrderans.get(i).getStatus().equals("0")) {
                //initialisasi
                System.out.println("size = " + listOrderans.size());
                Produk produk = new Produk();
                SuratJalan sj = new SuratJalan();

                System.out.println("");
                //validasi stok

                //mengalirkan data penyusaian ditabel order
                orderan.setId(listOrderans.get(i).getId());
                orderan.setStatus("1");
                orderan.setProduk(listOrderans.get(i).getProduk());
                orderan.setQuantity(listOrderans.get(i).getQuantity());
                orderan.setToko(listOrderans.get(i).getToko());
                orderan.setTotalHarga(listOrderans.get(i).getTotalHarga());

                int stok = Integer.parseInt(orderan.getProduk().getStok()) - Integer.parseInt(orderan.getQuantity());
                if (stok < 0) {
                    return "errorStok";
                } else {
                    service.save(orderan);
                    //insert ke tabel surat jalan
                    sj.setOrderan(orderan);
                    sj.setStatus("0");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    System.out.println("1 ="+sj.getIsTax());
                    sj.setTglKirim(formatter.format(date));
                    sj.setTglTerima(formatter.format(date));
                    sj.setIsTax("1");
                    suratJalanService.save(sj);
                    //update stok

                    produk.setId(orderan.getProduk().getId());
                    produk.setMerkProduk(orderan.getProduk().getMerkProduk());
                    produk.setHarga(orderan.getProduk().getHarga());
                    produk.setJenisProduk(orderan.getProduk().getJenisProduk());
                    produk.setCatatan(orderan.getProduk().getCatatan());

                    produk.setStok(String.valueOf(stok));
                    produkService.save(produk);
                }
            }
        }
        return "listProduk";

    }

    @RequestMapping("/orderan/new")
    public String showNewOrderanForm(Model model) {
        Orderan orderan = new Orderan();
        model.addAttribute("produk", service.getAllProduk());
        model.addAttribute("toko", service.getAllToko());
        model.addAttribute("orderan", orderan);
        return "createOrderan";
    }

    @RequestMapping(value = "/orderan/save", method = RequestMethod.POST)
    public String saveOrderan(@ModelAttribute("orderan") Orderan orderan) {
        orderan.setStatus("0"); // set default Status to = 0
        int getQuantity = Integer.valueOf(orderan.getQuantity());
        int getHarga = Integer.valueOf(orderan.getProduk().getHarga());
        int total = getQuantity * getHarga;
        orderan.setTotalHarga(String.valueOf(total));
        Calendar cal3 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        orderan.setTanggalOrder(sdf.format(cal3.getTime()));
        service.save(orderan);
        return "redirect:/orderan";
    }

    @RequestMapping("/orderan/edit/{id}")
    public ModelAndView showEditOrderanForm(@PathVariable(name = "id") Integer id, Model model) {
        ModelAndView mav = new ModelAndView("editOrderan");
        Orderan orderan = service.get(id);
        model.addAttribute("produk", service.getAllProduk());
        model.addAttribute("toko", service.getAllToko());
        mav.addObject("orderan", orderan);
        return mav;
    }

    @RequestMapping("/orderan/delete/{id}")
    public String deleteOrderan(@PathVariable(name = "id") Integer id) {
        service.delete(id);

        return "redirect:/orderan";
    }

}
