package com.petesparkingmgt.dto.user;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="email_preferences")
@Getter
@Setter
public class EmailPreferencesDTO extends BaseDTO {

    @Column(name = "user_id")
    private long userID;

    @Column(name = "parking_pal_pref")
    private int parkingPalPref;

    @Column(name = "report_pref")
    private int reportPref;

    @Column(name = "expiration_pref")
    private int expirationPref;

    @Column(name = "timer")
    private long timer;
}
