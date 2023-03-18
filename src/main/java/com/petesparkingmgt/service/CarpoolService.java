package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.CarpoolDAO;
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

}
