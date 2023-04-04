package com.petesparkingmgt.dao.users;

import com.petesparkingmgt.dto.user.FriendDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface FriendDAO extends JpaRepository<FriendDTO, Long> {

    public List<FriendDTO> getFriendDTOSByRecipientEmailAndStatusEquals(String recipientEmail, long status);

    public List<FriendDTO> getFriendDTOSBySenderEmailAndStatusEquals(String senderEmail, long status);

    public FriendDTO getFriendDTOBySenderEmailAndRecipientEmailAndStatusEquals(String senderEmail, String recipientEmail, long status);

    public FriendDTO getFriendDTOByRecipientEmailAndStatusEquals(String recipientEmail, long status);

    @Transactional
    public void deleteFriendDTOBySenderEmailAndRecipientEmailAndStatusEquals(String senderEmail, String recipientEmail, long status);

}
