package com.Gasto.CarRentalManagement.Entity;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @Column(name="id")
    private Long user_id;

    @Column(name="driving_no")
    private String driving_no;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="tele_no")
    private String tel_no;

    @Column(name="userName")
    private String userName;

    @Column(name="f_name")
    private String f_name;

    @Column(name="l_name")
    private String l_name;

    @Column(name="age_gap")
    private String age_gap;

    @Column(name="common_states")
    private CommonStatus common_status;

    @Column(name="gender")
    private String gender;

}
