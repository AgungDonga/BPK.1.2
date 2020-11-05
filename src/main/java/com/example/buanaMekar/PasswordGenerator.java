/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Insane
 */
public class PasswordGenerator {
    public static void main(String[] args) {
        Date today = new Date();
     
        //If you print Date, you will get un formatted output
        System.out.println("Today is : " + today);
     
        //formatting date in Java using SimpleDateFormat
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy");
        String date = DATE_FORMAT.format(today);
        System.out.println("Today in yyyy format : " + date);

        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "agung";
        String encodePassword = encoder.encode(rawPassword);
        
        System.out.println(encodePassword);
    }
    
}
