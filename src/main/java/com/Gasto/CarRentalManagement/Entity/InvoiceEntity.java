package com.Gasto.CarRentalManagement.Entity;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Invoice")
public class InvoiceEntity {
    @Id
    @Column(name="id")
    private Long invoice_id;

    @Column(name="payment_amount")
    private double payment_amount;

    @Column(name="paid_amount")
    private double paid_amount;

    @Column(name="balance")
    private double balance;

    @Column(name="common_states")
    private CommonStatus common_states;

    @Column(name="booking_id")
    private long booking_id;
}
