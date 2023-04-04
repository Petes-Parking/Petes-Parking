package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.users.PendingUserDAO;
import com.petesparkingmgt.dto.user.PendingUserDTO;
import com.petesparkingmgt.dto.user.UserDTO;
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

    public void add (PendingUserDTO dto) {
        dao.save(dto);
    }


    public List<PendingUserDTO> list() {
        return dao.findAll();
    }
    



    public void acceptedUser (long pendingUserId) {
        userService.add(pendingUserToUserDTO(pendingUserId));
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
