package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.HistoryDAO;
import com.petesparkingmgt.dto.HistoryDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.points.PointsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    @Autowired
    public UserService userService;

    @Autowired
    public HistoryDAO dao;


    /**
     * Returns the amount of points to be given for a user based on NOW
     * @param userid to get points for
     */
    public int getPointsFor(long userid){
        UserDTO user = userService.findUserById(userid);
        List<HistoryDTO> historyDTOList = dao.getHistoryDTOSByUserId(userid);
        // Need to sort this by date
        List<LocalDate> dateFormatted = historyDTOList.stream()
                .map(historyDTO -> LocalDate.parse(historyDTO.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))).collect(Collectors.toList())
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        List<LocalDate> threeLatest = new ArrayList<>();
        int j = Math.min(3, dateFormatted.size());
        for (int i = 0; i < j; i++) {
            threeLatest.add(dateFormatted.get(i));
        }
        return PointsManager.getPointsForHistory(threeLatest);


    }
}
