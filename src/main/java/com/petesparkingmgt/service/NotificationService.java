package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.parking.ParkingDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dao.users.NotificationDAO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    public NotificationDAO dao;


    public void addIncomingFriendNotification(long receiverId, String senderName) {
        NotificationDTO incomingReqNotif = new NotificationDTO();
        incomingReqNotif.setUserId(receiverId);
        incomingReqNotif.setStatus(false);
        incomingReqNotif.setTimeStamp(LocalDateTime.now().toString());
        incomingReqNotif.setMessage("You have an incoming friend request from " + senderName + "!");
        incomingReqNotif.setPageToGoTo("parkingpals");
        dao.save(incomingReqNotif);
    }

    public List<NotificationDTO> getUnreadNotificationsFor(long userid){
        return dao.getNotificationDTOSByUserIdAndStatusEquals(userid, false);
    }


}
