package com.Gasto.CarRentalManagement.Services.ServiceIMPL;

import com.Gasto.CarRentalManagement.Const.CommonMsg;
import com.Gasto.CarRentalManagement.Const.CommonStatus;
import com.Gasto.CarRentalManagement.DTO.BookingDto;
import com.Gasto.CarRentalManagement.Entity.BookingEntity;
import com.Gasto.CarRentalManagement.Repository.BookingRepository;
import com.Gasto.CarRentalManagement.Services.BookingService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import com.Gasto.CarRentalManagement.Util.CommonValidation;
import com.Gasto.CarRentalManagement.Util.DtoToEntityCast;
import com.Gasto.CarRentalManagement.Util.EntityToDtoCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class BookingServiceIMPL implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceIMPL(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public CommonResponse saveBooking(BookingDto bookingDto) {
        CommonResponse commonResponse = new CommonResponse();
        BookingEntity bookingEntity;
        List<String> validationList;

        try {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = formatter.format(date);
            bookingDto.setBook_date(formattedDate);

            validationList = this.bookingValidation(bookingDto);

            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                commonResponse.setCommonMessage("Booking save failed.");
                return commonResponse;
            }

           // bookingEntity = DtoToEntityCast.castBookingDtoInToBookingEntity(bookingDto);
            BookingEntity bookingEntity1 = new BookingEntity();
            bookingEntity1.setBook_date(bookingDto.getBook_date());
            bookingEntity1.setFrom_date(bookingDto.getFrom_date());

            bookingEntity1.setRecieve_date(bookingDto.getRecieve_date());
            bookingEntity1.setLocation(bookingDto.getLocation());
            bookingEntity1.setUser_id(bookingDto.getUser_id());
            bookingEntity1.setCommonStatus(bookingDto.getCommonStatus());
            bookingEntity1.setPurpose(bookingDto.getPurpose());

            Long bookingId = bookingDto.getId();
            LocalDate initialToDate = LocalDate.parse(bookingEntity1.getTo_date());
            Long vehicleId = bookingEntity1.getVehicleId();

            List<BookingEntity> bookingDtoList1 = bookingRepository.findById(bookingId);

            if(bookingDtoList1.size() != 0) {
                List<BookingEntity> dtoList = new ArrayList<>();
                for (BookingEntity dto : bookingDtoList1) {
                    LocalDate availableDate = LocalDate.parse(dto.getTo_date());
                    LocalDate nowDate = LocalDate.now();
                    if (nowDate.isAfter(availableDate)) {
                        dto.setTo_date(String.valueOf(availableDate));

                    } else {
                       return commonResponse;
                    }
                    dtoList.add(dto);
                }

                if (!dtoList.isEmpty()) {
                    for (BookingEntity dto : dtoList) {
                        bookingEntity1.setTo_date(dto.getTo_date());
                    }
                    bookingEntity1.setVehicleId(bookingDto.getVehicleId());
                    bookingEntity1 = bookingRepository.save(bookingEntity1);
                    commonResponse.setPayload(Collections.singletonList(bookingEntity1));
                    commonResponse.setCommonMessage("Booking saved successfully.");
                    commonResponse.setStatus(true);
                } else {
                    bookingEntity1.setTo_date(String.valueOf(initialToDate));
                }
            }else{
               bookingEntity1.setTo_date(bookingDto.getTo_date());
               bookingEntity1.setVehicleId(bookingDto.getVehicleId());
                bookingEntity1 = bookingRepository.save(bookingEntity1);
                commonResponse.setPayload(Collections.singletonList(bookingEntity1));
                commonResponse.setCommonMessage("Booking saved successfully.");
                commonResponse.setStatus(true);
            }


        } catch (Exception e) {
            LOGGER.error("/**************Exception in Booking Service -> saveBooking(^)!", e);
            commonResponse.setCommonMessage("An error occurred while saving the booking.");
            commonResponse.setStatus(false);
        }
        return commonResponse;


    }

    @Override
    public CommonResponse updateBooking(BookingDto bookingDto) {
        CommonResponse commonResponse = new CommonResponse();
        List<String> validationList;

        try {
            // Validate the input data
//            validationList = this.bookingValidation(bookingDto);

//            if (!validationList.isEmpty()) {
//                commonResponse.setErrorMessages(validationList);
//                commonResponse.setCommonMessage("Booking update failed.");
//                return commonResponse;
//            }

            // Check if the booking exists
            Optional<BookingEntity> optionalBookingEntity = bookingRepository.findById(bookingDto.getId());
            if (optionalBookingEntity.isPresent()) {
                // Cast the DTO to an entity and update
                BookingEntity updatedBookingEntity = DtoToEntityCast.castBookingDtoInToBookingEntity(bookingDto);
                bookingRepository.save(updatedBookingEntity);
                commonResponse.setPayload(Collections.singletonList(updatedBookingEntity));
                commonResponse.setCommonMessage("Booking updated successfully.");
                commonResponse.setStatus(true);
            } else {
                commonResponse.setCommonMessage("Booking not found.");
                commonResponse.setStatus(false);
            }
        } catch (Exception e) {
            LOGGER.error("/**************Exception in Booking Service -> updateBooking(^)!", e);
            commonResponse.setCommonMessage("An error occurred while updating the booking.");
            commonResponse.setStatus(false);
        }
        return commonResponse;










    }


    @Override
    public CommonResponse deleteBooking(Long bookingId) {
        CommonResponse commonResponse = new CommonResponse();
        List<String> validationList = this.existingBookingValidation(bookingId);

        try {
                if (!validationList.isEmpty()) {
                    commonResponse.setErrorMessages(validationList);
                    commonResponse.setCommonMessage("Booking deletion failed.");
                    return commonResponse;
                }

            Optional<BookingEntity> optionalBookingEntity = bookingRepository.findById(bookingId);


            if (optionalBookingEntity.isPresent()) {
                BookingEntity bookingEntity = optionalBookingEntity.get();
                // Perform deletion logic, e.g., setting status to DELETE
                // or directly deleting from the repository
                // Here, let's assume we are setting the status to DELETE
                bookingEntity.setCommonStatus(CommonStatus.DELETE);
                bookingRepository.save(bookingEntity);
                commonResponse.setPayload(Collections.singletonList(bookingEntity));
                commonResponse.setCommonMessage("Booking deleted successfully.");
                commonResponse.setStatus(true);
            } else {
                commonResponse.setCommonMessage("Booking not found.");
                commonResponse.setStatus(false);
            }
        } catch (Exception e) {
            LOGGER.error("Exception in Booking Service -> deleteBooking!", e);
            commonResponse.setCommonMessage("An error occurred while deleting the booking.");
            commonResponse.setStatus(false);
        }
        return commonResponse;
    }

