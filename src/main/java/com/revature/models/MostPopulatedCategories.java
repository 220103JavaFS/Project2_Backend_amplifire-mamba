package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class MostPopulatedCategories {
    //the purpose of this class is just to create a table in the database which maps the id numbers and titles of the
    //categories with the most questions. NOTE - the field names here match what is sent from the JService API

    @Id
    private int id;
    private String title;
    private int clues_count;

    public MostPopulatedCategories() {
    }

    public MostPopulatedCategories(int id, String title, int clues_count) {
        this.id = id;
        this.title = title;
        this.clues_count = clues_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClues_count() {
        return clues_count;
    }

    public void setClues_count(int clues_count) {
        this.clues_count = clues_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MostPopulatedCategories that = (MostPopulatedCategories) o;
        return id == that.id && clues_count == that.clues_count && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, clues_count);
    }

    @Override
    public String toString() {
        return "MostPopulatedCategories{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", clues_count=" + clues_count +
                '}';
    }
}
