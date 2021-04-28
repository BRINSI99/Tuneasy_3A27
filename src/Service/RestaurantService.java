/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.database;
import Entities.Restaurant;
import Interface.Iservice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BabyViper
 */
public class RestaurantService implements Iservice<Restaurant> {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public RestaurantService() {
       cnx = database.getInstance().getConnection();
        
    }
    
    
    
    @Override
    public void ajouter(Restaurant r) {
    String req="insert into restaurants (nom,adresse,ville,description,type,photo,num_tel,email,note) values ('"+r.getNom()+"','"+r.getDescription()+"','"+r.getAdresse()+"','"+r.getVille()+"','"+r.getType()+"','"+r.getPhoto()+"','"+r.getNum_tel()+"','"+r.getEmail()+"','"+r.getNote()+"')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } 
        catch (SQLException ex) {
            Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public ArrayList<Restaurant> getAll() {
        ArrayList<Restaurant> restos = new ArrayList<>();
    String req = "SELECT * FROM restaurants";
    
    try{
         st=cnx.createStatement();
         rs = st.executeQuery(req);
         while (rs.next()){
          Restaurant r = new Restaurant();
          r.setId_resto(rs.getInt("id_resto"));
          r.setNom(rs.getString("nom"));
          r.setAdresse(rs.getString("adresse"));
          
          r.setVille(rs.getString("ville"));
          r.setDescription(rs.getString("description"));
          r.setType(rs.getString("type"));
          r.setPhoto(rs.getString("photo"));
          r.setNum_tel(rs.getInt("num_tel"));
          r.setEmail(rs.getString("email"));
          r.setNote(rs.getInt("note"));
         restos.add(r);
         }
    
    }
    catch (SQLException ex){
        Logger.getLogger(RestaurantService.class.getName()).log(Level.SEVERE, null, ex);
    }   
    
    
    return restos ;
    }
    public ResultSet getRestaurant() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id_resto,nom,adresse,ville,description,type,photo,num_tel,email,note FROM restaurants");
       
        return rs;
    }
 
    public void delete(Restaurant t) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `restaurants` WHERE `id_resto` ='" + t.getId_resto()+ "';";
        st.executeUpdate(requeteDelete);
    }
    public void update(Restaurant t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `restaurants` SET `nom` = '" + t.getNom() + "', `adresse` = '" + t.getAdresse() + "', `ville` = '" + t.getVille() + "', `description` = '" + t.getDescription() + "', `type` = '" + t.getType() +"', `photo` = '" + t.getPhoto() +"', `num_tel` = '" + t.getNum_tel() +"', `email` = '" + t.getEmail() +"', `note` = '" + t.getNote() + "' WHERE `restaurants`.`id_resto` = '" + t.getId_resto() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
}
