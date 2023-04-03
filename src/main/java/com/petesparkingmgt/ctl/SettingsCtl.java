package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dto.EmailPreferencesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SettingsCtl {

    @Autowired
    public EmailPreferencesDAO emailDAO;

    @GetMapping("/settings")
    public String settingsPage(@ModelAttribute("form") UserForm form, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        EmailPreferencesDTO emailDTO = new EmailPreferencesDTO();
        boolean exists = false;
        if (emailDAO.getByUserID(user.getId()) != null) {
            emailDTO = emailDAO.getByUserID(user.getId());
            exists = true;
        }

        boolean expPref = true;
        boolean repPref = true;
        boolean palPref = true;
        int timer = 30;

        if (exists == true) {
            if (emailDTO.getReportPref() == 0) {
                repPref = false;
            }
            if (emailDTO.getParkingPalPref() == 0) {
                palPref = false;
            }
            if (emailDTO.getExpirationPref() == 0) {
                expPref = false;
            }
            timer = (int) emailDTO.getTimer();
        }

        model.addAttribute("reportPref", repPref);
        model.addAttribute("expirationPref", expPref);
        model.addAttribute("parkingPalPref", palPref);
        model.addAttribute("theTimer", timer);

        return "settings";
    }

    @PostMapping("/settingsSaveEmail")
    public String saveEmailSettings(@RequestParam(name = "palPref", required = false) boolean palPref,
                                    @RequestParam(name = "repPref", required = false) boolean repPref,
                                    @RequestParam(name = "palPref", required = false) boolean expPref,
                                    @RequestParam(name = "timerInput", required = false) Integer timerInput,
                                    HttpSession session,
                                    Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (timerInput != null) {
            expPref = true;
        } else {
            expPref = false;
        }

        int expPrefInt = 0;
        int repPrefInt = 0;
        int palPrefInt = 0;
        long timerInputLong = 0;
        if (timerInput != null) {
            timerInputLong = timerInput;
        }

        if (expPref == true) {
            expPrefInt = 1;
        }
        if (repPref == true) {
            repPrefInt = 1;
        }
        if (palPref == true) {
            palPrefInt = 1;
        }

        EmailPreferencesDTO emailDTO = new EmailPreferencesDTO();
        boolean exists = false;
        if (emailDAO.getByUserID(user.getId()) != null) {
            emailDTO = emailDAO.getByUserID(user.getId());
            exists = true;
        }

        if (timerInput == null) {
            if (exists == true) {
                timerInputLong = emailDTO.getTimer();
            } else {
                timerInputLong = 30;
            }
        }

        emailDTO.setExpirationPref(expPrefInt);
        emailDTO.setReportPref(repPrefInt);
        emailDTO.setParkingPalPref(palPrefInt);
        emailDTO.setTimer(timerInputLong);
        emailDTO.setUserID(user.getId());
        emailDAO.save(emailDTO);

        int timerInt = (int) timerInputLong;

        model.addAttribute("reportPref", repPref);
        model.addAttribute("expirationPref", expPref);
        model.addAttribute("parkingPalPref", palPref);
        model.addAttribute("theTimer", timerInt);

        System.out.println(palPref);
        System.out.println(repPref);
        System.out.println(expPref);
        System.out.println(timerInput);

        return "redirect:/settings";
    }

}
