package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "paniers")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PanierItem> items = new ArrayList<>();

    private double total;

    private String codePromo;
    private double remise; // remise en euros

    public Panier() {}

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public List<PanierItem> getItems() { return items; }
    public void setItems(List<PanierItem> items) { this.items = items; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getCodePromo() { return codePromo; }
    public void setCodePromo(String codePromo) { this.codePromo = codePromo; }

    public double getRemise() { return remise; }
    public void setRemise(double remise) { this.remise = remise; }
}
