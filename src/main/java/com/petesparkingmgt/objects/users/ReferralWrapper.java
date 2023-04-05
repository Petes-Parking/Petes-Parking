package com.petesparkingmgt.objects.users;

import com.petesparkingmgt.dto.referrals.ReferralDTO;
import com.petesparkingmgt.dto.referrals.ReferralUserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReferralWrapper {

    private ReferralDTO referral; // referral as stored in database consisting of the code and creator id

    private ReferralUserDTO user; // person who used the referral consisting of referral.getid

    public ReferralWrapper(ReferralDTO referral, ReferralUserDTO user) {
        this.referral = referral;
        this.user = user;
    }

    public ReferralWrapper() {
    }
}
