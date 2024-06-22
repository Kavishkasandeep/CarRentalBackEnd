package com.Gasto.CarRentalManagement.Repository;

import com.Gasto.CarRentalManagement.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Long>{

    UserEntity findByPassword( String password);

    UserEntity findByUserNameAndPassword(String UserName,String password);
}
