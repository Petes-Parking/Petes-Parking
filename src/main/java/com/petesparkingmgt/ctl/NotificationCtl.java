package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.users.NotificationDAO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class NotificationCtl {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/read")
    public ModelAndView readNotificationAndRedirect(@RequestParam("id") int notificationId, @RequestParam("page") String targetPage) {
        notificationService.markAsRead(notificationId);
        return new ModelAndView("redirect:/" + targetPage);
    }
}
