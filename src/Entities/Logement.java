/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class Logement {
    private int id;
    private String file_name;
    private String titre;
    private String description;
    private String type_logement;
    private String adresse;
    private String ville;
    private int n_chambres;
    private int n_lits;
    private int n_salles_de_bains;
    private float prix_nuit;
    private int animaux_acceptes;
    private int valide;
    private String updated_at;
    private int fumeur;
    private int id_client; 

    public Logement() {
    }

    public Logement(int id, String file_name, String titre, String description, String type_logement, String adresse, String ville, int n_chambres, int n_lits, int n_salles_de_bains, float prix_nuit, int animaux_acceptes, int valide,  int fumeur) {
        this.id = id;
        this.file_name = file_name;
        this.titre = titre;
        this.description = description;
        this.type_logement = type_logement;
        this.adresse = adresse;
        this.ville = ville;
        this.n_chambres = n_chambres;
        this.n_lits = n_lits;
        this.n_salles_de_bains = n_salles_de_bains;
        this.prix_nuit = prix_nuit;
        this.animaux_acceptes = animaux_acceptes;
        this.valide = valide;
        
        this.fumeur = fumeur;
    }
    /* public Logement(int id, String file_name, String titre, String description, String type_logement, String adresse, String ville, int n_chambres, int n_lits, int n_salles_de_bains, float prix_nuit, int animaux_acceptes, int valide,  int fumeur) {
        this.id = id;
        this.file_name = file_name;
        this.titre = titre;
        this.description = description;
        this.type_logement = type_logement;
        this.adresse = adresse;
        this.ville = ville;
        this.n_chambres = n_chambres;
        this.n_lits = n_lits;
        this.n_salles_de_bains = n_salles_de_bains;
        this.prix_nuit = prix_nuit;
        this.animaux_acceptes = animaux_acceptes;
        this.valide = valide;
        
        this.fumeur = fumeur;
    }*/
     public Logement(int id, String file_name, String titre, String description, String type_logement, String adresse, String ville, int n_chambres, int n_lits, int n_salles_de_bains, float prix_nuit, int animaux_acceptes, int valide,  int fumeur,int id_client) {
        this.id = id;
        this.file_name = file_name;
        this.titre = titre;
        this.description = description;
        this.type_logement = type_logement;
        this.adresse = adresse;
        this.ville = ville;
        this.n_chambres = n_chambres;
        this.n_lits = n_lits;
        this.n_salles_de_bains = n_salles_de_bains;
        this.prix_nuit = prix_nuit;
        this.animaux_acceptes = animaux_acceptes;
        this.valide = valide;
        
        this.fumeur = fumeur;
         this.id_client = id_client;
    }
     

    
    public Logement(String file_name, String titre, String description, String type_logement, String adresse, String ville, int n_chambres, int n_lits, int n_salles_de_bains, float prix_nuit, int animaux_acceptes, int valide, String updated_at, int fumeur) {
        this.file_name = file_name;
        this.titre = titre;
        this.description = description;
        this.type_logement = type_logement;
        this.adresse = adresse;
        this.ville = ville;
        this.n_chambres = n_chambres;
        this.n_lits = n_lits;
        this.n_salles_de_bains = n_salles_de_bains;
        this.prix_nuit = prix_nuit;
        this.animaux_acceptes = animaux_acceptes;
        this.valide = valide;
        this.updated_at = updated_at;
        this.fumeur = fumeur;
       
    }

    public Logement(int aInt, String string, String desc , int n_chambres ,int animaux) {
        this.id = aInt;
        this.titre = string ; 
        this.description= desc ;
        this.n_chambres= n_chambres;
        this.animaux_acceptes= animaux;
    }

    public Logement(String titre , String description, String type_logement, String adresse, String ville, String file_name, int n_chambres, int n_lits, int n_salles_de_bains, float prix_nuit, int animaux_acceptes, int valide, int fumeur) {
        
        this.titre = titre;
        this.description = description;
        this.type_logement = type_logement;
        this.adresse = adresse;
        this.ville = ville;
        this.file_name = file_name;
        this.n_chambres = n_chambres;
        this.n_lits = n_lits;
        this.n_salles_de_bains = n_salles_de_bains;
        this.prix_nuit = prix_nuit;
        this.animaux_acceptes = animaux_acceptes;
        this.valide = valide;
        this.fumeur = fumeur;
        
       
                    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType_logement() {
        return type_logement;
    }

    public void setType_logement(String type_logement) {
        this.type_logement = type_logement;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getN_chambres() {
        return n_chambres;
    }

    public void setN_chambres(int n_chambres) {
        this.n_chambres = n_chambres;
    }

    public int getN_lits() {
        return n_lits;
    }

    public void setN_lits(int n_lits) {
        this.n_lits = n_lits;
    }

    public int getN_salles_de_bains() {
        return n_salles_de_bains;
    }

    public void setN_salles_de_bains(int n_salles_de_bains) {
        this.n_salles_de_bains = n_salles_de_bains;
    }

    public float getPrix_nuit() {
        return prix_nuit;
    }

    public void setPrix_nuit(float prix_nuit) {
        this.prix_nuit = prix_nuit;
    }

    public int getAnimaux_acceptes() {
        return animaux_acceptes;
    }

    public void setAnimaux_acceptes(int animaux_acceptes) {
        this.animaux_acceptes = animaux_acceptes;
    }

    public int getValide() {
        return valide;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getFumeur() {
        return fumeur;
    }

    public void setFumeur(int fumeur) {
        this.fumeur = fumeur;
    }
      public int getId_client() {
        return id_client;
    }

      public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Logement{" + "id=" + id + ", file_name=" + file_name + ", titre=" + titre + ", description=" + description + ", type_logement=" + type_logement + ", adresse=" + adresse + ", ville=" + ville + ", n_chambres=" + n_chambres + ", n_lits=" + n_lits + ", n_salles_de_bains=" + n_salles_de_bains + ", prix_nuit=" + prix_nuit + ", animaux_acceptes=" + animaux_acceptes + ", valide=" + valide + ", updated_at=" + updated_at + ", fumeur=" + fumeur + '}';
    }
    
    
}
