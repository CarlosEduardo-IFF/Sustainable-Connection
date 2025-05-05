package edu.iff.project.sustainableconnection.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User{

    private String matriculation;
    private int avatar;
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    private int credits;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getMatriculation() {
        return matriculation;
    }

    public void setMatriculation(String matriculation) {
        this.matriculation = matriculation;
    }

    
    public Client(){

    }

    public Client(String name, String email, String password, UserRole role, String matriculation, int avatar) {
        super(name, email, password, role);
        this.points = 0;
        this.credits = 0;
        this.avatar = avatar;
        this.matriculation = matriculation;
    }


    public Client(String name, String email, String password, UserRole role, String matriculation, int avatar, int points, int credits) {
        super(name, email, password, role);
        this.points = points;
        this.credits = credits;
        this.avatar = avatar;
        this.matriculation = matriculation;
    }
    
}
