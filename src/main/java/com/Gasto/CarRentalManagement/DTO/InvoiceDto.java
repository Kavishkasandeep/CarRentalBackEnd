package com.Gasto.CarRentalManagement.DTO;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long invoice_id;
    private double payment_amount;
    private double paid_amount;
    private double balance;
    private CommonStatus common_states;
    private long booking_id;
}
