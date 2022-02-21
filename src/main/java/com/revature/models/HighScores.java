package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "high_scores")
public class HighScores {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User statOwner;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp gameTime;
    private int score;

    public HighScores(int gameId, User statOwner, Timestamp gameTime, int score) {
        this.gameId = gameId;
        this.statOwner = statOwner;
        this.gameTime = gameTime;
        this.score = score;
    }

    public HighScores() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public User getStatOwner() {
        return statOwner;
    }

    public void setStatOwner(User statOwner) {
        this.statOwner = statOwner;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getGameTime() {
        return gameTime;
    }

    public void setGameTime(Timestamp gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighScores that = (HighScores) o;
        return gameId == that.gameId && score == that.score && Objects.equals(statOwner, that.statOwner) && Objects.equals(gameTime, that.gameTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, statOwner, gameTime, score);
    }

    @Override
    public String toString() {
        return "HighScores{" +
                "gameId=" + gameId +
                //", statOwner=" + statOwner +
                ", gameTime=" + gameTime +
                ", score=" + score +
                '}';
    }
}
