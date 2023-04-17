package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.PermissionDAO;
import com.petesparkingmgt.dao.referrals.ReferralUserDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.PermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {


    @Autowired
    public UserDAO users;

    @Autowired
    public PermissionDAO permissionDAO;


    public boolean hasPermissionToReport(long userid) {
        return permissionDAO.findByUserID(userid).isCanReport();
    }

    public void addDefaultPermission(long userid) {
        PermissionDTO newPerm = new PermissionDTO();
        newPerm.setUserID(userid);
        newPerm.setCanReport(true);
        permissionDAO.save(newPerm);
    }

    public void changeReportPermission(long userid, boolean toChange) {
        PermissionDTO newPerm = permissionDAO.findByUserID(userid);
        newPerm.setCanReport(toChange);
        permissionDAO.save(newPerm);
    }
}
