package com.example.backniznes;

import com.example.backniznes.Account.AccountEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ad", schema = "public", catalog = "d1j7icf55e42mi")
public class AdEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = true, length = -1)
    private String title;
    @Basic
    @Column(name = "content", nullable = true, length = -1)
    private String content;
    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id", nullable = false)
    private AccountEntity accountByIdAccount;

}
