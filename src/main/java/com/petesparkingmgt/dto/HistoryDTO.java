package com.petesparkingmgt.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_history")
@Getter
@Setter
public class HistoryDTO extends BaseDTO{

    @Column(name = "userId", length = 755)
    private long userId;

    @Column(name = "date", length = 755)
    private String date;

    @Column(name = "area", length = 755)
    private String area;


}
