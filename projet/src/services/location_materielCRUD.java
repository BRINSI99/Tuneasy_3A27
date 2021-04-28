/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.location_materiel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import tools.MyConnection;
/**
 *
 * @author asus
 */
public class location_materielCRUD {
    public void addLocation_Materiel(location_materiel l) {
        try {
            String requete = "INSERT INTO `location_materiel`( `id_location`, `materiel_id`, `total_location`) VALUES (?,?,?)";

            PreparedStatement loc = MyConnection.getInstance().getCnx().prepareStatement(requete);

            loc.setInt(1, l.getId_location());
            loc.setInt(2, l.getMateriel_id());
            loc.setFloat(3, l.getTotal_location());
            loc.executeUpdate();

            System.out.println("location materiel ajoutee!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean delete(int id) throws SQLException {
        int retour = JOptionPane.showConfirmDialog(null,
                "OK - Annuler",
                "Delete location materiel",
                JOptionPane.OK_CANCEL_OPTION);
        System.out.println("retour :" + retour);
        if (retour == 0) {
            PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement("DELETE FROM location_materiel WHERE id_location ='" + id + "' ;");
            pre.executeUpdate();
        }
        return true;
    }

    public ObservableList<location_materiel> readAllLocation_materiel() throws SQLException {
        ObservableList oblistdisc = FXCollections.observableArrayList();

        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("select * from location_materiel ;");
        while (rs.next()) {
            int id_location = rs.getInt("id_location");
            int materiel_id = rs.getInt("materiel_id");
            float total_location = rs.getFloat("total_location");

            location_materiel l = new location_materiel(id_location, materiel_id, total_location);
            oblistdisc.add(l);

        }
        return oblistdisc;
    }

    public boolean update(int id_location, float total_location) throws SQLException {
        PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement("  UPDATE `location_materiel` SET `total_location`='" + total_location + "' WHERE id_location='" + id_location + "' ;");

        JOptionPane.showMessageDialog(null, "Location modifiee avec succes");
        pre.executeUpdate();
        return true;
    }

    public boolean affiche(int id) throws SQLException {
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `location_materiel` WHERE materiel_id='" + id + "';");
        while (rs.next()) {
            int comp_id = rs.getInt("materiel_id");
            if (comp_id != 0) {
                return true;
            }
        }
        return false;
    }

    public float returnID(int id) throws SQLException {
        float nb = 0;
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `location_materiel` WHERE materiel_id='" + id + "';");
        while (rs.next()) {
            nb = rs.getFloat("total_location");
            return nb;
        }
        return nb;
    }

    public float getIdLocMat(int id) throws SQLException {
        float nb = 0;
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `location_materiel` WHERE materiel_id='" + id + "';");
        while (rs.next()) {
            nb = rs.getFloat("id");
            return nb;
        }
        return nb;
    }
}
