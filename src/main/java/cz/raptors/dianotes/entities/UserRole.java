package cz.raptors.dianotes.entities;

import cz.raptors.dianotes.entities.enums.UserRoleType;

import javax.persistence.*;

/**
 * Created by vamvda1 on 25.3.14.
 */
@Table(name = "USER_ROLE")
@Entity
public class UserRole extends AbstractEntity {

    @Column(name = "USER_ROLE_TYPE")
    @Enumerated(EnumType.STRING)
    private UserRoleType userRoleType;

    public UserRoleType getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(UserRoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

}
