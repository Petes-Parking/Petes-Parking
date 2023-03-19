package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarpoolUsersService {

    @Autowired
    public CarpoolUsersDAO dao;


    public void add (CarpoolUserDTO dto) {
        dao.save(dto);
    }



    public List<CarpoolUserDTO> getInvitesForUser(long userid) {
        return dao.getCarpoolUserDTOSByUserIdAndStatusEquals(userid, 0);

    }

    public List<CarpoolUserDTO> getConfirmedUsersFor(long carpoolid) {
        return dao.getCarpoolUserDTOSByUserIdAndStatusEquals(carpoolid, 1);
    }

    public CarpoolUserDTO getCarpoolFor(long userid) {
        return dao.getCarpoolUserDTOByUserId(userid);
    }

    public void acceptInvite(long carpoolId, long userId){
        CarpoolUserDTO dto = dao.getCarpoolUserDTOSByCarpoolIdAndUserId(carpoolId, userId);
        dto.setStatus(1);
    }

    public void rejectInvite(long carpoolId, long userId){
       dao.deleteCarpoolUserDTOByCarpoolIdAndUserId(carpoolId, userId);
    }



}
