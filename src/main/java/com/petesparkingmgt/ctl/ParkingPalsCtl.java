package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.FriendDAO;
import com.petesparkingmgt.dto.FriendDTO;
import com.petesparkingmgt.form.AddFriendForm;
import com.petesparkingmgt.form.FriendResponseForm;
import com.petesparkingmgt.form.ViewFriendForm;
import com.petesparkingmgt.objects.users.User;
import com.petesparkingmgt.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;

import javax.servlet.http.HttpSession;
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

        List<FriendDTO> friends = service.getConfirmedUsersFor(user.getEmail());
        model.addAttribute("friends", friends); // can be empty

        List<FriendDTO> outgoingRequests = service.getOutgoingRequestsFor(user.getEmail());
        model.addAttribute("outgoingRequests", outgoingRequests); // can be empty

        return "parkingpals";

    }

    @PostMapping("/friendInvite")
    public String invite(@ModelAttribute("addFriendForm") AddFriendForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "error";

        List<UserDTO> users = userDAO.findAll();
        model.addAttribute("users", users);

        List<FriendDTO> requests = service.getInvitesForUser(user.getEmail());
        model.addAttribute("requests", requests); // can be empty

        List<FriendDTO> friends = service.getConfirmedUsersFor(user.getEmail());
        model.addAttribute("friends", friends); // can be empty

        List<FriendDTO> outgoingRequests = service.getOutgoingRequestsFor(user.getEmail());
        model.addAttribute("outgoingRequests", outgoingRequests); // can be empty

        UserDTO toInvite = userDAO.findByEmail(form.getEmail());

        // Check if a friend request has already been sent
        FriendDTO existingRequest = dao.getFriendDTOByRecipientEmailAndStatusEquals(toInvite.getEmail(), 0);
        if (existingRequest != null) {
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

        return "parkingpals";
    }

    @PostMapping("/friendInviteResponse")
    public String inviteResponse(@ModelAttribute("friendResponseForm") FriendResponseForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "error";

        List<UserDTO> users = userDAO.findAll();
        model.addAttribute("users", users);

        List<FriendDTO> requests = service.getInvitesForUser(user.getEmail());
        model.addAttribute("requests", requests); // can be empty

        List<FriendDTO> friends = service.getConfirmedUsersFor(user.getEmail());
        model.addAttribute("friends", friends); // can be empty

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

        return "parkingpals";
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