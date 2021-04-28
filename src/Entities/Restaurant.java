/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author BabyViper
 */
public class Restaurant {
    private int id_resto;
    private String nom;
    private String adresse;
    private String ville;
    private String description;
    private String type;
    private String photo;
    private int num_tel;
    private String email;
    private int note;

    public Restaurant(String nom, String adresse, String ville, String description, String type, String photo, int num_tel, String email, int note) {
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.description = description;
        this.type = type;
        this.photo = photo;
        this.num_tel = num_tel;
        this.email = email;
        this.note = note;
    }

    public Restaurant(int id_resto, String nom, String adresse, String ville, String description, String type, String photo, int num_tel, String email, int note) {
        this.id_resto = id_resto;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.description = description;
        this.type = type;
        this.photo = photo;
        this.num_tel = num_tel;
        this.email = email;
        this.note = note;
    }
    

    public Restaurant() {}

    public int getId_resto() {
        return id_resto;
    }

    public void setId_resto(int id_resto) {
        this.id_resto = id_resto;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id_resto=" + id_resto + ", nom=" + nom + ", adresse=" + adresse + ", ville=" + ville + ", description=" + description + ", type=" + type + ", photo=" + photo + ", num_tel=" + num_tel + ", email=" + email + ", note=" + note + '}';
    }
    
    
}
