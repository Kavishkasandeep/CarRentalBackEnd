package com.Gasto.CarRentalManagement.Services.Auth;

import com.Gasto.CarRentalManagement.Const.CommonMsg;
import com.Gasto.CarRentalManagement.DTO.Auth.LoginDto;
import com.Gasto.CarRentalManagement.Entity.UserEntity;
import com.Gasto.CarRentalManagement.Repository.UserRepository;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import com.Gasto.CarRentalManagement.Util.CommonValidation;
import com.Gasto.CarRentalManagement.Util.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LoginServiceIMPL implements LoginService {
    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator=new JwtGenerator();

    @Autowired
    public LoginServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CommonResponse validUser(LoginDto loginDto) {
        CommonResponse commonResponse = new CommonResponse();

        List<String>validationList = new ArrayList<>();

        UserEntity exsistinguser = new UserEntity();

        validationList=this.userCridentialValidation(loginDto);
        System.out.println(loginDto.toString());
        exsistinguser = this.findExsistingUser(loginDto);
        System.out.println(exsistinguser.toString());

//        if(validationList!=null){
//            commonResponse.setStatus(false);
//            commonResponse.setCommonMessage("Invalid UserName or Password");
//            commonResponse.setErrorMessages(validationList);
//            return commonResponse;
//        }

        if(exsistinguser == null){
            commonResponse.setStatus(false);
            commonResponse.setCommonMessage("Cridentials not match for any Users");
            return commonResponse;
        }

        String token=jwtGenerator.GenerateJWT(exsistinguser);

        commonResponse.setStatus(true);
        commonResponse.setCommonMessage("Token Granted .Login credentials matched");
        commonResponse.setPayload(Collections.singletonList(exsistinguser));
        commonResponse.setToken(token);

        return commonResponse;
    }

    public UserEntity findExsistingUser(LoginDto loginDto) {


        try{
            UserEntity userEntity= new UserEntity();
            userEntity=userRepository.findByUserNameAndPassword(loginDto.getUserName(),loginDto.getPassword());
            return userEntity;
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    private List<String> userCridentialValidation(LoginDto loginDto) {
        List<String>validationList = new ArrayList<>();

        if(CommonValidation.stringNullValidation(loginDto.getUserName())){
            validationList.add(CommonMsg.EMPTY_USER_NAME);
        }
        if(CommonValidation.stringNullValidation((loginDto.getPassword()))){
            validationList.add(CommonMsg.EMPTY_PASSWORD);
        }
        if(CommonValidation.isValidUserName(loginDto.getUserName())){
            validationList.add(CommonMsg.INVALID_USER_NAME);
        }
        if(CommonValidation.isValidPassword((loginDto.getPassword()))){
            validationList.add(CommonMsg.INVALID_PASSWORD);
        }

        return validationList;
    }
}
