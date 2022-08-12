package com.example.profile.command.repo;

import com.example.profile.domain.SkillsCandidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface skillsprofileRepository extends JpaRepository<SkillsCandidat, String> {
    }

