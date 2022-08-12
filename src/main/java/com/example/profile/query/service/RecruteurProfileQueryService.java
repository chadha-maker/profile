package com.example.profile.query.service;

import com.example.profile.domain.RecruteurProfileQuery;

import java.util.List;


public interface RecruteurProfileQueryService {
        RecruteurProfileQuery findById(long Id);

        List<RecruteurProfileQuery> getProfiles();
        List<RecruteurProfileQuery> findProfileByOwner(String username);
    }


