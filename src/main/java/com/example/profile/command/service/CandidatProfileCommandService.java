package com.example.profile.command.service;

import com.example.profile.command.dto.CandidatProfileCommandDto;
import com.example.profile.command.dto.RecruteurProfileCommandDto;
import com.example.profile.domain.CandidatProfileCommand;
import com.example.profile.domain.RecruteurProfileCommand;
import com.example.profile.domain.SkillsCandidat;

import java.util.List;
import java.util.Set;

public interface CandidatProfileCommandService {
    int createProfileCandidat(CandidatProfileCommandDto projectCommandDto, List<SkillsCandidat> skills);
    int updateProfileCandidat(CandidatProfileCommandDto projectCommandDto);
    void deleteProfileCandidat(Long id );
    List<CandidatProfileCommand> getAll();
}

