package com.Gasto.CarRentalManagement.Repository;


import com.Gasto.CarRentalManagement.Entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository <InvoiceEntity,Long> {
}
