package com.example.profile.controller;

import com.example.profile.domain.CandidatProfileQuery;
import com.example.profile.domain.RecruteurProfileQuery;
import com.example.profile.query.service.CandidatProfileQueryService;
import com.example.profile.query.service.RecruteurProfileQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/profileCandidat-query")
@CrossOrigin("*")
public class CandidatProfileQueryController {
    @Autowired
    private CandidatProfileQueryService candidatProfileQueryService;
    @Autowired
    KafkaTemplate<Long,String> kafkaTemplate;



    @GetMapping("/allCandidats")
    public List<CandidatProfileQuery> getProfiles(){

        return this.candidatProfileQueryService.getProfiles();
    }

    @GetMapping("/profileCandidat-owner/{username}")
    public List<CandidatProfileQuery> getProfilesByOwner(@PathVariable("username") String username)
    {

        return  this.candidatProfileQueryService.findProfileByOwner(username);
    }


    @GetMapping("/profileCandidatbyid/{id}")
    public CandidatProfileQuery getProfileById(@PathVariable long id){
        return this.candidatProfileQueryService.findById(id);
    }

}

