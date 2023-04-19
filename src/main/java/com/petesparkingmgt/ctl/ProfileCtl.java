package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.dto.user.VehicleDTO;
import com.petesparkingmgt.form.VehicleForm;
import com.petesparkingmgt.points.PointsManager;
import com.petesparkingmgt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class ProfileCtl {


    @Autowired
    public VehicleDAO dao;

    @Autowired
    public UserDAO userdao;

    @Autowired
    public NotificationService notificationService;

    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }
        PointsManager.LevelWrapper wrapper = PointsManager.getLevel(user.getPoints());
        model.addAttribute("nextLevelPoints", wrapper.getNextLevelThreshold());
        model.addAttribute("user", user);
        VehicleDTO vehicleDTO = dao.getVehicleDTOByUserId(user.getId());
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

        if (vehicleDTO != null) {
            model.addAttribute("vehicle", vehicleDTO);
        } else {
            // currently does not exist in database
            vehicleDTO = new VehicleDTO();
            vehicleDTO.setUserId(user.getId());
            model.addAttribute(vehicleDTO);
            dao.save(vehicleDTO);
        }

        byte[] imageData = user.getProfilePicture();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("profilePic", base64Image);

        NotificationService.addNotifications(model, notificationService, user.getId());


        return "profilePage";
    }

    @PostMapping("/addVehicle")
    public String addVehicle(@ModelAttribute("vehicleForm") VehicleForm form, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            return "error";
        }
        VehicleDTO exist = dao.getVehicleDTOByUserId(user.getId());
        if (exist != null){
            // Update and return back to database
            exist.setMake1(form.getMake1());
            exist.setModel1(form.getModel2());
            exist.setColor1(form.getColor1());
            exist.setLicense1(form.getLicense1());
            exist.setMake2(form.getMake2());
            exist.setModel2(form.getModel2());
            exist.setColor2(form.getColor2());
            exist.setLicense2(form.getLicense2());
            exist.setPermitA(form.getPermitA());
            exist.setPermitB(form.getPermitB());
            exist.setPermitC(form.getPermitC());
            exist.setPermitCG(form.getPermitCG());
            exist.setPermitR(form.getPermitR());
            exist.setPermitD(form.getPermitD());
            dao.save(exist);
            model.addAttribute("permitA", form.getPermitA());
            model.addAttribute("permitB", form.getPermitB());
            model.addAttribute("permitC", form.getPermitC());
            model.addAttribute("permitCG", form.getPermitCG());
            model.addAttribute("permitR", form.getPermitR());
            model.addAttribute("permitD", form.getPermitD());
            model.addAttribute("success", "Updated car(s)!");

        } else {
            if (form.getDTO() != null) {
                VehicleDTO dto = form.getDTO();
                dto.setUserId(user.getId());
                dao.save(dto);
                model.addAttribute("success", "Saved car(s)!");
            } else {
                model.addAttribute("error", "Error while saving car(s)!");
            }
        }
        return "redirect:/profile";

    }

    @PostMapping("/addProfilePic")
    public String addProfilePic(@RequestParam("imageData") MultipartFile imageData, HttpSession session) throws IOException {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (!imageData.isEmpty()) {
            user.setProfilePicture(imageData.getBytes());
        }
        userdao.save(user);

        return "redirect:/profile";
    }

}
