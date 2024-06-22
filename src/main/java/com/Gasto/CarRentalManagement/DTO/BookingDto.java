package com.Gasto.CarRentalManagement.DTO;


import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long id;
    private String book_date;
    private String from_date;
    private LocalDate recieve_date;
    private String to_date;
    private Long user_id;
    private CommonStatus commonStatus;
    private String location;
    private String purpose;
    private Long vehicleId;

}
