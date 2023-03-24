package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.PendingUserDTO;
import com.petesparkingmgt.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PendingUserDAO extends JpaRepository<PendingUserDTO, Long> {

    public PendingUserDTO findByEmailAndPassword(String email, String password);
    public PendingUserDTO findById(long id);

    public PendingUserDTO findByEmail(String email);



}
