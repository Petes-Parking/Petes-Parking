package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.HistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryDAO extends JpaRepository<HistoryDTO, Long> {
    public List<HistoryDTO> getHistoryDTOSByUserId(long id);
}
