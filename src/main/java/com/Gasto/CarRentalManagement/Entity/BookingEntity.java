package com.Gasto.CarRentalManagement.Entity;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long b_id;

    @Column(name="book_date")
    private String book_date;

    @Column(name="from_date")
    private String from_date;

    @Column(name="recieve_date")
    private LocalDate recieve_date;

    @Column(name="to_date")
    private String to_date;

    @Column(name="user_id")
    private Long user_id;

    @Column(name="common_states")
    private CommonStatus commonStatus;

    @Column(name="location")
    private String location;

    @Column(name="purpose")
    private String purpose;

    @Column(name = "vehicle_id")
    private Long vehicleId;
}
