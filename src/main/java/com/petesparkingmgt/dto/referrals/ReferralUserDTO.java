package com.petesparkingmgt.dto.referrals;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="referral_users")
@Getter
@Setter
public class ReferralUserDTO extends BaseDTO {

    @Column(name = "referralId", length = 755)
    private long referralId;

    @Column(name = "referred_user", length = 755)
    private long referredId;
}
