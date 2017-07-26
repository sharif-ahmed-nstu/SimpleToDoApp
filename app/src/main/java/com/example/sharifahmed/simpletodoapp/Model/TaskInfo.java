package com.example.sharifahmed.simpletodoapp.Model;

/**
 * Created by SHARIF AHMED on 25-Jul-17.
 */
public class TaskInfo {

    private int id;
    private String name;
    private String description;
    private String dateCreated;
    private String dateUpdated;


    public TaskInfo() {
    }


    public TaskInfo(int id, String name, String description, String dateCreated, String dateUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }



    public TaskInfo(String name, String description, String dateCreated, String dateUpdated) {
        this.name = name;
        this.dateUpdated = dateUpdated;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
