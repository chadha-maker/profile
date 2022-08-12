package com.example.profile.query.repo;

import com.example.profile.domain.RecruteurProfileQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RecruteurProfileQueryRepository extends MongoRepository<RecruteurProfileQuery, String>
{
    @Query("{ 'id': ?0 }")
    RecruteurProfileQuery findById(long id);




}

