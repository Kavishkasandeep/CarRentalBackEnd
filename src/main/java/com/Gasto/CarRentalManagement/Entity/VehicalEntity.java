package com.Gasto.CarRentalManagement.Entity;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle") // Assuming your table name is "vehicle"
public class VehicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Add generation strategy if applicable
    @Column(name = "id")
    private Long vehicleId;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "details")
    private String details;

    @Column(name = "image")
    private String image;

    @Column(name = "vehicle_price")
    private double vehiclePrice;

    @Column(name = "vehicle_num")
    private String vehicleNum;

    @Column(name = "type")
    private String type;

    @Column(name = "brand")
    private String brand;

    @Column(name = "available_states")
    private boolean availableStates;

    @Enumerated(EnumType.STRING)
    @Column(name = "common_status") // Match the column name with the database
    private CommonStatus commonStatus;

    @Column(name = "per_day_price")
    private double perDayPrice;
}
