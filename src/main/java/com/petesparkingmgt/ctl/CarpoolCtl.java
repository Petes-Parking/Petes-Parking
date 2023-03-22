package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.form.CarpoolForm;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.CarpoolService;
import com.petesparkingmgt.service.CarpoolUsersService;
import com.petesparkingmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
3. Deleting a carpool should remove from users table too (if leader left, this would cause bugs) --- DONE (think needs to be tested)
4. When creating a carpool while having active invitations, and after creating, those invitations vanish -- need to test
5. Probably need to check for no duplicate group names - not done
6. More probably


after a certain point, it is beneficial to just finish the user story and not worry about every single edge
case since there is a lot no doubt. as long as the core functionality is working.

 */
@Controller
public class CarpoolCtl {

    @Autowired
    public CarpoolService service;

    @Autowired
    public CarpoolUsersService carpoolUsersService;

    @Autowired
    public CarpoolDAO dao;

    @Autowired
    public CarpoolUsersDAO carpoolUsersDAO;

    @Autowired
    public UserDAO userDAO;

    @GetMapping("/carpool")
    public String carpool(@ModelAttribute("form") CarpoolForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }

        System.out.println("User: " + user.toString() + " on /carpool");
        CarpoolUserDTO carpoolUserDTO = carpoolUsersService.getCarpoolFor(user.getId());

        model.addAttribute("carPoolName", "");


        // Potential issue here where duplicate names can cause issues, but will examine this later
        // Thinking of maybe creating a wrapper object, refactoring CarpoolUserDTO to contain name maybe
        List<String> invites = carpoolUsersService.getInvitesForUser(user.getId()).stream()
                .map(cu -> dao.getCarpoolDTOById(cu.getCarpoolId()).getCarPoolName()).collect(Collectors.toList());


        model.addAttribute("invitations", invites); // can be empty

        if (carpoolUserDTO != null) {
            CarpoolDTO carpool = dao.getCarpoolDTOById(carpoolUserDTO.getCarpoolId());

            model.addAttribute("carpool", carpool);
            model.addAttribute("hasCarpool", true);
            model.addAttribute("carPoolName", carpool.getCarPoolName());

            if(carpool.getLeaderId() == user.getId()) {
                model.addAttribute("isLeader", true);
            } else {
                model.addAttribute("isLeader", false);
            }

            model.addAttribute("members", service.getNamesOfMembers(carpool.getId()));


        } else {
            model.addAttribute("hasCarpool", false);
        }
        return "carpool";
    }


    @PostMapping("/createCarpool")
    public String createCarpool(@ModelAttribute("carform") CarpoolForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");

        model.addAttribute("carPoolName", "");
        model.addAttribute("hasCarpool", true);
        model.addAttribute("isLeader", true);


        System.out.println(user.toString() + " on /createCarpool");

        if (form.getDTO() != null) {
            CarpoolDTO dto = form.getDTO();

            // Insert into users table as well


            dto.setLeaderId(user.getId());
            service.add(dto);
            CarpoolUserDTO carpoolUserDTO = new CarpoolUserDTO();
            CarpoolDTO inTable = dao.getCarpoolDTOByLeaderId(user.getId());
            carpoolUserDTO.setCarpoolId(inTable.getId());
            carpoolUserDTO.setUserId(user.getId());
            carpoolUserDTO.setStatus(1);
            carpoolUsersService.add(carpoolUserDTO);
            model.addAttribute("carpool", dto);
            model.addAttribute("carPoolName", dto.getCarPoolName());

            model.addAttribute("messages", "New carpool created!");

            // Special case here, on create, there is only one member, the leader.
            // Method before wasnt working because it was too quick for the database, so just manually doing it.
            List<String> members = new ArrayList<>();
            members.add("(leader) " + user.getFirstName() + " " + user.getLastName());
            model.addAttribute("members", members);


            System.out.println("Created carpool DTO with name " + form.getCarPoolName() + " and leaderid " + dto.getLeaderId());
        } else {
            System.out.println("CarpoolDTO was null!");

        }
        return "carpool";
    }


    /**
     * Mapping for when a user leaves a carpool. If leader leaves, it disbands the entire carpool.
     * @param model
     * @param session
     * @return jsp to follow
     */
    @GetMapping("/leaveCarpool")
    public String leaveCarpool(@ModelAttribute("form") CarpoolForm form, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        CarpoolUserDTO leaving = carpoolUsersService.getCarpoolFor(user.getId());

        System.out.println(user.toString() + " on /leaveCarpool");

        // model.addAttribute("carPoolName", "");
        model.addAttribute("hasCarpool", false);
        model.addAttribute("messages", "You have left your carpool!");

        carpoolUsersService.leaveCarpoolFor(user.getId(), leaving.getCarpoolId());



        return "carpool";

    }

}