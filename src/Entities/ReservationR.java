/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author BabyViper
 */
public class ReservationR {
    private int id_res_plat;
    private int id_client;
    private int id_plat;
    private int quantity;
    private Date date_reservation;
    private Boolean etat; 
    private String  NomPlat ;
    private String  email ;
    
    public Boolean getEtat() {
        return etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReservationR(int id_res_plat, int id_client, int id_plat, int quantity, Date date_reservation, Boolean etat, String NomPlat, String email) {
        this.id_res_plat = id_res_plat;
        this.id_client = id_client;
        this.id_plat = id_plat;
        this.quantity = quantity;
        this.date_reservation = date_reservation;
        this.etat = etat;
        this.NomPlat = NomPlat;
        this.email = email;
    }
    

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getNomPlat() {
        return NomPlat;
    }

    public void setNomPlat(String NomPlat) {
        this.NomPlat = NomPlat;
    }
    

    public ReservationR(int id_res_plat, int id_client, int id_plat, int quantity, Date date_reservation, Boolean etat) {
        this.id_res_plat = id_res_plat;
        this.id_client = id_client;
        this.id_plat = id_plat;
        this.quantity = quantity;
        this.date_reservation = date_reservation;
        this.etat = etat;
    }

    public ReservationR() {
    }

    public ReservationR(int id_res_plat, int id_client, int id_plat, int quantity, Date date_reservation) {
        this.id_res_plat = id_res_plat;
        this.id_client = id_client;
        this.id_plat = id_plat;
        this.quantity = quantity;
        this.date_reservation = date_reservation;
    }

    public ReservationR(int id_res_plat, int id_client, int id_plat, int quantity, Date date_reservation, String NomPlat) {
        this.id_res_plat = id_res_plat;
        this.id_client = id_client;
        this.id_plat = id_plat;
        this.quantity = quantity;
        this.date_reservation = date_reservation;
        this.NomPlat = NomPlat;
    }

    public ReservationR(int id_res_plat, int id_client, int id_plat, int quantity, Date date_reservation, Boolean etat, String NomPlat) {
        this.id_res_plat = id_res_plat;
        this.id_client = id_client;
        this.id_plat = id_plat;
        this.quantity = quantity;
        this.date_reservation = date_reservation;
        this.etat = etat;
        this.NomPlat = NomPlat;
    }

    public int getId_plat() {
        return id_plat;
    }

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    

    public int getId_res_plat() {
        return id_res_plat;
    }

    public void setId_res_plat(int id_res_plat) {
        this.id_res_plat = id_res_plat;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    @Override
    public String toString() {
        return "ReservationR{" + "id_res_plat=" + id_res_plat + ", id_client=" + id_client + ", id_plat=" + id_plat + ", quantity=" + quantity + ", date_reservation=" + date_reservation + ", etat=" + etat + '}';
    }
    
}
