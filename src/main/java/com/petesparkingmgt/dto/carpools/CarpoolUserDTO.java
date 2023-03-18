package com.petesparkingmgt.dto.carpools;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="carpool_users")
@Getter
@Setter
public class CarpoolUserDTO extends BaseDTO {

//    @EmbeddedId
//    @Column(name ="id", length = 255)
//    private CarpoolUserKey id;

    @Column(name = "carpoolId", length = 755)
    private long carpoolId;

    @Column(name = "userId", length = 755)
    private long userId;




}
