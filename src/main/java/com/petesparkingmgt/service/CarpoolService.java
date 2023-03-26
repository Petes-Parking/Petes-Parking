package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.BookingDAO;
import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarpoolService {

    @Autowired
    public CarpoolDAO dao;

    @Autowired
    public CarpoolUsersDAO carpoolUsersDAO;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public BookingDAO bookingDAO;


    public void add (CarpoolDTO dto) {
        dao.save(dto);
    }

    public boolean hasReservation(long carpoolid) {
        return bookingDAO.getBookingDTOByCarpoolId(carpoolid) != null;
    }

    public BookingDTO getBookingFor(long carpoolid) {
        if (hasReservation(carpoolid)){
            return bookingDAO.getBookingDTOByCarpoolId(carpoolid);
        }
         return null;
    }

    public List<String> getNamesOfMembers(long carpoolID){
        List<String> names = new ArrayList<>();
        List<CarpoolUserDTO> members = carpoolUsersDAO.getCarpoolUserDTOSByCarpoolIdAndStatusEquals(carpoolID, 1);
        long leaderId = dao.getCarpoolDTOById(carpoolID).getLeaderId();
        if (!members.isEmpty()) {
            for (CarpoolUserDTO member : members) {
                UserDTO user = userDAO.findById(member.getUserId());
                String toAdd = (leaderId == user.getId())
                        ? "(leader) " + user.getFirstName() + " " + user.getLastName() : user.getFirstName() + " " + user.getLastName();
                names.add(toAdd);
            }
        }

        return names;

    }


    public boolean isNameTaken(String name){
        return dao.getCarpoolDTOByCarPoolName(name) != null;
    }



    /**
     *
     * @param user
     */


}
