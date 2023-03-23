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
        String[] histories = new String[historyList.size()];
        for (int i = 0; i < historyList.size(); i++) {
            String stringOne = historyList.get(i).getDate();
            String stringTwo = historyList.get(i).getArea();
            String history = stringOne + " : " + stringTwo;
            histories[i] = history;
            System.out.println("TESTESTESTTESTESTSETESTSTSTT:  " + histories[i]);
        }
        model.addAttribute("histories", histories);

        List<FavoriteDTO> favoriteList = favoriteDAO.getFavoriteDTOSByUserId(user.getId());
        String[] favorites = new String[favoriteList.size()];
        for (int i = 0; i < favoriteList.size(); i++) {
            String favorite = favoriteList.get(i).getArea();
            favorites[i] = favorite;
        }
        model.addAttribute("favorites", favorites);
        return "historyPage";
    }


}
