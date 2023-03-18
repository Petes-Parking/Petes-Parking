package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.PoorParkReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoorParkReportDAO extends JpaRepository<PoorParkReportDTO, Long> {
}
