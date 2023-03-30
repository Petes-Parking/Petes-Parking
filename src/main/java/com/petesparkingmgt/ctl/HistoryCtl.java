package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.HistoryDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.FavoriteDTO;
import com.petesparkingmgt.dao.FavoriteDAO;
import com.petesparkingmgt.dto.HistoryDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.CarpoolForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryCtl {

    @Autowired
    public HistoryDAO historyDAO;
    @Autowired
    public FavoriteDAO favoriteDAO;
    @GetMapping("/history")
    public String HistoryPage(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }

        List<HistoryDTO> historyList = historyDAO.getHistoryDTOSByUserId(user.getId());
        List<String> formattedHistList = new ArrayList<>();
        for (HistoryDTO historyDTO : historyList) {
            String stringOne = historyDTO.getDate();
            String stringTwo = historyDTO.getArea();
            String history = stringOne + " : " + stringTwo;
            formattedHistList.add(history);
        }
        model.addAttribute("histories", formattedHistList);

        List<FavoriteDTO> favoriteList = favoriteDAO.getFavoriteDTOSByUserId(user.getId());
        List<String> formattedFavList = new ArrayList<>();
        for (FavoriteDTO favoriteDTO : favoriteList) {
            String favorite = favoriteDTO.getArea();
            formattedFavList.add(favorite);
        }
        model.addAttribute("favorites", formattedFavList);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

        return "historyPage";
    }


}
