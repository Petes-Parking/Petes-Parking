package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpReportDAO extends JpaRepository<ExpReportDTO, Long> {

    public List<ExpReportDTO> findAll();

    public ExpReportDTO getById(Long reportID);
}
