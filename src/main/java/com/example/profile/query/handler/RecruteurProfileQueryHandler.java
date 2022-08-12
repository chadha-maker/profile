package com.example.profile.query.handler;

import com.example.profile.query.dto.CandidatProfileQueryDto;
import com.example.profile.query.dto.RecruteurProfileQueryDto;

public interface RecruteurProfileQueryHandler
{
    public  void createProfileRecruteur(RecruteurProfileQueryDto recruteurProfileQueryDto);
    public void updateProfileRecruteur(RecruteurProfileQueryDto recruteurProfileQueryDto);
    public  void deleteProfileRecruteur(RecruteurProfileQueryDto recruteurProfileQueryDto);

}

