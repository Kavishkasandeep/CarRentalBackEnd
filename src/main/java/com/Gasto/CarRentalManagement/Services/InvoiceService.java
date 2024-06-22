package com.Gasto.CarRentalManagement.Services;

import com.Gasto.CarRentalManagement.DTO.InvoiceDto;
import com.Gasto.CarRentalManagement.Util.CommonResponse;

public interface InvoiceService {

    CommonResponse saveInvoice(InvoiceDto invoiceDto);

    CommonResponse updateInvoice(InvoiceDto invoiceDto);

    CommonResponse deleteInvoice(Long invoiceId);

    CommonResponse getAllInvoices();

    CommonResponse getInvoice(Long invoiceId);
}
