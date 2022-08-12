package com.example.profile.query.service;

import com.example.profile.domain.CandidatProfileQuery;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CandidatProfileQueryServiceImpl implements CandidatProfileQueryService{
    @Override
    public CandidatProfileQuery findById(long Id) {
        return null;
    }

    @Override
    public List<CandidatProfileQuery> getProfiles() {
        return null;
    }

    @Override
    public List<CandidatProfileQuery> findProfileByOwner(String username) {
        return null;
    }
}