//    @Override
//    public CommonResponse getAllBooking() {
//        CommonResponse commonResponse = new CommonResponse();
//        List<BookingDto> bookingDtoList = new ArrayList<>();
//        List<BookingEntity> bookingEntities = new ArrayList<>();
//        System.out.println( "workes here");
//
//        try {
//            System.out.println( "workes in try block here");
////            Predicate<BookingEntity> filterOnStatus = booking -> booking.getCommonStatus() != CommonStatus.DELETE;
////            bookingDtoList = bookingRepository.findAll()
////                    .stream()
////                    .filter(filterOnStatus)
////                    .map(EntityToDtoCast::castBookingEntityToBookingDto)
////                    .collect(Collectors.toList());
//
//            for (BookingEntity booking : bookingEntities) {
//                if (booking.getCommonStatus() != CommonStatus.DELETE) {
//                    BookingDto bookingDto = EntityToDtoCast.castBookingEntityToBookingDto(booking);
//                    bookingDtoList.add(bookingDto);
//                }
//            }
//
//            System.out.println( "workes in after predicate block here");
//
//            System.out.println( "booking dto list length is"+bookingDtoList.size());
//            for (BookingDto bookingDTO : bookingDtoList) {
//                System.out.println(bookingDTO.toString());
//            }
//
//
//
//            if (bookingDtoList.isEmpty()) {
//                commonResponse.setCommonMessage("Booking list is empty.");
//                return commonResponse;
//            }
//
//            commonResponse.setPayload(Collections.singletonList(bookingDtoList));
//            commonResponse.setCommonMessage("Booking list retrieved successfully.");
//            commonResponse.setStatus(true);
//        } catch (Exception e) {
//            LOGGER.error("Exception in Booking Service -> getAllBooking!", e);
//            commonResponse.setCommonMessage("An error occurred while retrieving the booking list.");
//            commonResponse.setStatus(false);
//        }
//        return commonResponse;
//    }





