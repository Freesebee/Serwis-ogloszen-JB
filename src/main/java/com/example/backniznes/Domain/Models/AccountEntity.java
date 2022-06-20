package com.example.backniznes.Domain.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "login", nullable = true, length = -1)
    private String login;
    @Basic
    @Column(name = "password", nullable = true, length = -1)
    private String password;
    @Basic
    @Column(name = "email", nullable = true, length = -1)
    private String email;
    @Basic
    @Column(name = "role", nullable = true, length = -1)
    private String role;
    @Basic
    @Column(name = "nickname", nullable = true, length = -1)
    private String nickname;
    @JsonIgnore
    @OneToMany(mappedBy = "accountByIdAccount")
    private Collection<AdEntity> adsById;
    @Basic
    @Column(name = "id_personal_data_fk", nullable = true)
    private Integer idPersonalDataFk;

    public AccountEntity(String login, String password, String email, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public AccountEntity(String login, String password, String email, String role, Collection<AdEntity> adsById) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.adsById = adsById;
    }

    public AccountEntity() {

    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", nickname='" + nickname + '\'' +
                ", adsById=" + adsById +
                ", idPersonalDataFk=" + idPersonalDataFk + " ";
    }
}
