package com.example.profile.query.handler;

import com.example.profile.query.dto.CandidatProfileQueryDto;

public interface CandidatProfileQueryHandler
{
    public  void createProfilecandidat(CandidatProfileQueryDto candidatProfileQueryDto);
    public void updateProfileCandidat(CandidatProfileQueryDto candidatProfileQueryDto);
    public  void deleteProfileCandidat(CandidatProfileQueryDto candidatProfileQueryDto);

}
