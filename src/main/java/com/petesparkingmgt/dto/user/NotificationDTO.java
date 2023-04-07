package com.petesparkingmgt.dto.user;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="notifications")
@Getter
@Setter
public class NotificationDTO extends BaseDTO {

    @Column(name = "user_id", length = 755)
    private long userId;

    @Column(name = "notification_message", length = 755)
    private String message;

    @Column(name = "time_stamp", length = 755)
    private String timeStamp;

    @Column(name = "is_read", length = 755)
    private boolean isRead;

    @Column(name = "pageToGo", length = 755)
    private String pageToGoTo;

    @Override
    public String toString() {
        return "NotificationDTO [userid=" + userId + " message=" + message + " timeStamp=" + timeStamp + " isRead=" +isRead;
    }
}
