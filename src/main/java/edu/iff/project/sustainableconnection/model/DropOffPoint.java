package edu.iff.project.sustainableconnection.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "drop_off_points")
public class DropOffPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    //@JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Address address;

    public DropOffPoint() {}

    public DropOffPoint(String name, String description, Address address) {
        this.name = name;
        this.description = description;
        this.address = address;
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
    public Address getAddress() { 
        return address; 
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
    public void setAddress(Address address) { 
        this.address = address; 
    }
}