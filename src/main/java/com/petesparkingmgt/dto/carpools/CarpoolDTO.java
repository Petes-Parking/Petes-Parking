package com.petesparkingmgt.dto.carpools;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="carpools")
@Getter
@Setter
public class CarpoolDTO extends BaseDTO {


    /**
     * @author maxfuligni
     *
     * Current design is a one-to-many relationship with CarpoolUserDTO.
     * In table carpools, every single carpool will contain one entry per carpool denoting who is the
     * 'leader'. Only leaders can reserve a spot, other users in the carpool will not be able to book a spot.
     *
     * In table carpool_users, every single user currently in a carpool will contain an entry. An example of this can look like
     * the below.
     *
     * ------Carpools ---
     * id: 1
     * leader_id: 1
     *
     *
     *
     * ---- Carpool_users-----
     * carpool_id: 1
     * userid: 2
     *
     * carpool_id: 1
     * userid: 3
     *
     * The above is a carpool with a leader with ID 1, and members with IDs 2 and 3.
     * Hopefully this illustrates what I am trying to achieve.
     *
     */


    @Column(name = "leader_id")
    private long leaderId;

}
