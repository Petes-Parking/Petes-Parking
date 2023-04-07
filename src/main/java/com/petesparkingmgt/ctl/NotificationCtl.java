package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.users.NotificationDAO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class NotificationCtl {

    @Autowired
    private NotificationDAO dao;

    @GetMapping("/notifications")
    public String showNotifications(Model model, HttpSession seession) {
       UserDTO user = (UserDTO) seession.getAttribute("user");
        List<NotificationDTO> allNotifications = dao.getNotificationDTOSByUserId(user.getId());
        allNotifications.forEach(System.out::println);
        model.addAttribute("notifications", allNotifications);
        return "notifications";
    }

}
