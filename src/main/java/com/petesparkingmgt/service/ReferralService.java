package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.referrals.ReferralDAO;
import com.petesparkingmgt.dao.referrals.ReferralUserDAO;
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

}
