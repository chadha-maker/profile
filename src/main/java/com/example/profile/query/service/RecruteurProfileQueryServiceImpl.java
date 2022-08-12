package com.example.profile.query.service;

import com.example.profile.domain.RecruteurProfileQuery;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecruteurProfileQueryServiceImpl implements RecruteurProfileQueryService {
    @Override
    public RecruteurProfileQuery findById(long Id) {
        return null;
    }

    @Override
    public List<RecruteurProfileQuery> getProfiles() {
        return null;
    }

    @Override
    public List<RecruteurProfileQuery> findProfileByOwner(String username) {
        return null;
    }
}
