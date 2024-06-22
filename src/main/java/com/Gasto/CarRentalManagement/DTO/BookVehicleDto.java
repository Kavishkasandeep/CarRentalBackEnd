package com.Gasto.CarRentalManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookVehicleDto {
    private long vehicleId;
    private LocalDate todate;
}
