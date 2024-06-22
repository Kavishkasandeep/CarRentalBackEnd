package com.Gasto.CarRentalManagement.Controller;

import com.Gasto.CarRentalManagement.DTO.InvoiceDto;
import com.Gasto.CarRentalManagement.Services.InvoiceService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/InvoiceManagement")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("")
    public String invoiceControllerHome() {
        return "Welcome to Invoice Management Controller";
    }

    @PostMapping(path = "/saveInvoice")
    public CommonResponse saveInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.saveInvoice(invoiceDto);
    }

    @PutMapping(path = "/updateInvoice")
    public CommonResponse updateInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.updateInvoice(invoiceDto);
    }

    @DeleteMapping("/deleteInvoice/{invoiceId}")
    public CommonResponse deleteInvoice(@PathVariable Long invoiceId) {
        return invoiceService.deleteInvoice(invoiceId);
    }

    @GetMapping(path = "/getAllInvoices")
    public CommonResponse getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping(path = "/getInvoice/{invoiceId}")
    public CommonResponse getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }
}
