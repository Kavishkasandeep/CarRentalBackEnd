package com.Gasto.CarRentalManagement.Util;

import com.Gasto.CarRentalManagement.DTO.BookingDto;
import com.Gasto.CarRentalManagement.DTO.InvoiceDto;
import com.Gasto.CarRentalManagement.DTO.UserDto;
import com.Gasto.CarRentalManagement.DTO.VehicalDto;
import com.Gasto.CarRentalManagement.Entity.BookingEntity;
import com.Gasto.CarRentalManagement.Entity.InvoiceEntity;
import com.Gasto.CarRentalManagement.Entity.UserEntity;
import com.Gasto.CarRentalManagement.Entity.VehicalEntity;
import com.Gasto.CarRentalManagement.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


public class DtoToEntityCast {
    @Autowired
    private  BookingRepository  bookingRepository;



    public static BookingEntity castBookingDtoInToBookingEntity(BookingDto bookingDto){
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setB_id(bookingDto.getId());
        bookingEntity.setBook_date(bookingDto.getBook_date());
        bookingEntity.setFrom_date(bookingDto.getFrom_date());
        bookingEntity.setTo_date(bookingDto.getTo_date());
        bookingEntity.setRecieve_date(bookingDto.getRecieve_date());
        bookingEntity.setLocation(bookingDto.getLocation());
        bookingEntity.setUser_id(bookingDto.getUser_id());
        bookingEntity.setCommonStatus(bookingDto.getCommonStatus());
        bookingEntity.setPurpose(bookingDto.getPurpose());
        bookingEntity.setVehicleId(bookingDto.getVehicleId());
        Long bookingId = bookingDto.getId();
        LocalDate initialToDate = LocalDate.parse(bookingEntity.getTo_date());

//        List<BookingDto> bookingDtoList1 = bookingRepository.findAllByBookingId(bookingId);
//
//        if(bookingDtoList1 != null) {
//            List<BookingDto> dtoList = new ArrayList<>();
//            for (BookingDto dto : bookingDtoList1) {
//                LocalDate availableDate = dto.getTo_date();
//                LocalDate nowDate = LocalDate.now();
//                if (availableDate.isAfter(nowDate)) {
//                    dto.setTo_date(LocalDate.parse(String.valueOf(nowDate)));
//                } else {
//                    System.out.println("The to-date is not after today.");
//                }
//                dtoList.add(dto);
//            }
//
//            if (!dtoList.isEmpty()) {
//                for (BookingDto dto : dtoList) {
//                    bookingEntity.setTo_date(dto.getTo_date());
//                }
//            } else {
//                bookingEntity.setTo_date(initialToDate);
//            }
//        }else{
//            bookingEntity.setTo_date(bookingDto.getTo_date());
//        }
        return bookingEntity;
    }

//    private List<BookingDto> checkingIsAvailableVehicle(Long vehicleId, LocalDate tiDate) {
//        List<BookingDto> bookingDtoList1 = bookingRepository.findByVehicleIdAndTo_date(vehicleId,tiDate);
//        List<BookingDto> dtoList = new ArrayList<>();
//        BookingDto  bookingDto2 = new BookingDto();
//        for (BookingDto bookingDto : bookingDtoList1
//        ){
//
//           Date availableDate = bookingDto.getTo_date();
//           Date nowDate = Date.now();
//            if (availableDate.isAfter(nowDate)) {
//                bookingDto2.setTo_date(String.valueOf(nowDate));
//            } else {
//                System.out.println("The to-date is not after today.");
//            }
//            dtoList.add(bookingDto2);
//        }
//        return dtoList;
//    }


    public static UserEntity castUserDtoInToUserEntity(UserDto userDto) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_id(userDto.getUser_id());
            userEntity.setDriving_no(userDto.getDriving_no());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPassword(userDto.getPassword());
            userEntity.setTel_no(userDto.getTel_no());
            userEntity.setUserName(userDto.getUserName());
            userEntity.setF_name(userDto.getF_name());
            userEntity.setL_name(userDto.getL_name());
            userEntity.setAge_gap(userDto.getAge_gap());
            userEntity.setCommon_status(userDto.getCommon_status());
            userEntity.setGender(userDto.getGender());
            return userEntity;
        }





        public static VehicalEntity castVehicleDtoToVehicleEntity(VehicalDto vehicleDto) {
            if (vehicleDto == null) {
                return null;
            }

            VehicalEntity  vehicleEntity = new VehicalEntity();

            vehicleEntity.setVehicleId(vehicleDto.getVehical_id());
            vehicleEntity.setName(vehicleDto.getName());
            vehicleEntity.setColor(vehicleDto.getColor());
            vehicleEntity.setDetails(vehicleDto.getDetails());
            vehicleEntity.setImage(vehicleDto.getImage());
            vehicleEntity.setVehiclePrice(vehicleDto.getVehical_price());
            vehicleEntity.setVehicleNum(vehicleDto.getVehical_num());
            vehicleEntity.setType(vehicleDto.getType());
            vehicleEntity.setBrand(vehicleDto.getBrand());
            vehicleEntity.setAvailableStates(vehicleDto.isAvailable_states());
            vehicleEntity.setCommonStatus(vehicleDto.getCommon_states());
            vehicleEntity.setPerDayPrice(vehicleDto.getPerDay_price());

            return vehicleEntity;
        }

    public static InvoiceEntity castInvoiceDtoToInvoiceEntity(InvoiceDto invoiceDto) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setInvoice_id(invoiceDto.getInvoice_id());
        invoiceEntity.setPayment_amount(invoiceDto.getPayment_amount());
        invoiceEntity.setPaid_amount(invoiceDto.getPaid_amount());
        invoiceEntity.setBalance(invoiceDto.getBalance());
        invoiceEntity.setCommon_states(invoiceDto.getCommon_states());
        invoiceEntity.setBooking_id(invoiceDto.getBooking_id());
        return invoiceEntity;
    }


}
