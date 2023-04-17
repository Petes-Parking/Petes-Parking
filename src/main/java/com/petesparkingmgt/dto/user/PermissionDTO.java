package com.petesparkingmgt.dto.user;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="permissions")
@Getter
@Setter
public class PermissionDTO extends BaseDTO {

    @Column(name = "user_id")
    private long userID;

    @Column(name = "report_permission")
    private boolean canReport;
}
