package com.example.roommvvm.repository.room;


import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "notes")
public class Entity {
    @PrimaryKey(autoGenerate = true)
    int id;

    String title;

    String desc;

    int priority;

    public Entity(String title, String desc, int priority) {
        this.title = title;
        this.desc = desc;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}
