package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.carpools.CarpoolDAO;
import com.petesparkingmgt.dao.carpools.CarpoolUsersDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.form.CarpoolAddMemberForm;
import com.petesparkingmgt.form.CarpoolResponseForm;
import com.petesparkingmgt.service.CarpoolService;
import com.petesparkingmgt.service.CarpoolUsersService;
import com.petesparkingmgt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarpoolUsersCtl {

    @Autowired
    public CarpoolUsersService service;

    @Autowired
    public CarpoolService carpoolService;

    @Autowired
    public CarpoolUsersDAO dao;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public CarpoolDAO carpoolDAO;

    @Autowired
    public NotificationService notificationService;



    @PostMapping("/carpoolInvite")
    public String createCarpool(@ModelAttribute("carInviteForm") CarpoolAddMemberForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "error";

        CarpoolDTO carpool = carpoolDAO.getCarpoolDTOByLeaderId(user.getId());
        UserDTO toInvite = userDAO.findByEmail(form.getEmail());
        model.addAttribute("hasCarpool", true);
        model.addAttribute("carpool", carpool);
        model.addAttribute("carPoolName", carpool.getCarPoolName());
        model.addAttribute("isLeader", true);
        if (carpoolService.hasReservation(carpool.getId())) {
            model.addAttribute("hasReservation", true);
            model.addAttribute("reservation", carpoolService.getBookingFor(carpool.getId()));
        } else {
            model.addAttribute("hasReservation", false);
        }

        model.addAttribute("members", carpoolService.getNamesOfMembers(carpool.getId()));



        if (toInvite == null) {
            // not an existing user, tell user this
            model.addAttribute("errors", form.getEmail() + " is not a registered email!");
            model.addAttribute("carPoolName", carpool.getCarPoolName());



        } else {
            if (toInvite.getId() == user.getId()){
                // cannot invite themself
                model.addAttribute("errors", "You cannot invite yourself!");
                return "carpool";
            }
            model.addAttribute("messages", "You have invited " + toInvite.getFirstName() + " " + toInvite.getLastName() + "!");
            CarpoolUserDTO dto = new CarpoolUserDTO();
            dto.setCarpoolId(carpool.getId());
            dto.setUserId(toInvite.getId());
            dto.setStatus(0);
            notificationService.addNotificationFor(toInvite.getId(), "You have been invited to Carpool: " + carpool.getCarPoolName() + "!", "carpool");
            dao.save(dto);
        }
        return "carpool";

    }


    @PostMapping("/inviteResponse")
    public String inviteResponse(@ModelAttribute("carResponseForm") CarpoolResponseForm form, Model model, HttpSession session) {

        CarpoolDTO responseCarpool = carpoolDAO.getCarpoolDTOByCarPoolName(form.getCarPoolName());
        String action = form.getAction();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) return "error";


        if (responseCarpool != null) {
            if (action.equalsIgnoreCase("accept")) {
                // Update status to 1, we can just get the current CarpoolUsersDTO and update it and
                // it should store to database
                CarpoolUserDTO existingUserDTO = service.getCarpoolFor(user.getId());
                if (existingUserDTO != null) {
                    CarpoolDTO exCarpool = carpoolDAO.getCarpoolDTOById(existingUserDTO.getCarpoolId());
                    String existingCarpoolName = carpoolDAO.getCarpoolDTOById(existingUserDTO.getCarpoolId()).getCarPoolName();
                    model.addAttribute("errors", "You are already in a carpool!");
                    model.addAttribute("hasCarpool", true);
                    model.addAttribute("carPoolName", existingCarpoolName); //  change to CarpoolDTO name
                    model.addAttribute("members", carpoolService.getNamesOfMembers(exCarpool.getId()));
                    if (carpoolService.hasReservation(exCarpool.getId())) {
                        model.addAttribute("hasReservation", true);
                        model.addAttribute("reservation", carpoolService.getBookingFor(exCarpool.getId()));
                    } else {
                        model.addAttribute("hasReservation", false);
                    }


                    return "carpool";
                } else {
                    service.acceptInvite(responseCarpool, user.getId());

                    model.addAttribute("carpool", responseCarpool);
                    model.addAttribute("hasCarpool", true);
                    model.addAttribute("carPoolName", responseCarpool.getCarPoolName());

                    model.addAttribute("isLeader", false);

                    model.addAttribute("members", carpoolService.getNamesOfMembers(responseCarpool.getId()));

                    if (carpoolService.hasReservation(responseCarpool.getId())) {
                        model.addAttribute("hasReservation", true);
                        model.addAttribute("reservation", carpoolService.getBookingFor(responseCarpool.getId()));
                    } else {
                        model.addAttribute("hasReservation", false);
                    }


                }
            } else if (action.equalsIgnoreCase("reject")) {
                // delete from database
                service.rejectInvite(responseCarpool.getId(), user.getId());
                CarpoolUserDTO carpoolUserDTO = service.getCarpoolFor(user.getId());
                if(carpoolUserDTO != null && carpoolUserDTO.getStatus() == 1) {
                    CarpoolDTO carpoolDTO = carpoolDAO.getCarpoolDTOById(carpoolUserDTO.getCarpoolId());

                    model.addAttribute("carpool", carpoolDTO);
                    model.addAttribute("hasCarpool", true);
                    model.addAttribute("carPoolName", carpoolDTO.getCarPoolName());

                    if (carpoolDTO.getLeaderId() == user.getId()) {
                        model.addAttribute("isLeader", true);
                    } else {
                        model.addAttribute("isLeader", false);
                    }

                    model.addAttribute("members", carpoolService.getNamesOfMembers(carpoolDTO.getId()));

                    if (carpoolService.hasReservation(carpoolDTO.getId())) {
                        model.addAttribute("hasReservation", true);
                        model.addAttribute("reservation", carpoolService.getBookingFor(carpoolDTO.getId()));
                    } else {
                        model.addAttribute("hasReservation", false);
                    }


                } else {
                    model.addAttribute("hasCarpool", false);
                    model.addAttribute("isLeader", false);
                }


            } else {
                // should never reach here but we'll return error anyways
                return "error";
            }
        }

        // Doing this at the end because a user can reject an invite, which should be reflected.

        List<String> invites = service.getInvitesForUser(user.getId()).stream()
                .map(carpoolUserDTO -> carpoolDAO.getCarpoolDTOById(carpoolUserDTO.getCarpoolId()).getCarPoolName()).collect(Collectors.toList());


        model.addAttribute("invitations", invites); // can be empty


        return "carpool";
    }






}