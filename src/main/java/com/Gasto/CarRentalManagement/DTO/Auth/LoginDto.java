package com.Gasto.CarRentalManagement.DTO.Auth;
public class LoginDto {
    private String userName;
    private String password;
    public LoginDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public LoginDto() {
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "LoginDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
