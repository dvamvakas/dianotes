package cz.raptors.dianotes.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by vamvda1 on 25.3.14.
 */
@Entity
@Table(name = "GLUCOSE")
public class Glucose extends AbstractEntity {

    @Column(name = "GLUCOSE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date glucoseTime;

    @Column(name = "GLUCOSE_VALUE")
    private BigDecimal glucoseValue;

    @ManyToOne
    @JoinColumn(name = "HEALTHCARD_ID")
    private HealthCard healthCard;

    public Date getGlucoseTime() {
        return glucoseTime;
    }

    public void setGlucoseTime(Date glucoseTime) {
        this.glucoseTime = glucoseTime;
    }

    public BigDecimal getGlucoseValue() {
        return glucoseValue;
    }

    public void setGlucoseValue(BigDecimal glucoseValue) {
        this.glucoseValue = glucoseValue;
    }

    public HealthCard getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(HealthCard healthCard) {
        this.healthCard = healthCard;
    }
}
