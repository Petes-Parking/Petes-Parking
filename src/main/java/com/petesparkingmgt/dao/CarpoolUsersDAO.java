package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarpoolUsersDAO extends JpaRepository<CarpoolUserDTO, Long> {

    public List<CarpoolUserDTO> getCarpoolUserDTOSByCarpoolId(long id);


    public List<CarpoolUserDTO> getCarpoolUserDTOSByUserIdAndStatusEquals(long userid, long status);

    public CarpoolUserDTO getCarpoolUserDTOSByCarpoolIdAndUserId(long carpoolid, long userid);

    public void deleteCarpoolUserDTOByCarpoolIdAndUserId(long carpoolId, long userid);

    public CarpoolUserDTO getCarpoolUserDTOByUserId(long userid);


}
