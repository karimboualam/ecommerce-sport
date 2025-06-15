// ✅ 11. Avis.java
package com.ecommerce.ajc.model;

import javax.persistence.*;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int note;
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Avis() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNote() { return note; }
    public void setNote(int note) { this.note = note; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
}