package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolDAO extends JpaRepository<CarpoolDTO, Long> {

    public CarpoolDTO getCarpoolDTOByLeaderId(long id);

    public void deleteById(long id);

}
