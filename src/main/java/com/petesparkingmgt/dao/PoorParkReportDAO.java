package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.PoorParkReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoorParkReportDAO extends JpaRepository<PoorParkReportDTO, Long> {

    public List<PoorParkReportDTO> findAll();

    public PoorParkReportDTO getById(Long reportID);

}
