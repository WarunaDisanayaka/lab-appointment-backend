package com.lab.labappointment.controller;

import com.lab.labappointment.entity.Invoice;
import com.lab.labappointment.entity.InvoiceItem;
import com.lab.labappointment.entity.PatientsEntity;
import com.lab.labappointment.repo.InvoiceItemRepository;
import com.lab.labappointment.repo.InvoiceRepository;
import com.lab.labappointment.repo.PatientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "http://localhost:3000")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private PatientsRepo patientsRepo;

    @PostMapping("/{patientId}")
    public ResponseEntity<Invoice> createInvoice(@PathVariable Long patientId, @RequestBody Invoice invoice) {
        Optional<PatientsEntity> patientOptional = patientsRepo.findById(Math.toIntExact(patientId));
        if (patientOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PatientsEntity patient = patientOptional.get();
        invoice.setPatient(patient);

        Invoice savedInvoice = invoiceRepository.save(invoice);
        return ResponseEntity.ok(savedInvoice);
    }


    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        if (invoiceOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Invoice invoice = invoiceOptional.get();
        return ResponseEntity.ok(invoice);
    }

    // Other CRUD operations for invoices

    @PostMapping("/{invoiceId}/items")
    public ResponseEntity<InvoiceItem> addItemToInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceItem item) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        if (invoiceOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Invoice invoice = invoiceOptional.get();
        item.setInvoice(invoice);

        InvoiceItem savedItem = invoiceItemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }

    // Other operations for managing invoice items
}
