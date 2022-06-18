package com.example.backniznes.Ad;

import com.example.backniznes.Account.AccountEntity;
import com.example.backniznes.Category.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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
    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = true)
    private CategoryEntity categoryByIdCategory;

    public AdEntity() {
    }

    public AdEntity(int id, String title, String content, String city, String street, AccountEntity accountByIdAccount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.city = city;
        this.street = street;
        this.accountByIdAccount = accountByIdAccount;
    }

    public AdEntity(AdEntity ad) {
        this.id = ad.getId();
        this.title = ad.getTitle();
        this.content = ad.getContent();
        this.city = ad.getCity();
        this.street = ad.getStreet();
        this.accountByIdAccount = ad.getAccountByIdAccount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return id == adEntity.id && Objects.equals(title, adEntity.title) && Objects.equals(content, adEntity.content) && Objects.equals(city, adEntity.city) && Objects.equals(street, adEntity.street) && Objects.equals(accountByIdAccount, adEntity.accountByIdAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, city, street, accountByIdAccount);
    }
}
