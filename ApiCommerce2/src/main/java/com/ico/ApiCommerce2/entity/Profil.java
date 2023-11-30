package com.ico.ApiCommerce2.entity;

import com.ico.ApiCommerce2.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profil")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public  class Profil implements UserDetails {
    @Id
    @Column(name = "email", updatable = false, nullable = false)
    private String email;
    @Column(name = "password", updatable = false, nullable = false)
    private String password;
    @Column(name = "nom", updatable = false, nullable = true)
    private String nom;
    @Column(name = "prenom", updatable = false, nullable = true)
    private String prenom;
    @Column(name = "adresse", updatable = true, nullable = true)
    private String adresse;
    @Column(name = "telephone", updatable = false, nullable = true)
    private String telephone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profil_roles", joinColumns = @JoinColumn(name = "profil_email"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles ;

    private String token;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
            // Assurez-vous que chaque rôle est préfixé par "ROLE_" comme l'exige Spring Security
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password ;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
