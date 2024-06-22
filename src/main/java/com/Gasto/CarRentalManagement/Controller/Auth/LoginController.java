package com.Gasto.CarRentalManagement.Controller.Auth;

import com.Gasto.CarRentalManagement.DTO.Auth.LoginDto;
import com.Gasto.CarRentalManagement.Services.Auth.LoginService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="api/v1/userLogin")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }


    @GetMapping("")
    public String LoginControllerHome (){
        return "Welcome to Login Management Controller";
    }


    @PostMapping(path = "/validUser")
    public CommonResponse validUser(@RequestBody LoginDto loginDto) {
        return loginService.validUser(loginDto);
    }
}
