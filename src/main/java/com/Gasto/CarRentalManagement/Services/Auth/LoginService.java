package com.Gasto.CarRentalManagement.Services.Auth;

import com.Gasto.CarRentalManagement.DTO.Auth.LoginDto;
import com.Gasto.CarRentalManagement.Util.CommonResponse;

public interface LoginService {
    CommonResponse validUser(LoginDto loginDto);
}
