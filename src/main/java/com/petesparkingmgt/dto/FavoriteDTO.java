package com.petesparkingmgt.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_favorite")
@Getter
@Setter
public class FavoriteDTO extends BaseDTO{

    @Column(name = "userId", length = 755)
    private long userId;

    @Column(name = "area", length = 755)
    private String area;


}
