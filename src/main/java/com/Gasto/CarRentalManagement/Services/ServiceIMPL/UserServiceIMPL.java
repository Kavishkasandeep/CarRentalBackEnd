package com.Gasto.CarRentalManagement.Services.ServiceIMPL;

import com.Gasto.CarRentalManagement.Const.CommonMsg;
import com.Gasto.CarRentalManagement.Const.CommonStatus;
import com.Gasto.CarRentalManagement.DTO.UserDto;
import com.Gasto.CarRentalManagement.Entity.UserEntity;
import com.Gasto.CarRentalManagement.Repository.BookingRepository;
import com.Gasto.CarRentalManagement.Repository.UserRepository;
import com.Gasto.CarRentalManagement.Services.UserService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import com.Gasto.CarRentalManagement.Util.DtoToEntityCast;
import com.Gasto.CarRentalManagement.Util.EntityToDtoCast;
//import org.hibernate.annotations.common.util.impl.LoggerFactory;
//import org.slf4j.LoggerFactory;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceIMPL(UserRepository   userRepository) {
        this.userRepository = userRepository;
    }

//    private static final System.Logger LOGGER = LoggerFactory.getLogger(UserServiceIMPL.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceIMPL.class);

    @Override
    public CommonResponse saveUser(UserDto userDto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            UserEntity userEntity = DtoToEntityCast.castUserDtoInToUserEntity(userDto);
            userRepository.save(userEntity);
            commonResponse.setPayload(Collections.singletonList(userEntity));
            commonResponse.setCommonMessage("User saved successfully.");
            commonResponse.setStatus(true);
        } catch (Exception e) {
            LOGGER.error("Exception in User Service -> saveUser!", e);
            commonResponse.setCommonMessage("An error occurred while saving the user.");
            commonResponse.setStatus(false);
        }
        return commonResponse;
    }

    @Override
    public CommonResponse updateUser(UserDto userDto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            UserEntity userEntity = DtoToEntityCast.castUserDtoInToUserEntity(userDto);
            userRepository.save(userEntity);
            commonResponse.setPayload(Collections.singletonList(userEntity));
            commonResponse.setCommonMessage("User updated successfully.");
            commonResponse.setStatus(true);
        } catch (Exception e) {
            LOGGER.error("Exception in User Service -> updateUser!", e);
            commonResponse.setCommonMessage("An error occurred while updating the user.");
            commonResponse.setStatus(false);
        }
        return commonResponse;
    }

    @Override
    public CommonResponse deleteUser(Long userId) {
        CommonResponse commonResponse = new CommonResponse();
        List<String> validationList = this.existingUserValidation(userId);

        try {
            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                commonResponse.setCommonMessage("User deletion failed.");
                return commonResponse;
            }

            Optional<UserEntity> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
                user.setCommon_status(CommonStatus.DELETE);
                userRepository.save(user);
                commonResponse.setPayload(Collections.singletonList(user));
                commonResponse.setCommonMessage("User deleted successfully.");
                commonResponse.setStatus(true);
            }
        } catch (Exception e) {
            LOGGER.error("Exception in User Service -> deleteUser!", e);
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getAllUsers() {
        CommonResponse commonResponse = new CommonResponse();
        List<UserDto> userDtoList = new ArrayList<>();

        try {
            Predicate<UserEntity> filterOnStatus = user -> user.getCommon_status() != CommonStatus.DELETE;
            userDtoList = userRepository.findAll()
                    .stream()
                    .filter(filterOnStatus)
                    .map(EntityToDtoCast::castUserInToUserDto)
                    .collect(Collectors.toList());

            if (userDtoList.isEmpty()) {
                commonResponse.setCommonMessage("User list is empty.");
                return commonResponse;
            }

            commonResponse.setPayload(Collections.singletonList(userDtoList));
            commonResponse.setCommonMessage("User list retrieved successfully.");
            commonResponse.setStatus(true);
        } catch (Exception e) {
            LOGGER.error("Exception in User Service -> getAllUsers!", e);
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getUser(Long userId) {
        CommonResponse commonResponse = new CommonResponse();
        List<String> validationList = new ArrayList<>();
        UserEntity existingUser;

        try {
            validationList = this.existingUserValidation(userId);

            if (!validationList.isEmpty()) {
                commonResponse.setErrorMessages(validationList);
                commonResponse.setCommonMessage("Get user failed.");
                return commonResponse;
            }

            existingUser = userRepository.findById(userId).orElse(null);
            if (existingUser != null) {
                UserDto userDto = EntityToDtoCast.castUserInToUserDto(existingUser);
                commonResponse.setPayload(Collections.singletonList(userDto));
                commonResponse.setCommonMessage("User retrieved successfully.");
                commonResponse.setStatus(true);
            } else {
                commonResponse.setCommonMessage("User not found.");
            }
        } catch (Exception e) {
            LOGGER.error("Exception in User Service -> getUser!", e);
            commonResponse.setCommonMessage("An error occurred while retrieving the user.");
            commonResponse.setStatus(false);
        }
        return commonResponse;
    }

    private List<String> existingUserValidation(Long id) {
        List<String> validationList = new ArrayList<>();

        // Validate ID
        if (id == null || id <= 0) {
            validationList.add(CommonMsg.INVALID_ID);
            return validationList; // Return early since the ID is invalid
        }

        // Check if user exists
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            validationList.add(CommonMsg.NOT_EXISTING_ID);
            return validationList; // Return early since the user doesn't exist
        }

        // Further check on the status
        UserEntity existingUser = optionalUser.get();
        if (existingUser.getCommon_status() == CommonStatus.DELETE) {
            validationList.add(CommonMsg.NOT_EXISTING_ID);
        }

        return validationList;
    }

}
