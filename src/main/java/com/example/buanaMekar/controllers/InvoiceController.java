/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.controllers;

import com.example.buanaMekar.entities.Invoice;
import com.example.buanaMekar.entities.Penagihan;
import com.example.buanaMekar.entities.SuratJalan;
import com.example.buanaMekar.repositories.SuratJalanRepository;
import com.example.buanaMekar.services.InvoiceService;
import com.example.buanaMekar.services.PenagihanService;
import com.example.buanaMekar.services.SuratJalanService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Insane
 */
@Controller
public class InvoiceController {

    @Autowired
    InvoiceService service;

    @Autowired
    SuratJalanService serviceSuratJalan;

    @Autowired
    PenagihanService servicePenagihan;

    @Autowired
    SuratJalanRepository repo;

    public String id1 = "", id2 = "";

    @RequestMapping("/invoice/report/{format}")
    public String generateReport(@PathVariable String format, @RequestParam(required = false, value = "id") String id, @RequestParam(required = false, value = "id2") String id22, HttpServletResponse response) throws FileNotFoundException, JRException, IOException {
        return service.exportReport2(format, id1, id2, response);
    }

    @RequestMapping("/invoice/createInvoice")
    public String createInvoice() {
        return "createInvoice";
    }

    @RequestMapping("/invoice")
    public String viewInvoicePage(Model model) {

        Date HariSekarang = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        List<Invoice> listInvoices = service.listAllInvoice();
        model.addAttribute("listInvoices", listInvoices);

//        insert ke penagihan
        for (int i = 0; i < listInvoices.size(); i++) {
            int hasilPerbandingan = ft.format(HariSekarang).compareTo(listInvoices.get(i).getTglJatuhTempo().substring(0, 10));
            System.out.println(ft.format(HariSekarang) + "=" + listInvoices.get(i).getTglJatuhTempo().substring(0, 10));
            System.out.println("Hasil perbandingan = " + hasilPerbandingan);
            if (hasilPerbandingan >= 0 && listInvoices.get(i).getStatus() == 0) {
                System.out.println("MASUK PENAGIHAN");
                Invoice invoice = new Invoice();
                invoice.setId(listInvoices.get(i).getId());
                invoice.setInvoice(listInvoices.get(i).getInvoice());
                invoice.setSuratJalan(listInvoices.get(i).getSuratJalan());
                invoice.setPpn(listInvoices.get(i).getPpn());
                invoice.setTotalHarga(listInvoices.get(i).getTotalHarga());
                invoice.setTglJatuhTempo(listInvoices.get(i).getTglJatuhTempo().substring(0, 10));
                invoice.setStatus(1);
                service.save(invoice);

                Penagihan penagihan = new Penagihan();
                penagihan.setIdInvoice(invoice);
                penagihan.setInvoice(listInvoices.get(i).getInvoice());
                penagihan.setStatusPenagihan("BELUM DITAGIH");
                penagihan.setCatatan("BELUM ADA AKSI");
                servicePenagihan.save(penagihan);
            }
        }
        return "listInvoice";

    }

//    @RequestMapping("/laporanBulanan")
//    public String viewLaporanBulanan(Model model) {
//
//        List<Invoice> listInvoices = service.();
//        model.addAttribute("listInvoices", listInvoices);
//
//        return "listInvoiceBulanan";
//    }
    @RequestMapping("/detailInvoice")
    public String viewDetailSuratJalanPage(Model model, HttpServletRequest request) {
        List<SuratJalan> listSuratJalans = serviceSuratJalan.listDetailInvoice(request.getParameter("id").replaceAll("%20", " "), request.getParameter("id2").replaceAll("%20", " "));
        id1 = request.getParameter("id").replaceAll("%20", " ");
        id2 = request.getParameter("id2").replaceAll("%20", " ");
        model.addAttribute("listSuratJalans", listSuratJalans);
        return "listDetailInvoice";
    }

