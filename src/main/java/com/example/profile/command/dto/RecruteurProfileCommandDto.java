package com.example.profile.command.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
public class RecruteurProfileCommandDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String prenom;
    private Date date_naissance;
    private String specialite;
    private String nom_entreprise;
    private String poste ;
    private int experience ;
    private String description;





}
