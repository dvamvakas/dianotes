package cz.raptors.dianotes.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vamvda1 on 25.3.14.
 */
@Table(name = "HEALTH_CARD")
@Entity
public class HealthCard extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "healthCard", fetch = FetchType.LAZY)
    private List<Bolus> userBoluses;

    @OneToMany(mappedBy = "healthCard", fetch = FetchType.LAZY)
    private List<Basal> userBasal;

    @OneToMany (mappedBy = "healthCard", fetch = FetchType.LAZY)
    private List<Glucose> userGlucose;

    @Column(name = "HEIGHT")
    private int height;

    @Column(name = "WEIGHT")
    private int weight;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Bolus> getUserBoluses() {
        return userBoluses;
    }

    public void setUserBoluses(List<Bolus> userBoluses) {
        this.userBoluses = userBoluses;
    }

    public List<Basal> getUserBasal() {
        return userBasal;
    }

    public void setUserBasal(List<Basal> userBasal) {
        this.userBasal = userBasal;
    }

    public List<Glucose> getUserGlucose() {
        return userGlucose;
    }

    public void setUserGlucose(List<Glucose> userGlucose) {
        this.userGlucose = userGlucose;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
