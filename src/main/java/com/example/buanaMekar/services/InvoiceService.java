/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.services;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Orderan;
import com.example.buanaMekar.repositories.InvoiceRepository;
import com.example.buanaMekar.repositories.OrderanRepository;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Insane
 */
@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository repo;

    @Autowired
    OrderanRepository OrderanRepo;

    public List<Invoice> listAll() {
        return repo.findAll();

    }

    public List<Invoice> listAllInvoice() {
        return repo.getAllInvoice();

    }

    public List<Invoice> findBulanan(String key1) {
        return repo.findBulanan(key1);
    }

    public String totalBulanan(String key1) {
        return repo.totalBulanan(key1);
    }

    public void save(Invoice invoice) {
        repo.save(invoice);

    }

    public Invoice get(String string) {
        return repo.findById(string).get();

    }

    public void delete(String string) {
        repo.deleteById(string);

    }

    public List<Orderan> getAllOrderan() {
        return OrderanRepo.findAll();
    }

    Connection conn;

    public static String terbilang(BigDecimal value) {
        value = value.setScale(0, BigDecimal.ROUND_HALF_EVEN);
        String strValue = value.toString();

        int lenValue = strValue.length();
        int x = 0;
        int y = 0;
        int z;
        String bil1 = "";
        String bil2 = "";
        String urai = "";
        while (x < lenValue) {
            x = x + 1;
            int strTot = Integer.valueOf(strValue.substring(x - 1, x));
            y = y + strTot;
            z = lenValue - x + 1;
            switch (strTot) {
                case 1:
                    if (z == 1 || z == 7 || z == 10 || z == 13) {
                        bil1 = "Satu ";
                    } else if (z == 4) {
                        if (x == 1) {
                            bil1 = "Se";
                        } else {
                            bil1 = "Satu ";
                        }
                    } else if (z == 2 || z == 5 || z == 8 || z == 11 || z == 14) {
                        x = x + 1;
                        int newStrTot = Integer.valueOf(strValue.substring(x - 1, x));
                        z = lenValue - x + 1;
                        bil2 = "";
                        switch (newStrTot) {
                            case 0:
                                bil1 = "Sepuluh ";
                                break;
                            case 1:
                                bil1 = "Sebelas ";
                                break;
                            case 2:
                                bil1 = "Dua belas ";
                                break;
                            case 3:
                                bil1 = "Tiga belas ";
                                break;
                            case 4:
                                bil1 = "Empat belas ";
                                break;
                            case 5:
                                bil1 = "Lima belas ";
                                break;
                            case 6:
                                bil1 = "Enam belas ";
                                break;
                            case 7:
                                bil1 = "Tujuh belas ";
                                break;
                            case 8:
                                bil1 = "Delapan belas ";
                                break;
                            case 9:
                                bil1 = "Sembilan belas ";
                                break;
                            default:
                                break;
                        }
                    } else {
                        bil1 = "Se";
                    }
                    break;
                case 2:
                    bil1 = "Dua ";
                    break;
                case 3:
                    bil1 = "Tiga ";
                    break;
                case 4:
                    bil1 = "Empat ";
                    break;
                case 5:
                    bil1 = "Lima ";
                    break;
                case 6:
                    bil1 = "Enam ";
                    break;
                case 7:
                    bil1 = "Tujuh ";
                    break;
                case 8:
                    bil1 = "Delapan ";
                    break;
                case 9:
                    bil1 = "Sembilan ";
                    break;
                default:
                    bil1 = "";
                    break;
            }

            if (strTot > 0) {
                if (z == 2 || z == 5 || z == 8 || z == 11 || z == 14) {
                    bil2 = "Puluh ";
                } else if (z == 3 || z == 6 || z == 9 || z == 12 || z == 15) {
                    bil2 = "Ratus ";
                } else {
                    bil2 = "";
                }
            } else {
                bil2 = "";
            }

            if (y > 0) {
                switch (z) {
                    case 4:
                        bil2 = bil2 + "Ribu ";
                        y = 0;
                        break;
                    case 7:
                        bil2 = bil2 + "Juta ";
                        y = 0;
                        break;
                    case 10:
                        bil2 = bil2 + "Milyar ";
                        y = 0;
                        break;
                    case 13:
                        bil2 = bil2 + "Trilyun ";
                        y = 0;
                        break;
                    default:
                        break;
                }
            }

            if (bil1.equals("Se")) {
                String pre = bil2.substring(0, 1);
                urai = urai + bil1 + bil2.replace(pre, pre.toLowerCase());
            } else {
                urai = urai + bil1 + bil2;
            }
        }

        return urai;
    }

    public String exportReport2(String reportFormat, String id, String id2, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
        try {
            String url1 = "jdbc:mysql://localhost/buana_mekar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//            String url1 = "jdbc:mysql://localhost:3306/buana_mekar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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
        File file = ResourceUtils.getFile("classpath:reportInvoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        System.out.println("TERBILNAG 1 ="+terbilang(new BigDecimal(100)));
        System.out.println("id=" + id);
        System.out.println("id2=" + id2);
        
        Map<String, Object> parameters = new HashMap<>();
        try{
        parameters.put("param1", id);
        parameters.put("param2", id2);
        id = "200";
        parameters.put("param3", terbilang(new BigDecimal(id)));
        System.out.println("TERBILNAG 2 ="+terbilang(new BigDecimal(id)));
//        parameters.put("param3", terbilang(new BigDecimal(id)));
        
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
        String pdfFileName = "D:/INDEX2/Invoice.pdf";
        response.setContentType("application/x-download");
        response.addHeader("Content-disposition", "attachment; filename=Invoice.pdf");
        OutputStream out = response.getOutputStream();
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jp, path + "\\Invoice.pdf");
            JasperExportManager.exportReportToPdfStream(jp, out);//export PDF directly

        }
        return "succes";
    }

}
