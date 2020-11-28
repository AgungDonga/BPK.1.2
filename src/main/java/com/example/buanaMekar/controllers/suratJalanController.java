/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.services.SuratJalanService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Insane
 */
@Controller
public class suratJalanController {
    
    @Autowired
    SuratJalanService service;
    
    public String id1 = "", id2 = "";
    
    @RequestMapping("/suratJalan/report/{format}")
    public String generateReport(@PathVariable String format, @RequestParam(required = false, value = "id") String id, @RequestParam(required = false, value = "id2") String id22, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
        return service.exportReport2(format, id1, id2, response);
    }

//    @RequestMapping(value = "/suratJalan/save", method = RequestMethod.POST)
//    public String saveJenisProduk(Model model,SuratJalan suratJalan) {
//        List<SuratJalan> listJenisProduks = service.listAll();
//        model.addAttribute("listSuratJalans", listJenisProduks);
//        suratJalan.setId(suratJalan.getId());
//        suratJalan.setIsTax(suratJalan.getIsTax());
//        System.out.println("1 "+suratJalan.getId());
//        System.out.println("2 "+suratJalan.getIsTax());
//        service.save(suratJalan);        
//        return "redirect:/suratJalan";
//    }
    
    @RequestMapping("/suratJalan")
    public String viewSuratJalanPage(Model model) {
        List<SuratJalan> listSuratJalans = service.listAllSuratJalan();
        model.addAttribute("listSuratJalans", listSuratJalans);
        return "listSuratJalan";
    }
    
    @RequestMapping("/detailSuratJalan")
    public String viewDetailSuratJalanPage(Model model, HttpServletRequest request) {
        List<SuratJalan> listSuratJalans = service.listDetailSuratJalan(request.getParameter("id").replaceAll("%20", " "), request.getParameter("id2").replaceAll("%20", " "));
        id1 = request.getParameter("id").replaceAll("%20", " ");
        id2 = request.getParameter("id2").replaceAll("%20", " ");
        model.addAttribute("listSuratJalans", listSuratJalans);
        return "listDetailSuratJalan";
    }
    
    @RequestMapping("/suratJalan/delete/{id}")
    public String deleteSuratJalan(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return "redirect:/suratJalan";
    }
    
}
