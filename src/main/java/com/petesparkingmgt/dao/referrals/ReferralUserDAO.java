package com.petesparkingmgt.dao.referrals;

import com.petesparkingmgt.dto.parking.BookingDTO;
import com.petesparkingmgt.dto.referrals.ReferralUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferralUserDAO extends JpaRepository<ReferralUserDTO, Long> {

    public boolean existsByReferredId(long userid);

    public ReferralUserDTO getByReferralId(long userid);


    public ReferralUserDTO getByReferredId(long userid);

    public List<ReferralUserDTO> findAllByReferralId(long referralId);


}
