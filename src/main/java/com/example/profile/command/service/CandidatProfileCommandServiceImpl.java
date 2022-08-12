package com.example.profile.command.service;

import com.example.profile.command.dto.CandidatProfileCommandDto;
import com.example.profile.command.repo.CandidatProfileCommandRepository;
import com.example.profile.domain.CandidatProfileCommand;
import com.example.profile.domain.SkillsCandidat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;



@Service
public class CandidatProfileCommandServiceImpl implements CandidatProfileCommandService {


    @Autowired
    CandidatProfileCommandRepository candidatProfileCommandRepository;

    @Autowired
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @Override
    public int createProfileCandidat(CandidatProfileCommandDto candidatProfileCommandDto, List<SkillsCandidat> skills) {

        CandidatProfileCommand candidatProfileCommand = new CandidatProfileCommand();
        candidatProfileCommand.setCreationDate(new Date(System.currentTimeMillis()));
        candidatProfileCommand.setName(candidatProfileCommandDto.getName());
        candidatProfileCommand.setLastModifiedDate(new Date(System.currentTimeMillis()));
        candidatProfileCommand.setDescription(candidatProfileCommandDto.getDescription());
        candidatProfileCommand.setSkills(skills);
        System.out.println(candidatProfileCommand);

        this.candidatProfileCommandRepository.save(candidatProfileCommand);
        System.out.println("id:");


        this.raiseEventToQueryProfileCandidat(candidatProfileCommand,"profileCandidat-event-create");


        return 1;


    }

    @Override
    public int updateProfileCandidat(CandidatProfileCommandDto candidatProfileCommandDto) {
        this.candidatProfileCommandRepository.findById(candidatProfileCommandDto.getId()).ifPresent(candidatProfileCommand -> {
            candidatProfileCommand.setLastModifiedDate(new Date(System.currentTimeMillis()));
            candidatProfileCommand.setName(candidatProfileCommandDto.getName());
            candidatProfileCommand.setDescription((candidatProfileCommandDto.getDescription()));
            this.raiseEventToQueryProfileCandidat(candidatProfileCommand,"profileCandidat-event-update");

        });
        return 1;



    }

    @Override
    public void deleteProfileCandidat(Long id) {
        Optional<CandidatProfileCommand> profileCommand = this.candidatProfileCommandRepository.findById(id);
        if(!profileCommand.isEmpty()){
            this.candidatProfileCommandRepository.deleteById(id);
            profileCommand.get().setId(id);
            this.raiseEventToQueryProfileCandidat(profileCommand.get(),"profileCandidat-event-delete");
        }else {
           CandidatProfileCommand candidatProfileCommand1 = new CandidatProfileCommand();
            candidatProfileCommand1.setId(id);
            this.raiseEventToQueryProfileCandidat(candidatProfileCommand1,"profileCandidat-event-delete");


        }

    }

    @Override
    public List<CandidatProfileCommand> getAll() {
        return this.candidatProfileCommandRepository.findAll();
    }

    private void raiseEventToQueryProfileCandidat(CandidatProfileCommand dto, String topic){
        try{
            String value = OBJECT_MAPPER.writeValueAsString(dto);
            this.kafkaTemplate.send(topic,value);
            System.out.println("sended");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

