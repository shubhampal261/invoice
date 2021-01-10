package com.arshad.webservice.invoice.serviceImpl;

import com.arshad.webservice.invoice.beans.Invoice;
import com.arshad.webservice.invoice.beans.InvoiceResponseModel;
import com.arshad.webservice.invoice.beans.ProductResponseModel;
import com.arshad.webservice.invoice.beans.CustomerResponseModel;
import com.arshad.webservice.invoice.mapper.InvoiceMapper;
import com.arshad.webservice.invoice.repo.InvoiceJPARepository;
import com.arshad.webservice.invoice.services.ExternalApiService;
import com.arshad.webservice.invoice.services.InvoiceService;
import com.arshad.webservice.invoice.utils.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Qualifier("invoiceServiceDbImpl")
public class InvoiceServiceDbImpl implements InvoiceService {

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private InvoiceJPARepository invoiceRepository;

    public List<InvoiceResponseModel> getAll() {
        List<InvoiceResponseModel> invoiceList = InvoiceMapper.INSTANCE.mapToInvoiceResponseModelList(invoiceRepository.findAll());
        if (CollectionUtils.isEmpty(invoiceList)) {
            return Collections.emptyList();
        }
        invoiceList.forEach(e -> e.setAmount(e.getRate() * e.getQuantity()));
        return invoiceList;
    }

    public InvoiceResponseModel getByID(int id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            final InvoiceResponseModel response = convertInvoiceEntityIntoResponseModel(invoiceOptional.get());
            response.setProductDetails(this.externalApiService.getProductById(response.getProduct()));
            response.setCustomerDetails(this.externalApiService.getCustomerById(response.getCustomer()));
            return response;
        }
        return null;
    }

    public InvoiceResponseModel add(Invoice invoice) {
        this.validateInvoice(invoice);
        invoice = invoiceRepository.save(invoice);
        return convertInvoiceEntityIntoResponseModel(invoice);
    }
    
    private void validateInvoice(final Invoice invoice) {
        if (invoice.getCustomer() == null) {
            throw new BadRequestException("customer must not be null");
        }
        final CustomerResponseModel customer = this.externalApiService.getCustomerById(invoice.getCustomer());
        if (customer == null) {
            throw new BadRequestException(String.format("customer with id '%d' is not present in the system", invoice.getCustomer()));
        }
        if (invoice.getProduct() == null) {
            throw new BadRequestException("product must not be null");
        }
        final ProductResponseModel product = this.externalApiService.getProductById(invoice.getProduct());
        if (customer == null) {
            throw new BadRequestException(String.format("product with id '%d' is not present in the system", invoice.getProduct()));
        }
        if (invoice.getRate() == null) {
            invoice.setRate(product.getRate());
        }
    }

    private InvoiceResponseModel convertInvoiceEntityIntoResponseModel(final Invoice invoice) {
        final InvoiceResponseModel invoiceResponseModel = InvoiceMapper.INSTANCE.mapToInvoiceResponseModel(invoice);
        invoiceResponseModel.setAmount(invoice.getRate() * invoice.getQuantity());
        return invoiceResponseModel;
    }

    @Override
    public InvoiceResponseModel deleteById(int id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            invoiceRepository.delete(invoice);
            return convertInvoiceEntityIntoResponseModel(invoice);
        }
        return null;
    }

}

