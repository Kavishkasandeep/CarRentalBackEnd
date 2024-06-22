package com.Gasto.CarRentalManagement.Services;

import com.Gasto.CarRentalManagement.DTO.BookingDto;
import com.Gasto.CarRentalManagement.Util.CommonResponse;

public interface BookingService {

    CommonResponse saveBooking(BookingDto bookingDto);

    CommonResponse updateBooking(BookingDto bookingDto);

    CommonResponse deleteBooking(Long bookingId);

    CommonResponse getAllBooking();

    CommonResponse getBooking(Long bookingId);
}
