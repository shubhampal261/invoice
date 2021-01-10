package com.arshad.webservice.invoice.mapper;

import com.arshad.webservice.invoice.beans.Invoice;
import com.arshad.webservice.invoice.beans.InvoiceResponseModel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    InvoiceResponseModel mapToInvoiceResponseModel(final Invoice invoice);

    List<InvoiceResponseModel> mapToInvoiceResponseModelList(final List<Invoice> invoiceList);
}
