package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.PendingUserDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.PendingUserDTO;
import com.petesparkingmgt.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        }
        return add;
    }

    public PendingUserDTO getPendingUser(long id) {
       return dao.findById(id);
    }

    public PendingUserDTO getPendingUser(String email) {
        return dao.findByEmail(email);
    }

    
}
