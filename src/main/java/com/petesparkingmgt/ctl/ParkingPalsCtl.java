package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dao.users.FriendDAO;
import com.petesparkingmgt.dto.user.EmailPreferencesDTO;
import com.petesparkingmgt.dto.user.FriendDTO;
import com.petesparkingmgt.form.AddFriendForm;
import com.petesparkingmgt.form.FriendResponseForm;
import com.petesparkingmgt.service.EmailService;
import com.petesparkingmgt.service.FriendService;
import com.petesparkingmgt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.UserDTO;

import javax.servlet.http.HttpSession;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.lang.*;

@Controller
public class ParkingPalsCtl {

    @Autowired
    public FriendService service;

    @Autowired
    public FriendDAO dao;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public EmailPreferencesDAO emailDAO;

    @Autowired
    public NotificationService notificationService;

    @GetMapping("/parkingpals")
    public String parkingPals(Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }

        List<UserDTO> users = userDAO.findAll();
        model.addAttribute("users", users);

        List<FriendDTO> requests = service.getInvitesForUser(user.getEmail());
        model.addAttribute("requests", requests); // can be empty

        System.out.println("In requests---");
        requests.forEach(System.out::println);
        System.out.println("-");

        List<FriendDTO> friends = service.getConfirmedUsersFor(user.getEmail());
        model.addAttribute("friends", friends); // can be empty

        List<FriendDTO> outgoingRequests = service.getOutgoingRequestsFor(user.getEmail());
        model.addAttribute("outgoingRequests", outgoingRequests); // can be empty

        return "parkingpals";

    }

    @PostMapping("/friendInvite")
    public String invite(@ModelAttribute("addFriendForm") AddFriendForm form, Model model, HttpSession session) throws NoSuchAlgorithmException, KeyManagementException {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "error";

        List<UserDTO> users = userDAO.findAll();
        model.addAttribute("users", users);

        List<FriendDTO> requests = service.getInvitesForUser(user.getEmail());
        model.addAttribute("requests", requests); // can be empty



        List<FriendDTO> outgoingRequests = service.getOutgoingRequestsFor(user.getEmail());
        model.addAttribute("outgoingRequests", outgoingRequests); // can be empty

        UserDTO toInvite = userDAO.findByEmail(form.getEmail());

        // Check if a friend request has already been sent
        FriendDTO existingRequest = dao.getFriendDTOBySenderEmailAndRecipientEmailAndStatusEquals(user.getEmail(), toInvite.getEmail(), 0);
        if (existingRequest != null) {
            System.out.println("test");
            return "parkingpals";
        }

        FriendDTO dto = new FriendDTO();
        dto.setSenderEmail(user.getEmail());
        dto.setRecipientEmail(toInvite.getEmail());
        dto.setStatus(0);
        dto.setSenderFirstName(user.getFirstName());
        dto.setSenderLastName(user.getLastName());
        dto.setRecipientFirstName(toInvite.getFirstName());
        dto.setRecipientLastName(toInvite.getLastName());
        dao.save(dto);

        notificationService.addIncomingFriendNotification(toInvite.getId(), user.getFirstName() + " " + user.getLastName());



        EmailPreferencesDTO emailDTO = emailDAO.getByUserID(toInvite.getId());

        if (emailDTO != null) {
            int emailPref = emailDTO.getParkingPalPref();
            System.out.print("Email preference: ");
            System.out.println(emailPref);
            if (emailPref == 1) {
                EmailService emailService = new EmailService();
                emailService.createParkingPalRequestEmail(dto);
            } else {
                System.out.println("No email sent since pref is off");
            }
        }



        List<FriendDTO> friends = service.getConfirmedUsersFor(user.getEmail());
        model.addAttribute("friends", friends); // can be empty

        return "redirect:/parkingpals";
    }

    @PostMapping("/friendInviteResponse")
    public String inviteResponse(@ModelAttribute("friendResponseForm") FriendResponseForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "error";

        List<UserDTO> users = userDAO.findAll();
        model.addAttribute("users", users);

        List<FriendDTO> requests = service.getInvitesForUser(user.getEmail());
        model.addAttribute("requests", requests); // can be empty



        List<FriendDTO> outgoingRequests = service.getOutgoingRequestsFor(user.getEmail());
        model.addAttribute("outgoingRequests", outgoingRequests); // can be empty

        String action = form.getAction();
        String sender = form.getEmail();

        if (action.equalsIgnoreCase("accept")) {
            service.acceptInvite(user.getEmail(), sender);
        } else if (action.equalsIgnoreCase("reject")) {
            service.rejectInvite(user.getEmail(), sender);
        } else {
            // should never reach here but we'll return error anyways
            return "error";
        }

        List<FriendDTO> friends = service.getConfirmedUsersFor(user.getEmail());
        model.addAttribute("friends", friends); // can be empty

        return "redirect:/parkingpals";
    }

//    @GetMapping("/viewFriend")
//    public String friendPage(@ModelAttribute("viewFriendForm") ViewFriendForm form, Model model, HttpSession session) {
//
//        UserDTO user = (UserDTO) session.getAttribute("user");
//        if (user == null) return "error";
//
//        UserDTO myFriend = userDAO.findByEmail(form.getEmail());
//        model.addAttribute("myFriend", myFriend);
//
//        return "friendpage";
//    }

}