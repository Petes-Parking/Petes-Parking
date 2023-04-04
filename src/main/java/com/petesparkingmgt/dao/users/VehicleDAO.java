package com.petesparkingmgt.dao.users;

import com.petesparkingmgt.dto.user.VehicleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDAO extends JpaRepository<VehicleDTO, Long> {

    public VehicleDTO getVehicleDTOById(long id);

    public VehicleDTO getVehicleDTOByUserId(long userid);
}
