package com.petesparkingmgt.dao.reports;

import com.petesparkingmgt.dto.reports.PoorParkReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoorParkReportDAO extends JpaRepository<PoorParkReportDTO, Long> {

    public List<PoorParkReportDTO> findAll();

    public PoorParkReportDTO getById(Long reportID);

    public void deleteById(Long reportID);
}
