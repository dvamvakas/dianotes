package cz.raptors.dianotes.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by vamvda1 on 25.3.14.
 */
@Entity
@Table(name = "BOLUS")
public class Bolus extends AbstractEntity {

    @Column(name = "BOLUS__TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bolusTime;

    @Column(name = "BOLUS_VALUE",scale = 2, precision = 5)
    private BigDecimal bolusValue;

    @ManyToOne
    @JoinColumn(name = "HEALTHCARD_ID")
    private HealthCard healthCard;

    public Date getBolusTime() {
        return bolusTime;
    }

    public void setBolusTime(Date bolusTime) {
        this.bolusTime = bolusTime;
    }

    public BigDecimal getBolusValue() {
        return bolusValue;
    }

    public void setBolusValue(BigDecimal bolusValue) {
        this.bolusValue = bolusValue;
    }

    public HealthCard getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(HealthCard healthCard) {
        this.healthCard = healthCard;
    }
}
