package com.lucodes.myfancypdfinvoices.springboot.web;

import com.lucodes.myfancypdfinvoices.springboot.dto.InvoiceDto;
import com.lucodes.myfancypdfinvoices.springboot.model.Invoice;
import com.lucodes.myfancypdfinvoices.springboot.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public Iterable<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @GetMapping("/invoices/user/{userId}")
    public Iterable<Invoice> findByUserId(@PathVariable String userId) {
        return invoiceService.findByUserId(userId);
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
