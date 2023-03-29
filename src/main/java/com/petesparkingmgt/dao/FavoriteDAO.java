package com.petesparkingmgt.dao;

import com.petesparkingmgt.dto.FavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface FavoriteDAO extends JpaRepository<FavoriteDTO, Long> {
    public List<FavoriteDTO> getFavoriteDTOSByUserId(long id);
    @Transactional
    public void deleteByUserIdAndArea(long userId, String area);
}
