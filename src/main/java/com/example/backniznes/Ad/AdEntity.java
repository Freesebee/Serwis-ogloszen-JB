package com.example.backniznes.Ad;

import com.example.backniznes.Account.AccountEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ad")
public class AdEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = true)
    private int id;
    @Basic
    @Column(name = "title", nullable = true, length = -1)
    private String title;
    @Basic
    @Column(name = "content", nullable = true, length = -1)
    private String content;
    @Basic
    @Column(name = "city", nullable = true, length = -1)
    private String city;
    @Basic
    @Column(name = "street", nullable = true, length = -1)
    private String street;
    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id", nullable = true)
    private AccountEntity accountByIdAccount;

    public AdEntity() {
    }
}
