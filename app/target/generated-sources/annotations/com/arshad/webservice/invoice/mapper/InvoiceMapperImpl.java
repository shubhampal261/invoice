package com.arshad.webservice.invoice.mapper;

import com.arshad.webservice.invoice.beans.Invoice;
import com.arshad.webservice.invoice.beans.InvoiceResponseModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-01T22:32:18+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceResponseModel mapToUserResponseModel(Invoice user) {
        if ( user == null ) {
            return null;
        }

        InvoiceResponseModel invoiceResponseModel = new InvoiceResponseModel();

        invoiceResponseModel.setId( user.getId() );
        invoiceResponseModel.setNumber( user.getNumber() );
        invoiceResponseModel.setInvoiceDate( user.getInvoiceDate() );
        invoiceResponseModel.setCustomer( user.getCustomer() );
        invoiceResponseModel.setProduct( user.getProduct() );
        invoiceResponseModel.setQuantity( user.getQuantity() );
        invoiceResponseModel.setCreatedAt( user.getCreatedAt() );
        invoiceResponseModel.setUpdatedAt( user.getUpdatedAt() );

        return invoiceResponseModel;
    }

    @Override
    public List<InvoiceResponseModel> mapToUserResponseModelList(List<Invoice> userList) {
        if ( userList == null ) {
            return null;
        }

        List<InvoiceResponseModel> list = new ArrayList<InvoiceResponseModel>( userList.size() );
        for ( Invoice invoice : userList ) {
            list.add( mapToUserResponseModel( invoice ) );
        }

        return list;
    }
}
