package com.Gasto.CarRentalManagement.Controller;

import com.Gasto.CarRentalManagement.DTO.BookingDto;
import com.Gasto.CarRentalManagement.Services.BookingService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/BookingManagement")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService=bookingService;
    }

    @GetMapping("")
    public String BookingControllerHome (){
        return "Welcome to Booking Management Controller";
    }


    @PostMapping(path="/saveBooking")
    public CommonResponse saveBooking(@RequestBody BookingDto bookingDto){
        System.out.println("the booking save DTo is:\n"+bookingDto.toString());
        CommonResponse cr = new CommonResponse();
        cr=bookingService.saveBooking(bookingDto);
        System.out.println("the booking aftter save coom response  is:\n"+cr.toString());
        return cr;

    }
    @PutMapping(path = "/updateBooking")
    public CommonResponse updateBooking(@RequestBody BookingDto bookingDto) {
        bookingDto.setId(Long.valueOf(1));
        System.out.println("the booking update DTo is:\n"+bookingDto.toString());
        CommonResponse cr = new CommonResponse();
        cr=bookingService.updateBooking(bookingDto);
        System.out.println("the booking aftter update  commn response  is:\n"+cr.toString());
        return cr;

    }


    @DeleteMapping("/deleteBooking/{bookingId}")
    public CommonResponse deleteBooking(@PathVariable Long bookingId) {
//        CommonResponse response = bookingService.deleteBooking(bookingId);
//        if (!response.getErrorMessages().isEmpty()) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }

        return bookingService.deleteBooking(bookingId);
    }

    @GetMapping(path="/getAllBooking")
    public CommonResponse getAllBooking(){
//        System.out.println("the booking save DTo is:\n"+bookingDto.toString());
        CommonResponse cr = new CommonResponse();
        cr=bookingService.getAllBooking();
        System.out.println("the bookings aftter after getting all coom response  is:\n"+cr.toString());
        return cr;

    }

    @GetMapping(path="/getBooking/{bookingId }")
    public CommonResponse getBooking(@PathVariable Long bookingId){
        return bookingService.getBooking(bookingId);
    }

}
