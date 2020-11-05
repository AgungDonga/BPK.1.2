/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Users;
import com.example.buanaMekar.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
public class mainController {
    
    @Autowired
    private UserService service;
    
    @GetMapping("login")
    public String handlingLog() {
        String result;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            result = "redirect:/";
        } else {
            result = "login";
        }
        return result;
    }
    
    @RequestMapping("/createuser")
    public String createUser(){
        return "createUser";
    }
    
    @RequestMapping("/")
    public String dashboard(){
        return "main";
    }

    @RequestMapping("/user")
    public String viewHomePage(Model model){
        List<Users> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "listUser";

    }
//    
//    @RequestMapping("/login")
//    public String viewLoginPage(){
//        return "login";
//
//    }
    
    

    @RequestMapping("/user/new")
    public String showNewUserForm(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "createUser";
    }

    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user")Users user){
        service.save(user);
        return "redirect:/user";
    }
    
    @RequestMapping("/user/edit/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name = "id")Integer id){
        ModelAndView mav = new ModelAndView("editUser");
        Users user = service.get(id);
        mav.addObject("user",user);
        return mav;
    }
    
    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id")Integer id){
        service.delete(id);
        
        return "redirect:/user";
    }
}
