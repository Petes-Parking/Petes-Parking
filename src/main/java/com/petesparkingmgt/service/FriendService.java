package com.petesparkingmgt.service;

import com.petesparkingmgt.dao.FriendDAO;
import com.petesparkingmgt.dto.FriendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    public FriendDAO dao;

    public void add (FriendDTO dto) {
        dao.save(dto);
    }

    public List<FriendDTO> getInvitesForUser(String userEmail) {
        return dao.getFriendDTOSByRecipientEmailAndStatusEquals(userEmail, 0);
    }

    public List<FriendDTO> getConfirmedUsersFor(String userEmail) {

        List<FriendDTO> confirmed = dao.getFriendDTOSByRecipientEmailAndStatusEquals(userEmail, 1);
        confirmed.addAll(dao.getFriendDTOSBySenderEmailAndStatusEquals(userEmail, 1));
        return confirmed;

    }

    public List<FriendDTO> getOutgoingRequestsFor(String userEmail) {

        return dao.getFriendDTOSBySenderEmailAndStatusEquals(userEmail, 0);
    }

    public void acceptInvite(String recipientEmail, String senderEmail) {

        FriendDTO temp = dao.getFriendDTOBySenderEmailAndRecipientEmailAndStatusEquals(senderEmail, recipientEmail, 0);

        FriendDTO accepted = new FriendDTO();
        accepted.setSenderEmail(senderEmail);
        accepted.setRecipientEmail(recipientEmail);
        accepted.setStatus(1);
        accepted.setSenderFirstName(temp.getSenderFirstName());
        accepted.setSenderLastName(temp.getSenderLastName());
        accepted.setRecipientFirstName(temp.getRecipientFirstName());
        accepted.setRecipientLastName(temp.getRecipientLastName());
        dao.save(accepted);

        dao.deleteFriendDTOBySenderEmailAndRecipientEmailAndStatusEquals(senderEmail, recipientEmail, 0);

    }

    public void rejectInvite(String recipientEmail, String senderEmail) {
        dao.deleteFriendDTOBySenderEmailAndRecipientEmailAndStatusEquals(senderEmail, recipientEmail, 0);
    }

}
