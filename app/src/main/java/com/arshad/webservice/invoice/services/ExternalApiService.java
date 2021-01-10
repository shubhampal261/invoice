package com.arshad.webservice.invoice.services;

import com.arshad.webservice.invoice.beans.CustomerResponseModel;
import com.arshad.webservice.invoice.beans.ProductResponseModel;

public interface ExternalApiService {

    ProductResponseModel getProductById(final Integer id);

    CustomerResponseModel getCustomerById(final Integer id);
}
