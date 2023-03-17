package com.petesparkingmgt.dto.carpools;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarpoolUserKey implements Serializable {

   // @Column(name = "carpool_id", length = 255)
    private long carpoolId;

//    @Column(name = "user_id", length = 255)
    private long userId;


    public CarpoolUserKey(long carpoolId, long userId) {
        this.carpoolId = carpoolId;
        this.userId = userId;
    }

    public CarpoolUserKey() {

    }

    // Getters and setters for carpoolId and userId


    public long getCarpoolId() {
        return carpoolId;
    }

    public void setCarpoolId(long carpoolId) {
        this.carpoolId = carpoolId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarpoolUserKey that = (CarpoolUserKey) o;
        return Objects.equals(carpoolId, that.carpoolId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carpoolId, userId);
    }
}
