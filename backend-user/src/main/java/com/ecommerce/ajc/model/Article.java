// ✅ 6. Article.java
package com.ecommerce.ajc.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reference;

    private String nom;
    private String marque;
    private String type;
    private String description;
    private String taille;
    private String couleur;
    private Double prix;
    private String categorie;
    private int stock;
    private String image;

    public Article() {}

    public int getReference() { return reference; }
    public void setReference(int reference) { this.reference = reference; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTaille() { return taille; }
    public void setTaille(String taille) { this.taille = taille; }
    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(reference, article.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}
