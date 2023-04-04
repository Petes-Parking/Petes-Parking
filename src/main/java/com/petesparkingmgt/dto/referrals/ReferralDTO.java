package com.petesparkingmgt.dto.referrals;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="referrals")
@Getter
@Setter
public class ReferralDTO extends BaseDTO {





    @Column(name = "userId", length = 755)
    private long userId;

    @Column(name = "code")
    private String code; // 0 is pending, 1 is accepted

    @Column(name = "num_of_uses")
    private int uses;




}
