package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.form.CarpoolAddMemberForm;
import com.petesparkingmgt.form.CarpoolResponseForm;
import com.petesparkingmgt.service.CarpoolUsersService;
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
    public CarpoolUsersDAO dao;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public CarpoolDAO carpoolDAO;



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


        if (toInvite == null) {
            // not an existing user, tell user this
            model.addAttribute("errors", form.getEmail() + " is not a registered email!");
            model.addAttribute("carPoolName", carpool.getCarPoolName());



        } else {
            model.addAttribute("messages", "You have invited " + toInvite.getFirstName() + " " + toInvite.getLastName() + "!");
            CarpoolUserDTO dto = new CarpoolUserDTO();
            dto.setCarpoolId(carpool.getId());
            dto.setUserId(toInvite.getId());
            dto.setStatus(0);
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

        List<String> invites = service.getInvitesForUser(user.getId()).stream()
                .map(carpoolUserDTO -> carpoolDAO.getCarpoolDTOById(carpoolUserDTO.getCarpoolId()).getCarPoolName()).collect(Collectors.toList());


        model.addAttribute("invitations", invites); // can be empty

        if (responseCarpool != null) {
            if (action.equalsIgnoreCase("accept")) {
                // Update status to 1, we can just get the current CarpoolUsersDTO and update it and
                // it should store to database
                service.acceptInvite(responseCarpool.getId(), user.getId());

                model.addAttribute("carpool", responseCarpool);
                model.addAttribute("hasCarpool", true);
                model.addAttribute("carPoolName", responseCarpool.getCarPoolName());

                model.addAttribute("isLeader", false);

                // Mapping CarpoolUserDTO to UserDTO
                List<UserDTO> carpoolMembers = service.getConfirmedUsersFor(responseCarpool.getId())
                        .stream().map(cuserDTO -> userDAO.findById(cuserDTO.getUserId())).collect(Collectors.toList());
                if (!dao.getCarpoolUserDTOSByCarpoolId(responseCarpool.getId()).isEmpty()) {
                    model.addAttribute("members", carpoolMembers);
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

                    List<UserDTO> carpoolMembers = service.getConfirmedUsersFor(responseCarpool.getId())
                            .stream().map(cuserDTO -> userDAO.findById(cuserDTO.getUserId())).collect(Collectors.toList());
                    if (!dao.getCarpoolUserDTOSByCarpoolId(responseCarpool.getId()).isEmpty()) {
                        model.addAttribute("members", carpoolMembers);
                    }

                }


            } else {
                // should never reach here but we'll return error anyways
                return "error";
            }
        }


        return "carpool";
    }






    }
