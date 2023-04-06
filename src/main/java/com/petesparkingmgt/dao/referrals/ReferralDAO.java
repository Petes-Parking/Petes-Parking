package com.petesparkingmgt.dao.referrals;

import com.petesparkingmgt.dto.parking.BookingDTO;
import com.petesparkingmgt.dto.referrals.ReferralDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferralDAO extends JpaRepository<ReferralDTO, Long> {

    public ReferralDTO getReferralDTOByUserId(long userid);

    public ReferralDTO findByUserId(long userid);

    public boolean existsByUserId(long userid);

    public boolean existsByCode(String code);

    public ReferralDTO getById(long id);

    public ReferralDTO getReferralDTOByCode(String code);
}
