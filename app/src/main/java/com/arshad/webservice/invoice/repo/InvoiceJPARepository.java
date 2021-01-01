package com.arshad.webservice.invoice.repo;

import com.arshad.webservice.invoice.beans.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceJPARepository extends JpaRepository<Invoice, Integer>  {
}

