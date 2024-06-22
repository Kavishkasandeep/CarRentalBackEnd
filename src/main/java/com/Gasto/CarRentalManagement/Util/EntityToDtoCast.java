package com.Gasto.CarRentalManagement.Util;

import com.Gasto.CarRentalManagement.DTO.BookingDto;
import com.Gasto.CarRentalManagement.DTO.InvoiceDto;
import com.Gasto.CarRentalManagement.DTO.UserDto;
import com.Gasto.CarRentalManagement.DTO.VehicalDto;
import com.Gasto.CarRentalManagement.Entity.BookingEntity;
import com.Gasto.CarRentalManagement.Entity.InvoiceEntity;
import com.Gasto.CarRentalManagement.Entity.UserEntity;
import com.Gasto.CarRentalManagement.Entity.VehicalEntity;

public class EntityToDtoCast {
    public static BookingDto castBookingEntityToBookingDto(BookingEntity bookingEntity) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(bookingEntity.getB_id());
        bookingDto.setBook_date(bookingEntity.getBook_date());
        bookingDto.setFrom_date(bookingEntity.getFrom_date());
        bookingDto.setTo_date(bookingEntity.getTo_date());
        bookingDto.setRecieve_date(bookingEntity.getRecieve_date());
        bookingDto.setLocation(bookingEntity.getLocation());
        bookingDto.setUser_id(bookingEntity.getUser_id());
        bookingDto.setCommonStatus(bookingEntity.getCommonStatus());
        bookingDto.setPurpose(bookingEntity.getPurpose());
        bookingDto.setVehicleId(bookingEntity.getVehicleId());
        return bookingDto;
    }

    public static UserDto castUserInToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUser_id(userEntity.getUser_id());
        userDto.setDriving_no(userEntity.getDriving_no());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setTel_no(userEntity.getTel_no());
        userDto.setUserName(userEntity.getUserName());
        userDto.setF_name(userEntity.getF_name());
        userDto.setL_name(userEntity.getL_name());
        userDto.setAge_gap(userEntity.getAge_gap());
        userDto.setCommon_status(userEntity.getCommon_status());
        userDto.setGender(userEntity.getGender());
        return userDto;
    }

    public static VehicalDto castVehicleEntityToVehicleDto(VehicalEntity vehicalEntity) {
        VehicalDto vehicleDto = new VehicalDto();
        vehicleDto.setVehical_id(vehicalEntity.getVehicleId());
        vehicleDto.setName(vehicalEntity.getName());
        vehicleDto.setColor(vehicalEntity.getColor());
        vehicleDto.setDetails(vehicalEntity.getDetails());
        vehicleDto.setImage(vehicalEntity.getImage());
        vehicleDto.setVehical_price(vehicalEntity.getVehiclePrice());
        vehicleDto.setVehical_num(vehicalEntity.getVehicleNum());
        vehicleDto.setType(vehicalEntity.getType());
        vehicleDto.setBrand(vehicalEntity.getBrand());
        vehicleDto.setAvailable_states(vehicalEntity.isAvailableStates());
        vehicleDto.setCommon_states(vehicalEntity.getCommonStatus());
        vehicleDto.setPerDay_price(vehicalEntity.getPerDayPrice());
        return vehicleDto;
    }

    public static InvoiceDto castInvoiceEntityToInvoiceDto(InvoiceEntity invoiceEntity) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoice_id(invoiceEntity.getInvoice_id());
        invoiceDto.setPayment_amount(invoiceEntity.getPayment_amount());
        invoiceDto.setPaid_amount(invoiceEntity.getPaid_amount());
        invoiceDto.setBalance(invoiceEntity.getBalance());
        invoiceDto.setCommon_states(invoiceEntity.getCommon_states());
        invoiceDto.setBooking_id(invoiceEntity.getBooking_id());
        return invoiceDto;
    }

}
