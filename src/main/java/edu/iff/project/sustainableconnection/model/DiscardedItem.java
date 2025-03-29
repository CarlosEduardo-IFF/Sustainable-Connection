package edu.iff.project.sustainableconnection.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "discarded_items")
public class DiscardedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private DropOffPoint dropOffPoint;

    private LocalDateTime discardDate;
    private int pointsEarned;

    @ManyToOne
    private DiscardedItemCategory category;

    public DiscardedItem() {}

    public DiscardedItem(User user, DropOffPoint dropOffPoint, LocalDateTime discardDate, int pointsEarned, DiscardedItemCategory category) {
        this.user = user;
        this.dropOffPoint = dropOffPoint;
        this.discardDate = discardDate;
        this.pointsEarned = pointsEarned;
        this.category = category;
    }

    public Long getId() { 
        return id; 
    }
    public User getUser() { 
        return user; 
    }
    public DropOffPoint getDropOffPoint() { 
        return dropOffPoint; 
    }
    public LocalDateTime getDiscardDate() { 
        return discardDate; 
    }
    public int getPointsEarned() { 
        return pointsEarned; 
    }
    public DiscardedItemCategory getCategory() { 
        return category; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }
    public void setUser(User user) { 
        this.user = user; 
    }
    public void setDropOffPoint(DropOffPoint dropOffPoint) { 
        this.dropOffPoint = dropOffPoint; 
    }
    public void setDiscardDate(LocalDateTime discardDate) { 
        this.discardDate = discardDate; 
    }
    public void setPointsEarned(int pointsEarned) { 
        this.pointsEarned = pointsEarned; 
    }
    public void setCategory(DiscardedItemCategory category) { 
        this.category = category; 
    }
}
