package edu.iff.project.sustainableconnection.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "discarded_item_category")
public class DiscardedItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	private int pointsPerItens;
	
    public DiscardedItemCategory() {}

    public DiscardedItemCategory(String name, int pointsPerItens) {
        this.setName(name);
        this.setPointsPerItens(pointsPerItens);
    }

	public int getPointsPerItens() {
		return pointsPerItens;
	}

	public void setPointsPerItens(int pointsPerItens) {
		this.pointsPerItens = pointsPerItens;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
