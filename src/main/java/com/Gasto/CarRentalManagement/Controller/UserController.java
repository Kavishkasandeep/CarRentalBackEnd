package com.Gasto.CarRentalManagement.Controller;
import com.Gasto.CarRentalManagement.DTO.UserDto;
import com.Gasto.CarRentalManagement.Services.UserService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/UserManagement")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("")
    public String userControllerHome() {
        return "Welcome to User Management Controller";
    }
    @PostMapping(path = "/saveUser")
    public CommonResponse saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping(path = "/updateUser")
    public CommonResponse updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public CommonResponse deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping(path = "/getAllUsers")
    public CommonResponse getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/getUser/{userId}")
    public CommonResponse getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
