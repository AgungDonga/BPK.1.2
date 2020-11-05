/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Insane
 */
@RestController
public class TomcatController {
    
    @GetMapping("/hello")
    public Collection<String> sayHello(){
        return IntStream.range(0, 10)
                .mapToObj(i -> "Hello number "+i)
                .collect(Collectors.toList());
    }
    
}
