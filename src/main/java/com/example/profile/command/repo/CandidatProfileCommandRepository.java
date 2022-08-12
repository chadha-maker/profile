package com.example.profile.command.repo;

import com.example.profile.domain.CandidatProfileCommand;
import com.example.profile.domain.RecruteurProfileCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatProfileCommandRepository extends JpaRepository<CandidatProfileCommand, Long> {

}

