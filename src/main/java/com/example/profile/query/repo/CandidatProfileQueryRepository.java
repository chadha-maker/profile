package com.example.profile.query.repo;

import com.example.profile.domain.CandidatProfileQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CandidatProfileQueryRepository extends MongoRepository<CandidatProfileQuery, String>
{
    @Query("{ 'id': ?0 }")
    CandidatProfileQuery findById(long id);


}

