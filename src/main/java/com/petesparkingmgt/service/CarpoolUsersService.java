package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarpoolUsersService {

    @Autowired
    public CarpoolUsersDAO dao;

    @Autowired
    public CarpoolDAO carpoolDAO;


    public void add (CarpoolUserDTO dto) {
        dao.save(dto);
    }



    public List<CarpoolUserDTO> getInvitesForUser(long userid) {
        return dao.getCarpoolUserDTOSByUserIdAndStatusEquals(userid, 0);

    }

    public List<CarpoolUserDTO> getConfirmedUsersFor(long carpoolid) {
        return dao.getCarpoolUserDTOSByUserIdAndStatusEquals(carpoolid, 1);
    }

    /**
     * Only returns status = 1; not pending
     * @param userid
     * @return
     */
    public CarpoolUserDTO getCarpoolFor(long userid) {
        return dao.getCarpoolUserDTOByUserIdAndStatusEquals(userid, 1);
    }

    public CarpoolUserDTO acceptInvite(long carpoolId, long userId){
        CarpoolUserDTO dto = dao.getCarpoolUserDTOSByCarpoolIdAndUserId(carpoolId, userId);
        dto.setStatus(1);
        return dto;
    }

    public void rejectInvite(long carpoolId, long userId){
        dao.deleteCarpoolUserDTOByCarpoolIdAndUserId(carpoolId, userId);
    }

    public void leaveCarpoolFor(long userId, long carPoolID) {
        CarpoolDTO carpool = carpoolDAO.getCarpoolDTOById(carPoolID);
        long leaderId = carpool.getLeaderId();
        if (leaderId == userId) {
            // leader is leaving, delete everything
            // Things to delete
            // Entry in Carpools
            // Every entry in Carpool_Users WHERE carpoolId = above
            dao.deleteCarpoolUserDTOSByCarpoolId(carPoolID);
            carpoolDAO.deleteById(carPoolID);


        } else {
            // not leader, just remove entry in carpool users table
            dao.deleteCarpoolUserDTOByCarpoolIdAndUserId(carPoolID, userId);
        }

    }


}