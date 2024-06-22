package com.Gasto.CarRentalManagement.Util;

import com.Gasto.CarRentalManagement.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class JwtGenerator {

    private static String secret = "Nijaadh20#";
    private static Date date = new Date();
    public String GenerateJWT(UserEntity user){
        //Claims

        Claims claims = Jwts.claims()
                .setIssuer((user.getUser_id().toString()))
                .setIssuedAt(date);

        //JWT
        return Jwts.builder().setClaims(claims).compact();

    }
}
