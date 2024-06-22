package com.Gasto.CarRentalManagement.Services;

        import com.Gasto.CarRentalManagement.DTO.UserDto;
        import com.Gasto.CarRentalManagement.Util.CommonResponse;

public interface UserService {
    CommonResponse saveUser(UserDto userDto);
    CommonResponse updateUser(UserDto userDto);
    CommonResponse deleteUser(Long userId);
    CommonResponse getAllUsers();
    CommonResponse getUser(Long userId);
}
