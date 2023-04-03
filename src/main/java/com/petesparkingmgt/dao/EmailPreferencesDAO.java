package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.EmailPreferencesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailPreferencesDAO extends JpaRepository<EmailPreferencesDTO, Long> {

    public EmailPreferencesDTO getByUserID(Long id);
}
