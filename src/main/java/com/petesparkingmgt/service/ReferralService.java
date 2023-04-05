package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.referrals.ReferralDAO;
import com.petesparkingmgt.dao.referrals.ReferralUserDAO;
import com.petesparkingmgt.dto.referrals.ReferralDTO;
import com.petesparkingmgt.dto.referrals.ReferralUserDTO;
import com.petesparkingmgt.objects.users.ReferralWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferralService {

    @Autowired
    public ReferralDAO dao;

    @Autowired
    public ReferralUserDAO userDAO;


    public boolean isValidReferralCode(String code) {
        return dao.existsByCode(code);
    }

    public boolean usedReferralCodeWhenCreatingAccount(long userid) {
        return userDAO.existsByReferredId(userid);
    }

    public ReferralUserDTO getReferralUserDTOFor(long userid) {
        if (usedReferralCodeWhenCreatingAccount(userid)) {

            return userDAO.getByReferredId(userid);
        } else return null;
    }

    public boolean createdAReferralCode(long userid) {
        return dao.existsByUserId(userid);
    }

    public ReferralWrapper getReferralThatUserUsedWhenRegistering(long userid){
        if (usedReferralCodeWhenCreatingAccount(userid)) {
            ReferralWrapper wrapper = new ReferralWrapper();
            ReferralUserDTO referralUserDTO = getReferralUserDTOFor(userid);
            wrapper.setUser(referralUserDTO);
            ReferralDTO referralDTO = dao.getById(referralUserDTO.getReferralId());
            wrapper.setReferral(referralDTO);

            return wrapper;
        }
        return null;
    }
    public void saveReferralUserDTO(ReferralUserDTO user) {
        userDAO.save(user);
    }
    public ReferralDTO getReferralForCode(String code) {
        return dao.getReferralDTOByCode(code);
    }

}
