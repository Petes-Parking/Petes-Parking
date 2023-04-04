package com.petesparkingmgt.dao.reports;

import com.petesparkingmgt.dto.reports.ExpReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpReportDAO extends JpaRepository<ExpReportDTO, Long> {

    public List<ExpReportDTO> findAll();

    public ExpReportDTO getById(Long reportID);

    public void deleteById(Long reportID);
}
