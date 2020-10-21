package com.bca.marvelcharacters.model;

public class Hero {

    String id;
    String name;
    String imagePath;
    String description;

    public Hero(String id, String name, String imagePath, String description){
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
