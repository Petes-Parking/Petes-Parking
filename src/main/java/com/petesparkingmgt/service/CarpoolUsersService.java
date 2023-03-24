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

    public CarpoolDTO getCarpoolDTOFor(long userid) {
        return carpoolDAO.getCarpoolDTOById(dao.getCarpoolUserDTOByUserIdAndStatusEquals(userid, 1).getCarpoolId());
    }

    public CarpoolUserDTO acceptInvite(CarpoolDTO carpool, long userId){
        // Delete entry with status 0, reinsert with status 1
        dao.deleteCarpoolUserDTOByCarpoolIdAndUserId(carpool.getId(), userId);
        CarpoolUserDTO accepted = new CarpoolUserDTO();
        accepted.setCarpoolId(carpool.getId());
        accepted.setUserId(userId);
        accepted.setStatus(1);
        add(accepted);

        return accepted;
    }


    public boolean isInCarpoolButNotLeader(long userid) {
        return getCarpoolFor(userid) != null && carpoolDAO.getCarpoolDTOById(getCarpoolFor(userid).getCarpoolId()).getLeaderId() != userid;
    }

    public String getCarpoolNameFor(long userid) {
        if (getCarpoolFor(userid) != null) {
            return carpoolDAO.getCarpoolDTOById(getCarpoolFor(userid).getCarpoolId()).getCarPoolName();
        } else return null;
    }
    public boolean isLeader(long userid) {
        return carpoolDAO.getCarpoolDTOByLeaderId(userid) != null;
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