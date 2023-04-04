package com.petesparkingmgt.dao.carpools;

import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolDAO extends JpaRepository<CarpoolDTO, Long> {

    public CarpoolDTO getCarpoolDTOByLeaderId(long id);

    public void deleteById(long id);

    public CarpoolDTO getCarpoolDTOById(long id);

    public CarpoolDTO getCarpoolDTOByCarPoolName(String name);

}
