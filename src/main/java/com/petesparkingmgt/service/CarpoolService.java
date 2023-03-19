package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarpoolService {

    @Autowired
    public CarpoolDAO dao;


    public void add (CarpoolDTO dto) {
        dao.save(dto);
    }


    /**
     *
     * @param user
     */
    public void removeCarpoolFor(UserDTO user) {
        CarpoolDTO remove = dao.getCarpoolDTOByLeaderId(user.getId()); // this will only work for leaders FYI
        if (remove.getLeaderId() == user.getId()) {
            // delete the entire carpool

            dao.deleteById(remove.getId());
            System.out.println("Deleted " + remove.getCarPoolName());

        } else {
            // TODO remove the entry in Carpool users table

        }
    }

}
