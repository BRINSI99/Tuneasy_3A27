/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;



import DataBase.database;
import Entities.Logement;

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
 * @author ASUS
 */
public class LogementService implements Iservice<Logement> {
    
     private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public LogementService() {
        cnx = database.getInstance().getConnection();
    }
    
    

    @Override
    public void ajouter(Logement l) { 
        String req="insert into logement (titre,description,type_logement,adresse,ville,n_chambres,n_lits,n_salles_de_bains,prix_nuit,animaux_acceptes,fumeur,file_name,id_client) values ('"+l.getTitre()+"','"+l.getDescription()+"','"+l.getType_logement()+"','"+l.getAdresse()+"','"+l.getVille()+"','"+l.getN_chambres()+"','"+l.getN_lits()+"','"+l.getN_salles_de_bains()+"','"+l.getPrix_nuit()+"','"+l.getAnimaux_acceptes()+"','"+l.getFumeur()+"','"+l.getFile_name()+"','"+l.getId_client()+"')";
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
        } 
        catch (SQLException ex) {
            Logger.getLogger(LogementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Logement> getAll() {
          ArrayList<Logement> logements = new ArrayList<>();
    String req = "SELECT * FROM logement";
    
    try{
         st=cnx.createStatement();
         rs = st.executeQuery(req);
         while (rs.next()){
          Logement l = new Logement();
          l.setId(rs.getInt("id"));
          l.setTitre(rs.getString("titre"));
          l.setDescription(rs.getString("description"));
          l.setType_logement(rs.getString("type"));
          l.setAdresse(rs.getString("adresse"));
          l.setVille(rs.getString("ville"));
          l.setFile_name(rs.getString("file_name"));
          l.setN_chambres(rs.getInt("n_chambres"));
          l.setN_lits(rs.getInt("n_lits"));
          l.setN_salles_de_bains(rs.getInt("n_salles_de_bains"));
          l.setPrix_nuit(rs.getFloat("prix_nuit"));
          l.setAnimaux_acceptes(rs.getInt("animaux_acceptes"));
          l.setValide(rs.getInt("valide"));
          l.setFumeur(rs.getInt("fumeur"));
           l.setFumeur(rs.getInt("id_client"));
       
         logements.add(l);
         }
    
    }
    catch (SQLException ex){
        Logger.getLogger(LogementService.class.getName()).log(Level.SEVERE, null, ex);
    }   
    
    
    return logements ;
    }
    public ResultSet getLogement() throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id,titre,description,type_logement,adresse,ville,n_chambres,n_lits,n_salles_de_bains,prix_nuit,animaux_acceptes,fumeur,file_name,valide,id_client FROM logement");
       
        return rs;
    }
     public void update(Logement t) throws SQLException {
        st = cnx.createStatement();
        String requeteUpdate = "UPDATE `logement` SET `titre` = '" + t.getTitre() + "', `description` = '" + t.getDescription() + "', `type_logement` = '" + t.getType_logement() + "', `adresse` = '" + t.getAdresse() + "', `ville` = '" + t.getVille() +"', `file_name` = '" + t.getFile_name() +"', `n_chambres` = '" + t.getN_chambres() +"', `n_lits` = '" + t.getN_lits() +"', `n_salles_de_bains` = '" + t.getN_salles_de_bains() +"',`prix_nuit` = '" + t.getPrix_nuit() +"',`animaux_acceptes` = '" + t.getAnimaux_acceptes() +"',`fumeur` = '" + t.getFumeur() + "' WHERE `logement`.`id` = '" + t.getId() + "' ;";
        st.executeUpdate(requeteUpdate);
    }
      public void delete(Logement t) throws SQLException {
        st = cnx.createStatement();
        String requeteDelete = "DELETE FROM `logement` WHERE `id` ='" + t.getId()+ "';";
        st.executeUpdate(requeteDelete);
    }
      
        public ResultSet getLogementById( int id) throws SQLException{
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT id,titre,description,type_logement,adresse,ville,n_chambres,n_lits,n_salles_de_bains,prix_nuit,animaux_acceptes,fumeur,file_name,valide,id_client  FROM logement where id = '" +id+"';");
    
       
        
        return rs;
    }
}
