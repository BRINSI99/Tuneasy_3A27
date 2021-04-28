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
public class Plat {
   private int id_plat;
    private int id_resto;
    private String nom;
    private String composition;
    private float prix;
    private String type;
    private String photo;
    private String nomR;

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public Plat(int id_plat, int id_resto, String nom, String composition, float prix, String type, String photo, String nomR) {
        this.id_plat = id_plat;
        this.id_resto = id_resto;
        this.nom = nom;
        this.composition = composition;
        this.prix = prix;
        this.type = type;
        this.photo = photo;
        this.nomR = nomR;
    }
    

    

    public Plat() {
    }

    public Plat(int id_plat, int id_rest, String nom, String composition, float prix, String type, String photo) {
        this.id_plat = id_plat;
        this.id_resto = id_rest;
        this.nom = nom;
        this.composition = composition;
        this.prix = prix;
        this.type = type;
        this.photo = photo;
    }

    public Plat(int id_rest, String nom, String composition, float prix, String type, String photo) {
        this.id_resto = id_rest;
        this.nom = nom;
        this.composition = composition;
        this.prix = prix;
        this.type = type;
        this.photo = photo;
    }

    public Plat(String nom, String composition, float prix, String type, String photo) {
        this.nom = nom;
        this.composition = composition;
        this.prix = prix;
        this.type = type;
        this.photo = photo;
    }

    
    public int getId_resto() {
        return id_resto;
    }

    public void setId_resto(int id_rest) {
        this.id_resto = id_rest;
    }

    public int getId_plat() {
        return id_plat;
    }

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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
    
}

