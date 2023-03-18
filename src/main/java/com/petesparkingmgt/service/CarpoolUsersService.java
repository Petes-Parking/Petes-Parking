package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarpoolUsersService {

    @Autowired
    public CarpoolUsersDAO dao;


    public void add (CarpoolUserDTO dto) {
        dao.save(dto);
    }




}
