package com.petesparkingmgt.dao.users;

import com.petesparkingmgt.dto.user.NotificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationDAO extends JpaRepository<NotificationDTO, Long> {


    public List<NotificationDTO> getNotificationDTOSByUserId(long userid);

}
