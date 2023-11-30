package com.ico.ApiCommerce2.entity;

import com.ico.ApiCommerce2.enumeration.StatusCommande;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@Table(name="commande")
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "date", updatable = false, nullable = false)
    private Date date;

    @ManyToOne()
    @JoinColumn( name="client_id" )
    private Client client;

    private StatusCommande status = StatusCommande.EN_ATTENTE_DE_VALIDATION ;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitQuantite> produits = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ProduitQuantite> getListeProduit() {
        return produits;
    }

}
