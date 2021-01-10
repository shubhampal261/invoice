package com.arshad.webservice.invoice.controllers;

import com.arshad.webservice.invoice.beans.Invoice;
import com.arshad.webservice.invoice.beans.InvoiceResponseModel;
import com.arshad.webservice.invoice.services.InvoiceService;
import com.arshad.webservice.invoice.utils.InvoiceConstants;
import com.arshad.webservice.invoice.utils.exceptions.InvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/invoice")
public class InvoiceController {

    @Autowired
    @Qualifier(value = "invoiceServiceDbImpl")
    private InvoiceService invoiceServiceImpl;

    @GetMapping()
    public List<InvoiceResponseModel> getAll(){
        return invoiceServiceImpl.getAll();
    }

    @GetMapping(path = "/{id}")
    public InvoiceResponseModel getById(@PathVariable int id){
        InvoiceResponseModel invoice = invoiceServiceImpl.getByID(id);
        if(invoice == null){
            throw new InvoiceNotFoundException(String.format(InvoiceConstants.NOT_FOUND_FOR_ID,id));
        }
        return invoice;
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody Invoice invoice){
        invoiceServiceImpl.add(invoice);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        InvoiceResponseModel invoice = invoiceServiceImpl.deleteById(id);
        if(invoice == null){
            throw new InvoiceNotFoundException(String.format(InvoiceConstants.CANNOT_DELETE,id));
        }
        return ResponseEntity.noContent().build();
    }

}
