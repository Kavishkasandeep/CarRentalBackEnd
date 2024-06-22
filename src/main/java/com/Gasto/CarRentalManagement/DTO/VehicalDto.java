package com.Gasto.CarRentalManagement.DTO;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicalDto {
    private Long vehical_id;
    private String name;
    private String color;
    private String details;
    private String image;
    private double vehical_price;
    private String vehical_num;
    private String type;
    private String brand;
    private boolean available_states;
    private CommonStatus common_states;
    private double perDay_price;
}
