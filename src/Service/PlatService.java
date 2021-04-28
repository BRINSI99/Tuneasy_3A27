/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.database;
import Entities.Plat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BabyViper
 */
public class PlatService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    public PlatService() {
       cnx = database.getInstance().getConnection();
        
    }
    
    public ResultSet getPlat() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id_plat , id_resto , nom, composition, prix, type, photo FROM plats");
    
       
        
        return rs;
    }
    
    public void delete(Plat t) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `plats` WHERE `id_plat` ='" + t.getId_plat()+ "';";
        st.executeUpdate(requeteDelete);
    }
    
    public void ajouter(Plat r) {
    String req="insert into plats (id_resto,nom,composition,prix,type,photo) values ('"+r.getId_resto()+"','"+r.getNom()+"','"+r.getComposition()+"','"+r.getPrix()+"','"+r.getType()+"','"+r.getPhoto()+"')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } 
        catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void update(Plat t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `plats` SET `nom` = '" + t.getNom() + "', `id_resto` = '" + t.getId_resto() + "', `composition` = '" + t.getComposition() + "', `prix` = '" +t.getPrix() + "', `type` = '" + t.getType() +"', `photo` = '" + t.getPhoto() +"' WHERE `plats`.`id_plat` = '" + t.getId_plat() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
     
     public ResultSet getPlatById( int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id_plat , id_resto , nom, composition, prix, type, photo FROM plats where id_resto = '" +id+"';");
    
       
        
        return rs;
    }
     
    public ResultSet getPlat2() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT plats.id_plat,plats.id_resto,plats.nom,plats.composition,plats.prix,plats.type,plats.photo,restaurants.nom FROM plats INNER JOIN restaurants ON plats.id_resto = restaurants.id_resto ");
    
       
        
        return rs;
    }
    
    public ResultSet getnomR() throws SQLException{
       
        st = cnx.createStatement();
        ResultSet rs2 = st.executeQuery(
                "SELECT nom FROM restaurants");
           
        
        return rs2;
    
    }
    public ResultSet getidR(String s) throws SQLException{
       
        st = cnx.createStatement();
        ResultSet rs2 = st.executeQuery(
                "SELECT id_resto FROM restaurants where nom='"+s+"';");
           
        
        return rs2;
    
    }

public ResultSet getnomR2(int s) throws SQLException{
       
        st = cnx.createStatement();
        ResultSet rs2 = st.executeQuery(
                "SELECT nom FROM restaurants where id_resto='"+s+"';");
           
        
        return rs2;
    
    }

}
