package cz.raptors.dianotes.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vamvda1 on 12.6.14.
 */
@Entity
@Table(name = "ASSIGNED_ROLES")
public class AssignedRoles extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="USER_ROLE_ID")
    private UserRole assignedRole;

    public AssignedRoles(){}

    public static class Builder {

        private User user;
        
        private UserRole userRole;

        public Builder user(User user){
            this.user = user;
            return this;
        }
        public Builder userRole(UserRole userRole){
            this.userRole = userRole;
            return this;
        }

        public AssignedRoles build(){
            return new AssignedRoles(this);
        }

    }

    private AssignedRoles(Builder b){
        this.assignedRole = b.userRole;
        this.user = b.user;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRole getAssignedRole() {
        return assignedRole;
    }

    public void setAssignedRole(UserRole assignedRole) {
        this.assignedRole = assignedRole;
    }
}
