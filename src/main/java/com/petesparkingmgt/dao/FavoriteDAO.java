package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.FavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDAO extends JpaRepository<FavoriteDTO, Long> {
    public List<FavoriteDTO> getFavoriteDTOSByUserId(long id);
}
