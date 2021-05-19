/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tuneasy.entities;


import java.util.Comparator;

public class Competition {
    private int id;
    private String name;
    private String description;
    private String category;
    private int places;
    private String address;
    private String date;
    private String image;

    public Competition() {
    }

    public Competition(String name, String description, String category, int places, String address, String date, String image) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.places = places;
        this.address = address;
        this.date = date;
        this.image = image;
    }

    public Competition(int id, String name, String description, String category, int places, String address, String date, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.places = places;
        this.address = address;
        this.date = date;
        this.image = image;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Comparator<Competition> nameComparator = new Comparator<Competition>() {
        @Override
        public int compare(Competition o1, Competition o2) {
            return (int) (o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        }
    };
    public static Comparator<Competition> categoryComparator = new Comparator<Competition>() {
        @Override
        public int compare(Competition o1, Competition o2) {
            return (int) (o1.getCategory().toLowerCase().compareTo(o2.getCategory().toLowerCase()));
        }
    };
}

