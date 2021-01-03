package com.arshad.webservice.invoice.services;

import com.arshad.webservice.invoice.beans.Invoice;
import com.arshad.webservice.invoice.beans.InvoiceResponseModel;

import java.util.List;

public interface InvoiceService {

    public List<InvoiceResponseModel> getAll();

    public InvoiceResponseModel getByID(int id);

    public InvoiceResponseModel add(Invoice invoice);

    public InvoiceResponseModel deleteById(int id);
}
