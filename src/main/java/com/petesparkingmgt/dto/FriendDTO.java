package com.petesparkingmgt.dto;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="friends")
@Getter
@Setter

public class FriendDTO extends BaseDTO {

    @Column(name = "senderEmail")
    private String senderEmail;
    @Column(name = "recipientEmail")
    private String recipientEmail;
    @Column(name = "status")
    private long status; // 0 is pending, 1 is accepted

    @Column(name = "senderFirstName")
    private String senderFirstName;

    @Column(name = "senderLastName")
    private String senderLastName;

    @Column(name = "recipientFirstName")
    private String recipientFirstName;

    @Column(name = "recipientLastName")
    private String recipientLastName;

    public String getSenderEmail() {
        return senderEmail;
    }
    public String getRecipientEmail() { return recipientEmail; }

}
