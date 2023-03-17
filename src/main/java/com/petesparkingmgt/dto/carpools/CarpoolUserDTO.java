package com.petesparkingmgt.dto.carpools;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="carpool_users")
@Getter
@Setter
public class CarpoolUserDTO {

    @EmbeddedId
    @Column(name ="id", length = 255)
    private CarpoolUserKey id;

//    @Column(name = "carpool_id", length = 755)
//    private long carpoolId;
//
//    @Column(name = "user_id", length = 755)
//    private long userId;




}
