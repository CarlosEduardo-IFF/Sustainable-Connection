package edu.iff.project.sustainableconnection.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "redemption_record")
public class RedemptionRecord {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne
	private User user;
	
    @ManyToOne
	private RewardItem rewardItem;
    
	private LocalDateTime redemptionDate;
	
	 public RedemptionRecord() {}

	 public RedemptionRecord(User user, RewardItem rewardItem, LocalDateTime redemptionDate) {
	     this.user = user;
	     this.rewardItem = rewardItem;
	     this.redemptionDate = redemptionDate;
	 }
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RewardItem getRewardItem() {
		return rewardItem;
	}

	public void setRewardItem(RewardItem rewardItem) {
		this.rewardItem = rewardItem;
	}

	public LocalDateTime getRedempionDate() {
		return redemptionDate;
	}

	public void setRedempionDate(LocalDateTime redemptionDate) {
		this.redemptionDate = redemptionDate;
	}

}
