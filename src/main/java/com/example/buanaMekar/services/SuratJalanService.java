/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Beannya;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.repositories.OrderanRepository;
import com.example.buanaMekar.repositories.SuratJalanRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author Insane
 */
@Service
public class SuratJalanService {

    @Autowired
    SuratJalanRepository repo;

    @Autowired
    OrderanRepository orderanRepo;

    Connection conn;
//baru gan

    public String exportReport2(String reportFormat, String id, String id2, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
        try {
            String url1 = "jdbc:mysql://localhost/buana_mekar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "";
// 
            conn = DriverManager.getConnection(url1, user, password);
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        //end

        String path = "D:\\INDEX2";
        File file = ResourceUtils.getFile("classpath:report1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param1", id);
        parameters.put("param2", id2);
        System.out.println("id=" + id);
        System.out.println("id2=" + id2);
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
        String pdfFileName = "D:/INDEX2/suratJalan.pdf";
        response.setContentType("application/x-download");
        response.addHeader("Content-disposition", "attachment; filename=suratJalan.pdf");
        OutputStream out = response.getOutputStream();
        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jp, path + "\\suratJalan.pdf");
            JasperExportManager.exportReportToPdfStream(jp, out);//export PDF directly
        }
        return "succes";
    }

//    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "D:\\INDEX2";
//        File file = ResourceUtils.getFile("classpath:report2.jrxml");
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("param1", "ALIONG COMPANY");
//        parameters.put("param2", "18/08/2020 23:04:55");
//        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
//
////        List<Beannya> collection = repo.findAllPesanan("ALIONG COMPANY", "18/08/2020 23:04:55");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(collection);
//        parameters.put("created", "Developer");
//        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jp, path + "\\suratJalan.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jp, path + "\\99999999.pdf");
//        }
//        JasperViewer.viewReport(jp);
//        System.out.println("BERHASIL");
//        return "success : " + path;
//    }

//    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "D:\\INDEX";
//
//        File file = ResourceUtils.getFile("classpath:suratJalan.jrxml");
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("param1", "ALIONG COMPANY");
//        parameters.put("param2", "18/08/2020 23:04:55");
//        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
//
//        JasperDesign jd = JRXmlLoader.load(file.getAbsolutePath());
//        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
//        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jp, path + "\\suratJalan.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jp, path + "\\suratJalan16.pdf");
//        }
//        return "success : " + path;
//        //kurang asem
//    }
    public List<SuratJalan> listAll() {
        return repo.findAll();
    }

    public List<SuratJalan> listAllSuratJalan() {
        return repo.findAllSuratJalan();
    }

    public List<SuratJalan> listDetailSuratJalan(String key1, String key2) {
        return repo.listDetailSuratJalan(key1, key2);
    }

    public List<SuratJalan> listDetailInvoice(String key1, String key2) {
        return repo.listDetailInvoice(key1, key2);
    }

    public List<SuratJalan> listDetailPenagihan(String key1, String key2) {
        return repo.listDetailPenagihan(key1, key2);
    }

    public void save(SuratJalan sj) {
        repo.save(sj);

    }

    public SuratJalan get(Integer integer) {
        return repo.findById(integer).get();

    }

    public void delete(Integer integer) {
        repo.deleteById(integer);

    }

//    public List<Orderan> getAllOrderan() {
//        return orderanRepo.findAll();
//    }
}