    @RequestMapping("/invoice/new")
    public String showNewInvoiceForm(Model model) {
        model.addAttribute("someBean", new SuratJalan());
        Invoice invoice = new Invoice();
        model.addAttribute("orderan", service.getAllOrderan());
        model.addAttribute("invoice", invoice);
        return "createInvoice";
    }

    @RequestMapping(value = "/invoice/save", method = RequestMethod.POST)
    public String saveInvoice(@RequestParam(value = "isTax", required = false, defaultValue = "0") String isTax, HttpServletRequest request) {

        Invoice invoicenya = new Invoice();
        String arrayBulan[] = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};
        Double totalHarga = 0.0;
        List<SuratJalan> listSuratJalans = serviceSuratJalan.listDetailSuratJalan(request.getParameter("id").replaceAll("%20", " "), request.getParameter("id2").replaceAll("%20", " "));
        for (int i = 0; i < listSuratJalans.size(); i++) {
            totalHarga = totalHarga + Double.valueOf(listSuratJalans.get(i).getOrderan().getTotalHarga());
        }

        for (int i = 0; i < listSuratJalans.size(); i++) {
            invoicenya.setId(i);
            invoicenya.setStatus(0);
            SuratJalan sj = new SuratJalan();

            sj.setId(listSuratJalans.get(i).getId());
            System.out.println("is Tax = " + isTax);
            System.out.println("Nilainya 1 = " + listSuratJalans.get(i).getId());
            sj.setOrderan(listSuratJalans.get(i).getOrderan());
            sj.setTglKirim(listSuratJalans.get(i).getTglKirim());
            sj.setTglTerima(listSuratJalans.get(i).getTglTerima());
            sj.setStatus("1");
//            sj.setId();
            sj.setIsTax(sj.getIsTax()); //ini
            sj.setIsTax(isTax);
            invoicenya.setSuratJalan(sj);

            Date today = new Date();


            //formatting date in Java using SimpleDateFormat
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy");
            String date = DATE_FORMAT.format(today);
            Calendar cal3 = Calendar.getInstance();
            Date tgl;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //update
            invoicenya.setInvoice(listSuratJalans.get(0).getId() + "/INV/BPK/SPR/"
                    + arrayBulan[Integer.parseInt(listSuratJalans.get(i).getTglKirim().substring(3, 4).equals("0")
                            ? listSuratJalans.get(i).getTglKirim().substring(4, 5) : listSuratJalans.get(i).getTglKirim()
                            .substring(3, 5))] + "/" + date);
            try {
                tgl = sdf.parse(listSuratJalans.get(i).getTglKirim());
                cal3.setTime(tgl);
                cal3.add(Calendar.MONTH, 1);
                invoicenya.setTglJatuhTempo(sdf.format(cal3.getTime()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Double result = null;
            Double ppnnya = totalHarga * 10 / 100;
            if (isTax.equals("1")) {
                System.out.println("PPN nya :"+ppnnya);
                result = totalHarga + ppnnya;
                invoicenya.setPpn(ppnnya.toString());
                invoicenya.setTotalHarga(result.toString());
            } else {
                invoicenya.setPpn("0");
                result = Double.parseDouble("0");
                invoicenya.setTotalHarga(totalHarga.toString());
            }

            System.out.println("TOTAL HRGA= "+totalHarga);
            
            service.save(invoicenya);
            serviceSuratJalan.save(sj);
//            repo.save(sj);
        }

        return "redirect:/invoice";
    }

    @RequestMapping("/invoice/edit/{id}")
    public ModelAndView showEditInvoiceForm(@PathVariable(name = "id") String id, Model model) {
        ModelAndView mav = new ModelAndView("editOrderan");
        Invoice invoice = service.get(id);
        model.addAttribute("orderan", service.getAllOrderan());
        mav.addObject("invoice", invoice);
        return mav;
    }

    @RequestMapping("/invoice/delete/{id}")
    public String deleteOrderan(@PathVariable(name = "id") String id) {
        service.delete(id);

        return "redirect:/invoice";
    }

}
