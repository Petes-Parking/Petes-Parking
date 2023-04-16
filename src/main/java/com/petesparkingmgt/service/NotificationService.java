package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.parking.ParkingDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dao.users.NotificationDAO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    public void markAsRead(long notificationId) {
        NotificationDTO notification = dao.getById(notificationId);
        notification.setStatus(true);
        update(notification);


    }

    public String generateNotificationUrl(long notificationId, String targetPage) {
        return String.format("/notification/read?id=%d&page=%s", notificationId, targetPage);
    }

    public void update(NotificationDTO toUpdate) {
        NotificationDTO notification = dao.getById(toUpdate.getId());
        notification.setStatus(toUpdate.isStatus());
        dao.save(notification);

    }

    public static void addNotifications(Model model, NotificationService notificationService, long userId){
        List<NotificationDTO> allNotifications = notificationService.getUnreadNotificationsFor(userId);
        List<String> notificationUrls = allNotifications.stream()
                .map(notification -> notificationService.generateNotificationUrl(notification.getId(), notification.getPageToGoTo()))
                .collect(Collectors.toList());
        notificationUrls.forEach(System.out::println);

        model.addAttribute("notifications", allNotifications);
        model.addAttribute("notificationUrls", notificationUrls);

    }
}

