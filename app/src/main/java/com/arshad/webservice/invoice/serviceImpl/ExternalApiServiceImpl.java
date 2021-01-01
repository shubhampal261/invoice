package com.arshad.webservice.invoice.serviceImpl;

import com.arshad.webservice.invoice.beans.ProductResponseModel;
import com.arshad.webservice.invoice.beans.UserResponseModel;
import com.arshad.webservice.invoice.services.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
        final ProductResponseModel result = restTemplate.getForObject(uri, ProductResponseModel.class);
        System.out.println(result);
        return result;
    }

    public UserResponseModel getCustomerById(final Integer id) {
        final String uri = this.customerBaseURL + "/v1/user-management/user/" + id.toString();
        final RestTemplate restTemplate = new RestTemplate();
        final UserResponseModel result = restTemplate.getForObject(uri, UserResponseModel.class);
        System.out.println(result);
        return result;
    }
}
