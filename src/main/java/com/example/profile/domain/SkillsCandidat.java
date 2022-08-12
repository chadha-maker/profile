package com.example.profile.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class SkillsCandidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skill ;
    @ManyToMany
    private List<CandidatProfileCommand> profiles;

}
