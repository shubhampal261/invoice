package com.arshad.webservice.invoice.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InvoiceResponseModel {

    private int id;

    private String number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date invoiceDate;

    private Integer customer;

    private UserResponseModel customerDetails;

    private Integer product;

    private ProductResponseModel productDetails;

    private Double quantity;

    private Date createdAt;

    private Date updatedAt;

    public InvoiceResponseModel() {
    }

    public InvoiceResponseModel(int id, String number, Date invoiceDate, Integer customer, Integer product, Double quantity, Date createdAt, Date updatedAt) {
        this.id = id;
        this.number = number;
        this.invoiceDate = invoiceDate;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserResponseModel getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(UserResponseModel customerDetails) {
        this.customerDetails = customerDetails;
    }

    public ProductResponseModel getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductResponseModel productDetails) {
        this.productDetails = productDetails;
    }
}
