package com.lucodes.myfancypdfinvoices.springboot.service;

import com.lucodes.myfancypdfinvoices.springboot.model.Invoice;
import com.lucodes.myfancypdfinvoices.springboot.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final String cdnUrl;

    public InvoiceService(InvoiceRepository invoiceRepository, @Value("${cdn.url}") String cdnUrl) {
        this.invoiceRepository = invoiceRepository;
        this.cdnUrl = cdnUrl;
    }

    @PostConstruct
    public void init() {
        log.info("Fetching PDF Template from S3...");
    }

    @PreDestroy
    public void shutdown() {
        log.info("Deleting downloaded templates...");
    }

    @Transactional
    public Iterable<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        log.info("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";

        Invoice invoice = new Invoice();
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);

        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Iterable<Invoice> findByUserId(String userId) {
        return invoiceRepository.findByUserId(userId);
    }
}
