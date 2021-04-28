/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class location_materiel {
    private int id_location, materiel_id;
    private float total_location;

    public location_materiel() {
    }

    public location_materiel(int id_location, int materiel_id, float total_location) {
        this.id_location = id_location;
        this.materiel_id = materiel_id;
        this.total_location = total_location;
    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public int getMateriel_id() {
        return materiel_id;
    }

    public void setMateriel_id(int materiel_id) {
        this.materiel_id = materiel_id;
    }

    public float getTotal_location() {
        return total_location;
    }

    public void setTotal_location(float total_location) {
        this.total_location = total_location;
    }

    @Override
    public String toString() {
        return "location_materiel{" + "id_location=" + id_location + ", materiel_id=" + materiel_id + ", total_location=" + total_location + '}';
    }

    
    
    
}
