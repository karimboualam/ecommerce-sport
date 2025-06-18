package dto;

import java.util.Date;

public class CommandeDashboardDTO {
    private Long id;
    private String username;
    private Date date;
    private Double montant;

    public CommandeDashboardDTO(Long id, String username, Date date, Double montant) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.montant = montant;
    }

    // Getters uniquement (pas besoin de setters si lecture seule)
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }

    public Double getMontant() {
        return montant;
    }
}
