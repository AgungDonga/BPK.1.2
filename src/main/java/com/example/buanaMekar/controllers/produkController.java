/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Produk;
import com.example.buanaMekar.services.ProdukService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
public class produkController {

    @Autowired
    private ProdukService service;

    @RequestMapping("/produk/createProduk")
    public String createProduk() {
        return "createProduk";
    }

    @RequestMapping("/produk")
    public String viewJenisProdukPage(Model model) {
        List<Produk> listProduks = service.listAll();
        model.addAttribute("jenisProduks", service.getAllJenisProduk());
        model.addAttribute("listProduks", listProduks);
        return "listProduk";

    }

    @RequestMapping("/produk/new")
    public String showNewProdukForm(Model model) {
        Produk produk = new Produk();
        model.addAttribute("jenisProduk", service.getAllJenisProduk());
        model.addAttribute("produk", produk);
        return "createProduk";
    }

    @RequestMapping(value = "/produk/save", method = RequestMethod.POST)
    public String saveProduk(@Valid @ModelAttribute("produk") Produk produk,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> err = bindingResult.getFieldErrors();
            for (FieldError e : err) {
                System.out.println("Error on object ---> " + e.getObjectName() 
                        + " on field ---> " + e.getField() + ". Message ---> " + e.getDefaultMessage());
            }
            return "redirect:/produk";
        } else {
            produk.setId(produk.getId().replaceAll(" ", "_"));
            service.save(produk);
        }
        return "redirect:/produk";
    }

    @RequestMapping("/produk/edit/{id}")
    public ModelAndView showEditProdukForm(@PathVariable(name = "id") String id, Model model) {
        ModelAndView mav = new ModelAndView("editProduk");
        Produk produk = service.get(id);
        model.addAttribute("jenisProduk", service.getAllJenisProduk());
        mav.addObject("produk", produk);
        return mav;
    }

    @RequestMapping("/produk/delete/{id}")
    public String deleteProduk(@PathVariable(name = "id") String id) {
        service.delete(id);

        return "redirect:/produk";
    }

}
