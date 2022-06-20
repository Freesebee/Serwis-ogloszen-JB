package com.example.backniznes.Domain.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "category", schema = "public", catalog = "dalh4kujhg8l3l")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "categoryByIdCategory")
    private Collection<AdEntity> adsById;

    @Basic
    @Column(name = "is_active")
    private boolean isActive;

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adsById=" + adsById;
    }
}
