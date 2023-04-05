package com.petesparkingmgt.dao.referrals;

import com.petesparkingmgt.dto.parking.BookingDTO;
import com.petesparkingmgt.dto.referrals.ReferralUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferralUserDAO extends JpaRepository<ReferralUserDTO, Long> {
}
