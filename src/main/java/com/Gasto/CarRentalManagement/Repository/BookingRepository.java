package com.Gasto.CarRentalManagement.Repository;

import com.Gasto.CarRentalManagement.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    List<BookingEntity> findById(Long id);
}
