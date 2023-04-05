package com.petesparkingmgt.dao.referrals;

import com.petesparkingmgt.dto.parking.BookingDTO;
import com.petesparkingmgt.dto.referrals.ReferralDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferralDAO extends JpaRepository<ReferralDTO, Long> {

    public List<ReferralDTO> getReferralDTOSByUserId(long userid);
}
