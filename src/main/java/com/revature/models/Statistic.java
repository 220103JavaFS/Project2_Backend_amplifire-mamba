package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stats")
public class Statistic {
    //this class is used to hold statistics on performance for each individual user in the database

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int statId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User statOwner;

    private String categoryName;

    //there are 5 difficulty levels for questions so there will be 10 different columns for each stat to track
    //(questions_correct + questions_attempted) * 5 = 10 columns to hold stats

    //easiest corresponds with $100 (pre price increase) and $200 questions from api
    private int easiestCorrect;
    private int easiestAttempted;

    //easy corresponds with $200 (pre price increase) and $400 questions from api
    private int easyCorrect;
    private int easyAttempted;

    //medium corresponds with $300 (pre price increase) and $600 questions from api
    private int mediumCorrect;
    private int mediumAttempted;

    //hard corresponds with $400 (pre price increase) and $800 questions from api
    private int hardCorrect;
    private int hardAttempted;

    //hardest corresponds with $500 (pre price increase) and $1000 questions from api
    private int hardestCorrect;
    private int hardestAttempted;

    public Statistic() {
    }

    public Statistic(int statId, User statOwner, String categoryName, int easiestCorrect, int easiestAttempted, int easyCorrect, int easyAttempted, int mediumCorrect, int mediumAttempted, int hardCorrect, int hardAttempted, int hardestCorrect, int hardestAttempted) {
        this.statId = statId;
        this.statOwner = statOwner;
        this.categoryName = categoryName;
        this.easiestCorrect = easiestCorrect;
        this.easiestAttempted = easiestAttempted;
        this.easyCorrect = easyCorrect;
        this.easyAttempted = easyAttempted;
        this.mediumCorrect = mediumCorrect;
        this.mediumAttempted = mediumAttempted;
        this.hardCorrect = hardCorrect;
        this.hardAttempted = hardAttempted;
        this.hardestCorrect = hardestCorrect;
        this.hardestAttempted = hardestAttempted;
    }

    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public User getStatOwner() {
        return statOwner;
    }

    public void setStatOwner(User statOwner) {
        this.statOwner = statOwner;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getEasiestCorrect() {
        return easiestCorrect;
    }

    public void setEasiestCorrect(int easiestCorrect) {
        this.easiestCorrect = easiestCorrect;
    }

    public int getEasiestAttempted() {
        return easiestAttempted;
    }

    public void setEasiestAttempted(int easiestAttempted) {
        this.easiestAttempted = easiestAttempted;
    }

    public int getEasyCorrect() {
        return easyCorrect;
    }

    public void setEasyCorrect(int easyCorrect) {
        this.easyCorrect = easyCorrect;
    }

    public int getEasyAttempted() {
        return easyAttempted;
    }

    public void setEasyAttempted(int easyAttempted) {
        this.easyAttempted = easyAttempted;
    }

    public int getMediumCorrect() {
        return mediumCorrect;
    }

    public void setMediumCorrect(int mediumCorrect) {
        this.mediumCorrect = mediumCorrect;
    }

    public int getMediumAttempted() {
        return mediumAttempted;
    }

    public void setMediumAttempted(int mediumAttempted) {
        this.mediumAttempted = mediumAttempted;
    }

    public int getHardCorrect() {
        return hardCorrect;
    }

    public void setHardCorrect(int hardCorrect) {
        this.hardCorrect = hardCorrect;
    }

    public int getHardAttempted() {
        return hardAttempted;
    }

    public void setHardAttempted(int hardAttempted) {
        this.hardAttempted = hardAttempted;
    }

    public int getHardestCorrect() {
        return hardestCorrect;
    }

    public void setHardestCorrect(int hardestCorrect) {
        this.hardestCorrect = hardestCorrect;
    }

    public int getHardestAttempted() {
        return hardestAttempted;
    }

    public void setHardestAttempted(int hardestAttempted) {
        this.hardestAttempted = hardestAttempted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return statId == statistic.statId && easiestCorrect == statistic.easiestCorrect && easiestAttempted == statistic.easiestAttempted && easyCorrect == statistic.easyCorrect && easyAttempted == statistic.easyAttempted && mediumCorrect == statistic.mediumCorrect && mediumAttempted == statistic.mediumAttempted && hardCorrect == statistic.hardCorrect && hardAttempted == statistic.hardAttempted && hardestCorrect == statistic.hardestCorrect && hardestAttempted == statistic.hardestAttempted && Objects.equals(statOwner, statistic.statOwner) && Objects.equals(categoryName, statistic.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statId, statOwner, categoryName, easiestCorrect, easiestAttempted, easyCorrect, easyAttempted, mediumCorrect, mediumAttempted, hardCorrect, hardAttempted, hardestCorrect, hardestAttempted);
    }

    @Override
    public String toString() {
        //to avoid infinite recursion, don't print out stat owner when printing out the statistic
        return "Statistic{" +
                "statId=" + statId +
                //", statOwner=" + statOwner +
                ", categoryName='" + categoryName + '\'' +
                ", easiestCorrect=" + easiestCorrect +
                ", easiestAttempted=" + easiestAttempted +
                ", easyCorrect=" + easyCorrect +
                ", easyAttempted=" + easyAttempted +
                ", mediumCorrect=" + mediumCorrect +
                ", mediumAttempted=" + mediumAttempted +
                ", hardCorrect=" + hardCorrect +
                ", hardAttempted=" + hardAttempted +
                ", hardestCorrect=" + hardestCorrect +
                ", hardestAttempted=" + hardestAttempted +
                '}';
    }
}
