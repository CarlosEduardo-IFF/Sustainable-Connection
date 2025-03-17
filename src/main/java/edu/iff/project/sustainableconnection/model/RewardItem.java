package edu.iff.project.sustainableconnection.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reward_items")
public class RewardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private int costInPoints;
    private int quantity;

    public RewardItem() {
        
    }

    public RewardItem(String name, String description, int costInPoints, int quantity) {
        this.name = name;
        this.description = description;
        this.costInPoints = costInPoints;
        this.quantity = quantity;
    }

    public Long getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public String getDescription() { 
        return description; 
    }
    public int getCostInPoints() { 
        return costInPoints; 
    }
    public int getQuantity() { 
        return quantity; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }
    public void setCostInPoints(int costInPoints) { 
        this.costInPoints = costInPoints; 
    }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }
}
