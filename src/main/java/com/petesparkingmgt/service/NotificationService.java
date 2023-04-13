package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.parking.ParkingDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dao.users.NotificationDAO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    public NotificationDAO dao;


    public void addNotificationFor(long receiverId, String message, String page) {
        NotificationDTO incomingReqNotif = new NotificationDTO();
        incomingReqNotif.setUserId(receiverId);
        incomingReqNotif.setStatus(false);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        incomingReqNotif.setTimeStamp(formattedDateTime);
        incomingReqNotif.setMessage(message);
        incomingReqNotif.setPageToGoTo(page);
        dao.save(incomingReqNotif);
    }

    public List<NotificationDTO> getUnreadNotificationsFor(long userid){
        return dao.getNotificationDTOSByUserIdAndStatusEquals(userid, false);
    }


}
