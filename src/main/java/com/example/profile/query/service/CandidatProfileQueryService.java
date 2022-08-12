package com.example.profile.query.service;

import com.example.profile.domain.CandidatProfileQuery;

import java.util.List;


public interface CandidatProfileQueryService {
    CandidatProfileQuery findById(long Id);

    List<CandidatProfileQuery> getProfiles();
    List<CandidatProfileQuery> findProfileByOwner(String username);
}


