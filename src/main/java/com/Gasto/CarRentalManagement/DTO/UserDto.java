package com.Gasto.CarRentalManagement.DTO;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long user_id;
    private String driving_no;
    private String email;
    private String password;
    private String tel_no;
    private String userName;
    private String f_name;
    private String l_name;
    private String age_gap;
    private CommonStatus common_status;
    private String gender;
}
