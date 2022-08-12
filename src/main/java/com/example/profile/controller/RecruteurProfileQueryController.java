package com.example.profile.controller;

import com.example.profile.domain.RecruteurProfileQuery;
import com.example.profile.query.service.RecruteurProfileQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/profileRecruteur-query")
@CrossOrigin("*")
public class RecruteurProfileQueryController {
    @Autowired
    private RecruteurProfileQueryService recruteurProfileQueryService;
    @Autowired
    KafkaTemplate<Long,String> kafkaTemplate;



    @GetMapping("/all")
    public List<RecruteurProfileQuery> getProfiles(){

        return this.recruteurProfileQueryService.getProfiles();
    }

    @GetMapping("/project-owner/{username}")
    public List<RecruteurProfileQuery> getProfilesByOwner(@PathVariable("username") String username)
    {

        return  this.recruteurProfileQueryService.findProfileByOwner(username);
    }


    @GetMapping("/projectbyid/{id}")
    public RecruteurProfileQuery getProfileById(@PathVariable long id){
        return this.recruteurProfileQueryService.findById(id);
    }

}

