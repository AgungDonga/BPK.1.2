/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.JenisProduk;
import com.example.buanaMekar.services.JenisProdukService;
import java.io.FileNotFoundException;
import java.util.List;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRException;
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
public class jenisProdukController {

    @Autowired
    private JenisProdukService service;
    
    @RequestMapping("/sj/report/{format}")
    public String generateReport(@PathVariable String format)throws FileNotFoundException, JRException{
        return service.exportReport2(format);
    }

    @RequestMapping("/jenisProduk/createJenisProduk")
    public String createJenisProduk() {
        return "createJenisProduk";
    }

    @RequestMapping("/jenisProduk")
    public String viewJenisProdukPage(Model model) {
        List<JenisProduk> listJenisProduks = service.listAll();
        model.addAttribute("listJenisProduks", listJenisProduks);
        return "listJenisProduk";

    }

    @RequestMapping("/jenisProduk/new")
    public String showNewJenisProdukForm(Model model) {
        JenisProduk jenisProduk = new JenisProduk();
        model.addAttribute("jenisProduk", jenisProduk);
        return "createJenisProduk";
    }

    @RequestMapping(value = "/jenisProduk/save", method = RequestMethod.POST)
    public String saveJenisProduk(@Valid @ModelAttribute("jenisproduk") JenisProduk jenisProduk,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<FieldError> err = bindingResult.getFieldErrors();
            for (FieldError e : err) {
                System.out.println("Error on object ---> " + e.getObjectName() + " on field ---> " + e.getField() + ". Message ---> " + e.getDefaultMessage());
            }
            return "redirect:/jenisProduk";
        } else {
            service.save(jenisProduk);
        }

        return "redirect:/jenisProduk";
    }

    @RequestMapping("/jenisProduk/edit/{id}")
    public ModelAndView showEditJenisProdukForm(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("editJenisProduk");
        JenisProduk jenisProduk = service.get(id);
        mav.addObject("jenisProduk", jenisProduk);
        return mav;
    }

    @RequestMapping("/jenisProduk/delete/{id}")
    public String deleteJenisProduk(@PathVariable(name = "id") Integer id) {
        service.delete(id);

        return "redirect:/jenisProduk";
    }

}
