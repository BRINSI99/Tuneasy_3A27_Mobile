/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author asus
 */
public class Materiel {
    private int id;
    private String nom_materiel;
    private String description_materiel;
    private float prix_materiel;
    private String photo_materiel;
    private int location_id;

    public Materiel() {
    }

    public Materiel(int id, String nom_materiel, String description_materiel, float prix_materiel, String photo_materiel, int location_id) {
        this.id = id;
        this.nom_materiel = nom_materiel;
        this.description_materiel = description_materiel;
        this.prix_materiel = prix_materiel;
        this.photo_materiel = photo_materiel;
        this.location_id = location_id;
    }

    public Materiel(String nom_materiel, String description_materiel, float prix_materiel) {
        this.nom_materiel = nom_materiel;
        this.description_materiel = description_materiel;
        this.prix_materiel = prix_materiel;
    }

    
    public Materiel(String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_materiel() {
        return nom_materiel;
    }

    public void setNom_materiel(String nom_materiel) {
        this.nom_materiel = nom_materiel;
    }

    public String getDescription_materiel() {
        return description_materiel;
    }

    public void setDescription_materiel(String description_materiel) {
        this.description_materiel = description_materiel;
    }

    public float getPrix_materiel() {
        return prix_materiel;
    }

    public void setPrix_materiel(float prix_materiel) {
        this.prix_materiel = prix_materiel;
    }

    public String getPhoto_materiel() {
        return photo_materiel;
    }

    public void setPhoto_materiel(String photo_materiel) {
        this.photo_materiel = photo_materiel;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", nom_materiel=" + nom_materiel + ", description_materiel=" + description_materiel + ", prix_materiel=" + prix_materiel + ", photo_materiel=" + photo_materiel + ", location_id=" + location_id + '}';
    }
    
    
}
