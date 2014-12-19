package cz.raptors.dianotes.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by vamvda1 on 25.3.14.
 */
@Entity
@Table(name= "BASAL")
public class Basal extends AbstractEntity {

    @Column(name = "BASAL_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date basalTime;

    @Column(name = "BASAL_VALUE", scale = 2, precision = 5)
    private BigDecimal basalValue;

    @ManyToOne
    @JoinColumn(name = "HEALTHCARD_ID")
    private HealthCard healthCard;

    public Date getBasalTime() {
        return basalTime;
    }

    public void setBasalTime(Date basalTime) {
        this.basalTime = basalTime;
    }

    public BigDecimal getBasalValue() {
        return basalValue;
    }

    public void setBasalValue(BigDecimal basalValue) {
        this.basalValue = basalValue;
    }

    public HealthCard getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(HealthCard healthCard) {
        this.healthCard = healthCard;
    }

}
