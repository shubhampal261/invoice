package com.arshad.webservice.invoice.serviceImpl;

import com.arshad.webservice.invoice.beans.CustomerResponseModel;
import com.arshad.webservice.invoice.beans.ProductResponseModel;
import com.arshad.webservice.invoice.services.ExternalApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {

    @Value("${external.url.product}")
    private String productBaseURL;

    @Value("${external.url.customer}")
    private String customerBaseURL;

    public ProductResponseModel getProductById(final Integer id) {
        final String uri = this.productBaseURL + "/v1/product/" + id.toString();
        final RestTemplate restTemplate = new RestTemplate();
        try {
            final ProductResponseModel result = restTemplate.getForObject(uri, ProductResponseModel.class);
            System.out.println(result);
            return result;
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
                System.out.println(String.format("product not found with id {%d}", id));
                return null;
            }
            throw ex;
        }
    }

    public CustomerResponseModel getCustomerById(final Integer id) {
        final String uri = this.customerBaseURL + "/v1/customer/" + id.toString();
        final RestTemplate restTemplate = new RestTemplate();
        try {
            final CustomerResponseModel result = restTemplate.getForObject(uri, CustomerResponseModel.class);
            System.out.println(result);
            return result;
        } catch (HttpClientErrorException ex) {
            if (ex.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
                System.out.println(String.format("product not found with id {%d}", id));
                return null;
            }
            throw ex;
        }
    }
}
