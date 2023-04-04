package com.petesparkingmgt.dao.users;

import com.petesparkingmgt.dto.user.PendingUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingUserDAO extends JpaRepository<PendingUserDTO, Long> {

    public PendingUserDTO findByEmailAndPassword(String email, String password);
    public PendingUserDTO findById(long id);

    public PendingUserDTO findByEmail(String email);



}
