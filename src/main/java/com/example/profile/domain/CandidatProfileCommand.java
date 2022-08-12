package com.example.profile.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "profilesCandidat")
@Getter
@Setter
public class CandidatProfileCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;
    private String prenom;
    private Date date_naissance;
    private String specialite;
    private String poste ;
    private int experience ;
    private String description;
    @ManyToMany
    private List<SkillsCandidat> skills;








    @Temporal(TemporalType.DATE)
    private Date creationDate;






    public CandidatProfileCommand() {
    }

    @Override
    public String toString() {
        return "ProjectCommand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }




    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;


}