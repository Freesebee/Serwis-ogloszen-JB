package com.example.backniznes.PersonalData;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "personal_data")
public class PersonalDataEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "surename", nullable = true, length = -1)
    private String surename;
    @Basic
    @Column(name = "city", nullable = true, length = -1)
    private String city;
    @Basic
    @Column(name = "street", nullable = true, length = -1)
    private String street;

    public PersonalDataEntity() {
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", surename='" + surename + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + " ";
    }
}
