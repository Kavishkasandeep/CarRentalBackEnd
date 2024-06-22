package com.Gasto.CarRentalManagement.DTO;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleBookingDto {
    private Long id;
    private String vehical_id;
    private String booking_id;
    private CommonStatus common_states;
}
