package com.arshad.webservice.invoice.serviceImpl;

import com.arshad.webservice.invoice.beans.Invoice;
import com.arshad.webservice.invoice.beans.InvoiceResponseModel;
import com.arshad.webservice.invoice.mapper.InvoiceMapper;
import com.arshad.webservice.invoice.repo.InvoiceJPARepository;
import com.arshad.webservice.invoice.services.ExternalApiService;
import com.arshad.webservice.invoice.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("invoiceServiceDbImpl")
public class InvoiceServiceDbImpl implements InvoiceService {

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private InvoiceJPARepository invoiceRepository;

    public List<InvoiceResponseModel> getAll() {
        List<InvoiceResponseModel> userList = InvoiceMapper.INSTANCE.mapToUserResponseModelList(invoiceRepository.findAll());
        return userList;
    }

    public InvoiceResponseModel getByID(int id) {
        Optional<Invoice> userOptional = invoiceRepository.findById(id);
        if (userOptional.isPresent()) {
            final InvoiceResponseModel response = InvoiceMapper.INSTANCE.mapToUserResponseModel(userOptional.get());
            response.setProductDetails(this.externalApiService.getProductById(response.getProduct()));
            response.setCustomerDetails(this.externalApiService.getCustomerById(response.getCustomer()));
            return response;
        }
        return null;
    }

    public InvoiceResponseModel add(Invoice invoice) {
        this.externalApiService.getProductById(invoice.getProduct());
        this.externalApiService.getCustomerById(invoice.getCustomer());
        invoice = invoiceRepository.save(invoice);
        return InvoiceMapper.INSTANCE.mapToUserResponseModel(invoice);
    }

    @Override
    public InvoiceResponseModel deleteById(int id) {
        Optional<Invoice> userOptional = invoiceRepository.findById(id);
        if (userOptional.isPresent()) {
            Invoice invoice = userOptional.get();
            invoiceRepository.delete(invoice);
            return InvoiceMapper.INSTANCE.mapToUserResponseModel(invoice);
        }
        return null;
    }

}