//    public CommonResponse getAllBooking() {
//        CommonResponse commonResponse = new CommonResponse();
//        List<BookingDto> bookingDTOList = new ArrayList<>();
//
//        try {
//            Predicate<BookingEntity> filterOnStatus = company -> company.getCommonStatus() != CommonStatus.DELETE;
//            bookingDTOList = bookingRepository.findAll()
//                    .stream()
//                    .filter(filterOnStatus)
//                    .map(EntityToDtoCast::castBookingEntityToBookingDto)
//                    .collect(Collectors.toList());
//
//            if (bookingDTOList.isEmpty()) {
//                commonResponse.setCommonMessage("Booking list is empty.");
//                commonResponse.setStatus(false);
//                return commonResponse;
//            }
//
//            commonResponse.setPayload(Collections.singletonList(bookingDTOList)); // Not using singletonList as it should be a list of DTOs
//            commonResponse.setCommonMessage("Booking list retrieved successfully.");
//            commonResponse.setStatus(true);
//        } catch (Exception e) {
//            LOGGER.error("Exception in Booking Service -> getAllBookings!", e);
//            commonResponse.setCommonMessage("An error occurred while retrieving the booking list.");
//            commonResponse.setStatus(false);
//        }
//        return commonResponse;
//    }


    @Override
    public CommonResponse getAllBooking() {
        CommonResponse commonResponse = new CommonResponse();
        List<BookingDto> companyDTOList = new ArrayList<>();

        try {
//            Predicate<BookingEntity> filterOnStatus = company -> company.getCommonStatus() != CommonStatus.DELETE;
//            companyDTOList = bookingRepository.findAll()
//                    .stream()
//                    .filter(filterOnStatus)
//                    .map(EntityToDtoCast::castBookingEntityToBookingDto)
//                    .collect(Collectors.toList());
//
//            if (companyDTOList.isEmpty()) {
//                commonResponse.setCommonMessage("Company list is empty.");
//                return commonResponse;
            List<BookingEntity>bookingEntities = new ArrayList<>();

            List<BookingEntity>bookingEntitiesList = new ArrayList<>();

            bookingEntities=bookingRepository.findAll();

            for (BookingEntity booking : bookingEntities) {
                if (booking.getCommonStatus() != CommonStatus.DELETE) {

                    bookingEntitiesList.add(booking);
                }
            }

            commonResponse.setPayload(Collections.singletonList(bookingEntitiesList));
            commonResponse.setCommonMessage("Company list retrieved successfully.");
            commonResponse.setStatus(true);
        } catch (Exception e) {
            LOGGER.error("Exception in Company Service -> getAllCompanies!", e);
        }
        return commonResponse;
    }


    @Override
    public CommonResponse getBooking(Long bookingId) {
        CommonResponse commonResponse = new CommonResponse();
        List<String> validationList = new ArrayList<>();
        BookingEntity existingBooking;

        try {
            Long id = bookingId;
            validationList = this.existingBookingValidation(id);

            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                commonResponse.setCommonMessage("Get booking failed.");
                return commonResponse;
            }

            //existingBooking = bookingRepository.findById(id);
            Optional<BookingEntity> optionalBookingEntity = bookingRepository.findById(bookingId);
            if (optionalBookingEntity != null) {
                BookingEntity bookingEntity = optionalBookingEntity.get();
                BookingDto bookingDto = EntityToDtoCast.castBookingEntityToBookingDto(bookingEntity);
                commonResponse.setPayload(Collections.singletonList(bookingDto));
                commonResponse.setCommonMessage("Booking retrieved successfully.");
                commonResponse.setStatus(true);
            } else {
                commonResponse.setCommonMessage("Booking not found.");
            }
        } catch (Exception e) {
            LOGGER.error("Exception in Booking Service -> getBooking!", e);
            commonResponse.setCommonMessage("An error occurred while retrieving the booking.");
            commonResponse.setStatus(false);
        }
        return commonResponse;
    }


    private List<String> bookingValidation(BookingDto bookingDto) {
        List<String> validationList = new ArrayList<>();

        if (CommonValidation.stringNullValidation(bookingDto.getBook_date())) {
            validationList.add(CommonMsg.EMPTY_BOOK_DATE);
        }
        if (CommonValidation.stringNullValidation(bookingDto.getFrom_date())) {
            validationList.add(CommonMsg.EMPTY_FROM_DATE);
        }
        if (CommonValidation.stringNullValidation(String.valueOf(bookingDto.getTo_date()))) {
            validationList.add(CommonMsg.EMPTY_TO_DATE);
        }
        if (CommonValidation.stringNullValidation(String.valueOf(bookingDto.getRecieve_date()))) {
            validationList.add(CommonMsg.EMPTY_RECIEVE_DATE);
        }
        if (CommonValidation.stringNullValidation(bookingDto.getLocation())) {
            validationList.add(CommonMsg.EMPTY_LOCATION);
        }
        if (CommonValidation.commenStatusNullValidation(bookingDto.getCommonStatus())) {
            validationList.add(CommonMsg.EMPTY_COMMON_STATUS);
        }
        if (CommonValidation.stringNullValidation(bookingDto.getPurpose())) {
            validationList.add(CommonMsg.EMPTY_PURPOSE);
        }
        if (CommonValidation.LongNumberNullValidation(bookingDto.getUser_id())) {
            validationList.add(CommonMsg.EMPTY_ID);
        }

        if (CommonValidation.isValidText(bookingDto.getBook_date())) {
            validationList.add(CommonMsg.INVALID_book_date);
        }
        if (CommonValidation.isValidText(bookingDto.getFrom_date())) {
            validationList.add(CommonMsg.INVALID_from_date);
        }
        if (CommonValidation.isValidText(String.valueOf(bookingDto.getTo_date()))) {
            validationList.add(CommonMsg.INVALID_to_date);
        }
        if (CommonValidation.isValidText(String.valueOf(bookingDto.getRecieve_date()))) {
            validationList.add(CommonMsg.INVALID_recieve_date);
        }
        if (CommonValidation.isValidText(bookingDto.getLocation())) {
            validationList.add(CommonMsg.INVALID_location);
        }
        if (CommonValidation.isValidText(bookingDto.getPurpose())) {
            validationList.add(CommonMsg.INVALID_purpose);
        }
        if (CommonValidation.isValidLongNumber(bookingDto.getUser_id())) {
            validationList.add(CommonMsg.INVALID_ID);
        }

        return validationList;
    }

    private List<String> existingBookingValidation(Long bookingId) {
        List<String> validationList = new ArrayList<>();

        // Validate bookingId
        if (bookingId == null || bookingId <= 0) {
            validationList.add(CommonMsg.INVALID_ID);
            return validationList; // Return early since the bookingId is invalid
        }

        // Check if booking exists
        Optional<BookingEntity> optionalBookingEntity = bookingRepository.findById(bookingId);
        BookingEntity bookingEntity = optionalBookingEntity.get();

        if (!optionalBookingEntity.isPresent()) {
            validationList.add(CommonMsg.NOT_EXISTING_ID);
        }
        if(bookingEntity.getCommonStatus()==CommonStatus.INACTIVE || bookingEntity.getCommonStatus()==CommonStatus.DELETE){
            validationList.add(CommonMsg.ALLREADY_DELETED);
        }

        return validationList;
    }

}
