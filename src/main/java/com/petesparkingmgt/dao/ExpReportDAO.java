package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.ExpReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpReportDAO extends JpaRepository<ExpReportDTO, Long> {
}
