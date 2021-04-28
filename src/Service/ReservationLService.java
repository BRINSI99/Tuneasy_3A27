/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DataBase.database;
import Entities.Logement;
import Entities.ReservationL;
import Entities.ReservationR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ReservationLService {
     private Connection cnx;
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
     public void ajouter(ReservationL r) { 
    String req="insert into reservation (logement_id,prix_total,id_client,date_check_in,date_check_out) values ('"+r.getLogement_id()+"','"+r.getPrix_total()+"','"+r.getId_client()+"','"+r.getDate_check_in()+"','"+r.getDate_check_out()+"')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } 
        catch (SQLException ex) {
            Logger.getLogger(ReservationLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      public  ReservationLService() {
       cnx = database.getInstance().getConnection();
        
    }
      
        public ResultSet getReservationL() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id ,id_client,logement_id ,date_check_in,date_check_out,prix_total FROM reservation");
        
     
    
      
        
        return rs;
    }
        public void delete(ReservationL t) throws SQLException {
        st = cnx.createStatement(); 
        String requeteDelete = "DELETE FROM `reservation` WHERE `id` ='" + t.getId()+ "';";
        st.executeUpdate(requeteDelete);
    }
        
         public void update(ReservationL t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `reservation` SET `date_check_in` = '" + t.getDate_check_in() + "', `date_check_out` = '" + t.getDate_check_out() +"', `prix_total` = '" + t.getPrix_total() + "' WHERE `reservation`.`id` = '" + t.getId() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
         
         public ResultSet getUserR(int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery( 
                "SELECT email,firstname FROM user WHERE id ='"+id+"';");
                         
   
        
        return rs;
    }
         
         public ResultSet getlg(int id) throws SQLException{
        st = cnx.createStatement(); 
        ResultSet rs = st.executeQuery(
                "SELECT titre FROM logement WHERE id ='"+id+"';");
                         
      
        
        return rs;
    }
         public ResultSet getReservationR2() throws SQLException{
        st = cnx.createStatement(); 
        ResultSet rs = st.executeQuery(
              
     
   
      "SELECT COUNT(id),DATE(date_check_in) AS date FROM reservation GROUP BY date"); 

        
        return rs;
    }
            public ResultSet getReservationR3() throws SQLException{
        st = cnx.createStatement(); 
        ResultSet rs = st.executeQuery( 
            
      "SELECT COUNT(r.id),l.titre  FROM reservation r   inner join logement l  on r.logement_id=l.id  GROUP BY r.logement_id"); 
       

        
        return rs; 
    }
             public void updateValide(Logement  t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `logement` SET `valide` = '" + 1 + "' WHERE `logement`.`id` = '" + t.getId() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
     
}
