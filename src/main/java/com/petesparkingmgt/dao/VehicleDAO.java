package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.VehicleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDAO extends JpaRepository<VehicleDTO, Long> {

    public VehicleDTO getVehicleDTOById(long id);
}
