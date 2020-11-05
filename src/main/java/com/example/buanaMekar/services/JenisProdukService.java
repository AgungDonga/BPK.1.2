/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.JenisProduk;
import com.example.buanaMekar.repositories.JenisProdukRepository;
import com.example.buanaMekar.repositories.SuratJalanRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Insane
 */
@Service
public class JenisProdukService {
    
    @Autowired
    JenisProdukRepository repo;
    
    @Autowired
    SuratJalanRepository sjRepo;
    //yaya
    public String exportReport2(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:\\INDEX2";
        File file = ResourceUtils.getFile("classpath:jenisProduk.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param1", "ALIONG COMPANY");
        parameters.put("param2", "18/08/2020 23:04:55");
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jp, path + "\\suratJalan.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jp, path + "\\suratJalan.pdf");
        }
        return "success : " + path;
    }

    public List<JenisProduk> listAll() {
        return repo.findAll();

    }

    public void save(JenisProduk jenisProduk) {
        repo.save(jenisProduk);

    }

    public JenisProduk get(Integer id) {
        return repo.findById(id).get();

    }

    public void delete(Integer id) {
        repo.deleteById(id);

    }
    
    public List<JenisProduk> getAll(){
        return repo.findAll();
    }
}
