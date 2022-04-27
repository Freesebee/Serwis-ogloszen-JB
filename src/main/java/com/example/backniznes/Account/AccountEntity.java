package com.example.backniznes.Account;

import com.example.backniznes.AdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "account", schema = "public", catalog = "d1j7icf55e42mi")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = true)
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
    @OneToMany(mappedBy = "accountByIdAccount")
    private Collection<AdEntity> adsById;

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
}
