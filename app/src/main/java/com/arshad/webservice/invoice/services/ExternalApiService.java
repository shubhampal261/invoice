package com.arshad.webservice.invoice.services;

import com.arshad.webservice.invoice.beans.ProductResponseModel;
import com.arshad.webservice.invoice.beans.UserResponseModel;

public interface ExternalApiService {

    ProductResponseModel getProductById(final Integer id);

    UserResponseModel getCustomerById(final Integer id);
}
