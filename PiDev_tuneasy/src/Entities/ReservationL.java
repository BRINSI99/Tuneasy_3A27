/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class ReservationL {
    
     private int id;
    private int   logement_id;
    private Float prix_total;
    private Date date_check_in;
    private Date date_check_out;
    private int id_client; 

    public ReservationL() {
    }

    public ReservationL(int id,int id_client, int logement_id, Date date_check_in, Date date_check_out, Float prix_total) {
        this.id = id; 
        this.logement_id = logement_id;
        this.id_client = id_client;
        this.prix_total = prix_total;
        this.date_check_in = date_check_in;
        this.date_check_out = date_check_out;
        this.prix_total = prix_total;
        
    }
     public ReservationL(int id, int logement_id, Float prix_total, Date date_check_in, Date date_check_out) {
        this.id = id;
        this.logement_id = logement_id;
        this.prix_total = prix_total;
        this.date_check_in = date_check_in;
        this.date_check_out = date_check_out;
          
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogement_id() {
        return logement_id;
    }

    public void setLogement_id(int logement_id) {
        this.logement_id = logement_id;
    }

    public Float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(Float prix_total) {
        this.prix_total = prix_total;
    }

    public Date getDate_check_in() {
        return date_check_in;
    }

    public void setDate_check_in(Date date_check_in) {
        this.date_check_in = date_check_in;
    }

    public Date getDate_check_out() {
        return date_check_out;
    }

    public void setDate_check_out(Date date_check_out) {
        this.date_check_out = date_check_out;
    }
     public int getId_client() {
        return id_client;
    }

      public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    @Override
    public String toString() {
        return "ReservationL{" + "id=" + id + ", logement_id=" + logement_id + ", prix_total=" + prix_total + ", date_check_in=" + date_check_in + ", date_check_out=" + date_check_out + '}';
    }
    
    
   
    
}
