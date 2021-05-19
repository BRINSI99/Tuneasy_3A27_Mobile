/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.entities;

import java.util.Date;

/**
 *
 * @author BabyViper
 */
public class ReservationR {
    private int id;
    private String nomplat;
    private String email;
    private String date;
    private int Qte;
   

    public ReservationR() {
    }

    public ReservationR(int id, String nomplat, String email, String date, int Qte) {
        this.id = id;
        this.nomplat = nomplat;
        this.email = email;
        this.date = date;
        this.Qte = Qte;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomplat() {
        return nomplat;
    }

    public void setNomplat(String nomplat) {
        this.nomplat = nomplat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    @Override
    public String toString() {
        return "ReservationR{" + "id=" + id + ", nomplat=" + nomplat + ", email=" + email + ", date=" + date + ", Qte=" + Qte + '}';
    }

    public String getNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   
    
    
    
    
    
}
