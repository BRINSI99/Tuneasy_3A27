/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.database;
import Entities.ReservationR;
import Entities.Restaurant;



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
public class ReservationRService {
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public void ajouter(ReservationR r) {
    String req="insert into reservation_plats (id_client,id_plat,quantity,date_reservation,etat) values ('"+r.getId_client()+"','"+r.getId_plat()+"','"+r.getQuantity()+"','"+r.getDate_reservation()+"','"+0+"')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } 
        catch (SQLException ex) {
            Logger.getLogger(ReservationRService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public  ReservationRService() {
       cnx = database.getInstance().getConnection();
        
    }
    
    public ResultSet getReservationR() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id_res_plat , id_client ,id_plat,quantity, date_reservation,etat FROM reservation_plats");
    
       //"SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat ");
       //"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
    
    public ResultSet getReservationR5(int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
               // "SELECT id_res_plat , id_client ,id_plat,quantity, date_reservation,etat FROM reservation_plats");
    
       "SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat WHERE r.id_client = '" + id + "';");
       //"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
    
     public ResultSet getReservationR7() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
               // "SELECT id_res_plat , id_client ,id_plat,quantity, date_reservation,etat FROM reservation_plats");
    
       "SELECT  reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation, etat, plats.nom, user.email FROM reservation_plats inner join plats on reservation_plats.id_plat = plats.id_plat inner join user on reservation_plats.id_client=user.id  ;");
       //"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
    
     public void update(ReservationR t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `reservation_plats` SET `quantity` = '" + t.getQuantity() + "', `date_reservation` = '" + t.getDate_reservation() + "',`etat` = '" + 0 + "' WHERE `reservation_plats`.`id_res_plat` = '" + t.getId_res_plat() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
     public void updateEtat(ReservationR t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `reservation_plats` SET `etat` = '" + 1 + "' WHERE `reservation_plats`.`id_res_plat` = '" + t.getId_res_plat() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
     
     
     
public void delete(ReservationR t) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `reservation_plats` WHERE `id_res_plat` ='" + t.getId_res_plat()+ "';";
        st.executeUpdate(requeteDelete);
    }

public ResultSet getUserR(int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT email,firstname FROM user WHERE id ='"+id+"';");
                         
        //"SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"', LEFT JOIN ON reservation_plats.id_client = user.id;");
   //  "SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,plats.nom,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"' INNER JOIN ON reservation_plats.id_client = user.id INNER JOIN ON reservation_plats.id_plat = plats.id_plat  ");
       //"SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat ");
       //"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
public ResultSet getPlt(int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT nom FROM plats WHERE id_plat ='"+id+"';");
                         
        //"SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"', LEFT JOIN ON reservation_plats.id_client = user.id;");
   //  "SELECT reservation_plats.id_res_plat , reservation_plats.id_client ,reservation_plats.id_plat,reservation_plats.quantity, reservation_plats.date_reservation,plats.nom,user.email,user.firstname FROM reservation_plats WHERE id_res_plat ='"+id+"' INNER JOIN ON reservation_plats.id_client = user.id INNER JOIN ON reservation_plats.id_plat = plats.id_plat  ");
       //"SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat ");
       //"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
 public ResultSet getReservationR2() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
               // "SELECT id_res_plat , id_client ,id_plat,quantity, date_reservation,etat FROM reservation_plats");
    
      //"SELECT  r.id_res_plat , r.id_client ,r.id_plat, p.nom,r.quantity, r.date_reservation FROM reservation_plats as r inner join plats as p on r.id_plat = p.id_plat ");
      "SELECT COUNT(id_res_plat),DATE(date_reservation) AS date FROM reservation_plats GROUP BY date"); 
//"SELECT  m.id, m.nom , m.marque, m.quantite,m.image,m.id_categorie,m.id_etat,c.nom,e.type_etat FROM materiel as m left join categorie as c on m.id_categorie = c.id left join etat as e on m.id_etat=e.id";
        
        return rs;
    }
}


    