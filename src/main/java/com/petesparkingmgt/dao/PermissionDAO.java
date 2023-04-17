package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.PaymentRequestDTO;
import com.petesparkingmgt.dto.user.PermissionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDAO extends JpaRepository<PermissionDTO, Long> {

    public PermissionDTO findByUserID(long userid);
}
