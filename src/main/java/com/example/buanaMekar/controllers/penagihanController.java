/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Penagihan;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.services.PenagihanService;
import com.example.buanaMekar.services.SuratJalanService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Insane
 */
@Controller
public class penagihanController {

    public String id1 = "", id2 = "";

    @Autowired
    private PenagihanService service;

    @Autowired
    SuratJalanService serviceSuratJalan;

    
    @RequestMapping("/penagihan")
    public String viewJenisPenagihanPage(Model model) {
        List<Penagihan> listPenagihans = service.listAll();
        model.addAttribute("listPenagihans", listPenagihans);
        return "listPenagihan";
    }

    @RequestMapping("/detailPenagihan")
    public String viewDetailPenagihan(Model model, HttpServletRequest request) {
        List<SuratJalan> listSuratJalans = serviceSuratJalan.listDetailPenagihan(request.getParameter("id").replaceAll("%20", " "), request.getParameter("id2").replaceAll("%20", " "));
        id1 = request.getParameter("id").replaceAll("%20", " ");
        id2 = request.getParameter("id2").replaceAll("%20", " ");
        model.addAttribute("listSuratJalans", listSuratJalans);
        return "listDetailPenagihan";
    }
    
    @RequestMapping("/penagihan/delete/{id}")
    public String deleteInvoice(@PathVariable(name = "id") String id) {
        service.delete(Integer.parseInt(id));
        return "redirect:/penagihan";
    }

}
