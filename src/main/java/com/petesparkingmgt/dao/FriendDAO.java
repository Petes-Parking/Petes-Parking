package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.FriendDTO;
import com.petesparkingmgt.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

public interface FriendDAO extends JpaRepository<FriendDTO, Long> {

    public List<FriendDTO> getFriendDTOSByRecipientEmailAndStatusEquals(String recipientEmail, long status);

    public List<FriendDTO> getFriendDTOSBySenderEmailAndStatusEquals(String senderEmail, long status);

    public FriendDTO getFriendDTOByRecipientEmailAndStatusEquals(String recipientEmail, long status);

    @Transactional
    public void deleteFriendDTOBySenderEmailAndRecipientEmail(String senderEmail, String recipientEmail);
    @Transactional
    public void deleteFriendDTOBySenderEmailAndRecipientEmailAndStatusEquals(String senderEmail, String recipientEmail, long status);

}
