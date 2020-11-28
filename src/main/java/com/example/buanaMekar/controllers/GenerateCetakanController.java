/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Produk;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.services.InvoiceService;
import com.example.buanaMekar.services.SuratJalanService;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GenerateCetakanController {
    
    @Autowired
    InvoiceService service;
    

    @RequestMapping("/generateLaporan")
    public String viewInvoicePage(Model model,@Valid @ModelAttribute("bulannya") String bulan) throws Exception{
        if(bulan.isEmpty()){
            System.out.println("A");
        }else{
            String bulann = bulan.substring(5, 7);
            System.out.println(bulann);
            String tahunn = bulan.substring(0, 4);
            System.out.println(tahunn);
            
            List<Invoice> listInvoices = service.findBulanan("%"+bulann+"/"+tahunn+"%");
            model.addAttribute("listInvoices", listInvoices);
            long totalHarga = 0;
//            model.addAttribute("totalHarganya", service.totalBulanan("%"+bulann+"/"+tahunn+"%"));
            model.addAttribute("totalHarganya", service.totalBulanan("%"+bulann+"/"+tahunn+"%"));
//            model.addAttribute("totalHarganya", "");
            model.addAttribute("totalOmzetnya", service.findBulananOmset("%"+bulann+"/"+tahunn+"%"));
            
            
            return "listInvoiceBulanan";
//            sout 
//            SimpleDateFormat DateFor = new SimpleDateFormat("dd/mm/yyyy");
//            Date date = DateFor.parse(bulan.replaceAll("-", "/"));
//            System.out.println("Date : "+date);
        }
        
        return "generateLaporanBulanan";
        
        
    }
    
}
