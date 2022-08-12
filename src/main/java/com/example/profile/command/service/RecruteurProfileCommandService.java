package com.example.profile.command.service;

import com.example.profile.command.dto.RecruteurProfileCommandDto;
import com.example.profile.domain.RecruteurProfileCommand;

import java.util.List;

public interface RecruteurProfileCommandService {
    int createProfile(RecruteurProfileCommandDto projectCommandDto);
    int updateProfile(RecruteurProfileCommandDto projectCommandDto);
    void deleteProfile(Long id );
    List<RecruteurProfileCommand> getAll();
}

