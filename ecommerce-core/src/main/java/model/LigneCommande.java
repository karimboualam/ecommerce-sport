package model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "lignes_commande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantite;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    @JsonBackReference
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public LigneCommande() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
}