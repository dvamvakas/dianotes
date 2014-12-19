package cz.raptors.dianotes.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cz.raptors.dianotes.entities.HealthCard;

public class GlucoseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932488875859612081L;
	private Date glucoseTime;
	private BigDecimal glucoseValue;
	private HealthCard healthCard;
	
	public GlucoseModel() {
		
	}

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
