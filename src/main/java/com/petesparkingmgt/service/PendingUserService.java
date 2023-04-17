package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.users.PendingUserDAO;
import com.petesparkingmgt.dto.referrals.ReferralDTO;
import com.petesparkingmgt.dto.referrals.ReferralUserDTO;
import com.petesparkingmgt.dto.user.PendingUserDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.points.PointsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PendingUserService {

    @Autowired
    public PendingUserDAO dao;

    @Autowired
    public UserService userService;

    @Autowired
    public PermissionService permissionService;

    @Autowired
    public ReferralService referralService;

    @Autowired
    public HistoryService historyService;

    public void add (PendingUserDTO dto) {
        dao.save(dto);
    }


    public List<PendingUserDTO> list() {
        return dao.findAll();
    }
    



    public void acceptedUser (long pendingUserId) {
        UserDTO transfer = pendingUserToUserDTO(pendingUserId);
        userService.add(transfer);


        ReferralUserDTO referralUserDTO = new ReferralUserDTO();
        permissionService.addDefaultPermission(transfer.getId());
        if (transfer.getReferralCodeUsed() != null && !transfer.getReferralCodeUsed().isEmpty()
        && referralService.isValidReferralCode(transfer.getReferralCodeUsed())) {
            ReferralDTO referral = referralService.getReferralForCode(transfer.getReferralCodeUsed());
            referral.setUses(referral.getUses() + 1);
            historyService.newPoints(transfer.getId());
            historyService.newPoints(userService.findUserById(referral.getUserId()).getId());
            referralUserDTO.setReferralId(referral.getId());
            referralUserDTO.setReferredId(transfer.getId());
            referralService.saveReferralUserDTO(referralUserDTO);
            referralService.updateReferral(referral);
        }
        dao.deleteById(pendingUserId);

    }

    public void rejectUser(long pendingUserId) {
        dao.deleteById(pendingUserId);
    }
    
    public void cancelUser(long pendingUserId) {
        dao.deleteById(pendingUserId);
    }


    public UserDTO pendingUserToUserDTO(long pendingUserId){
        PendingUserDTO pend = getPendingUser(pendingUserId);
        UserDTO add = new UserDTO();
        if (pend != null) {
            System.out.println("Got here! -");
            add.setUserRole(pend.getUserRole());
            add.setPassword(pend.getPassword());
            add.setPassword2(pend.getPassword2());
            add.setEmail(pend.getEmail());
            add.setPhoneNumber(pend.getPhoneNumber());
            add.setDob(pend.getDob());
            add.setGender(pend.getGender());
            add.setFirstName(pend.getFirstName());
            add.setLastName(pend.getLastName());
            add.setId(add.getId());
            add.setProfilePicture(getDefaultProfilePicture());
            add.setReferralCodeUsed(pend.getReferralCodeUsed());
        }
        return add;
    }

    public byte[] getDefaultProfilePicture() {
        String defaultProfilePicturePath = "src/main/webapp/resources/image/profile-pic.png";
        Path path = Paths.get(defaultProfilePicturePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PendingUserDTO getPendingUser(long id) {
       return dao.findById(id);
    }

    public PendingUserDTO getPendingUser(String email) {
        return dao.findByEmail(email);
    }

    
}
