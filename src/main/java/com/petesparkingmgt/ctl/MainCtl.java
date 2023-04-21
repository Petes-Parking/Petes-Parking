package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dto.user.FavoriteDTO;
import com.petesparkingmgt.dao.users.FavoriteDAO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.FavoriteForm;
import com.petesparkingmgt.service.NotificationService;
import com.petesparkingmgt.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainCtl {

    @Autowired
    public FavoriteDAO favoriteDAO;

    @Autowired
    public ParkingService parkingService;

    @Autowired
    private NotificationService notificationService;
    @GetMapping("/main")
    public String MainPage(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }
        List<FavoriteDTO> favoriteList = favoriteDAO.getFavoriteDTOSByUserId(user.getId());
        List<String> formattedFavList = new ArrayList<>();
        for (FavoriteDTO favoriteDTO : favoriteList) {
            String favorite = favoriteDTO.getArea();
            formattedFavList.add(favorite);
        }
        model.addAttribute("favorites", formattedFavList);

        byte[] imageData = user.getProfilePicture();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("profilePic", base64Image);
        parkingService.addOccupancyData(model);
        parkingService.addNameAndAdress(model);



        NotificationService.addNotifications(model, notificationService, user.getId());



        return "mainPage";
    }
    @PostMapping("/addFavorite")
    public String Add(@Valid @ModelAttribute("favoriteForm") FavoriteForm form, BindingResult bindingResult, Model model, HttpSession session) {
        UserDTO user = (UserDTO)session.getAttribute("user");
        List<FavoriteDTO> favoriteList = favoriteDAO.getFavoriteDTOSByUserId(user.getId());
        FavoriteDTO favoriteBean = new FavoriteDTO();
        favoriteBean.setUserId(user.getId());
        favoriteBean.setArea(form.getArea());
        boolean save = true;
        for (FavoriteDTO favoriteDTO : favoriteList) {
            String area = favoriteDTO.getArea();
            long id = favoriteDTO.getUserId();
            if (area.equals(form.getArea()) && id == user.getId()) {
                save = false;
            }
        }
        if (!save) {
            favoriteDAO.deleteByUserIdAndArea(user.getId(), form.getArea());
        } else {
            favoriteDAO.save(favoriteBean);
        }

        return "redirect:/main";
    }


}
