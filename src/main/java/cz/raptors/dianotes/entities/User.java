package cz.raptors.dianotes.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vamvda1 on 25.3.14.
 */
@NamedQueries({
	@NamedQuery(name = User.GET_USER_BY_EMAIL_AND_PASSWORD, query = "select u from User u where u.email=:email and u.password=:password")
})
@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

	public static final String GET_USER_BY_EMAIL_AND_PASSWORD = "User.getUserByEmailAndPassword";
	
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "EMAIL")
    @Email
    @NotEmpty
    private String email;

    @Column(name = "PASSWORD")
    @NotEmpty
    private String password;

    @Column(name = "FIRST_NAME")
    @NotEmpty
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotEmpty
    private String lastName;

    @Column (name = "SEX")
    @NotEmpty
    private String sex;

    @OneToMany(mappedBy = "user")
    private List<AssignedRoles> assignedRolesList;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private HealthCard healthCard;

    public Address getUser(){
        return this.address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public HealthCard getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(HealthCard healthCard) {
        this.healthCard = healthCard;
    }

    public List<AssignedRoles> getUserRoleList() {
        return assignedRolesList;
    }

    public void setUserRoleList(List<AssignedRoles> userRoleList) {
        this.assignedRolesList = userRoleList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
