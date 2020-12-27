package com.example.debtsapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    private String name;
    private int amount;
    private String description;


    @PrimaryKey(autoGenerate = true)
    private int id;

    public Note(String name, int amount, String description) {
        this.name = name;
        this.amount = amount;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }



}
