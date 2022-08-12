package com.example.profile.query.handler;

import com.example.profile.domain.CandidatProfileQuery;
import com.example.profile.domain.RecruteurProfileQuery;
import com.example.profile.query.dto.CandidatProfileQueryDto;
import com.example.profile.query.dto.RecruteurProfileQueryDto;
import com.example.profile.query.repo.CandidatProfileQueryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProfileCandidatEventHandlerImpl implements CandidatProfileQueryHandler
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private CandidatProfileQueryRepository profilecandidatQueryRepository;

    @Override
    public void createProfilecandidat(CandidatProfileQueryDto candidatProfileQueryDto) {
        System.out.println("Project created in query with pivileges");

        CandidatProfileQuery candidatprofileQuery = new CandidatProfileQuery();
        candidatprofileQuery.setId(candidatProfileQueryDto.getId());
        candidatprofileQuery.setCreationDate(candidatProfileQueryDto.getCreationDate());
        candidatprofileQuery.setName(candidatProfileQueryDto.getName());
        candidatprofileQuery.setDescription(candidatProfileQueryDto.getDescription());
        candidatprofileQuery.setLastModifiedDate(candidatProfileQueryDto.getLastModifiedDate());
        System.out.println(candidatprofileQuery);

        this.profilecandidatQueryRepository.save(candidatprofileQuery);


    }

    @Override
    public void updateProfileCandidat(CandidatProfileQueryDto candidatProfileQueryDto) {
        CandidatProfileQuery candidatProfileQuery= this.profilecandidatQueryRepository.findById(candidatProfileQueryDto.getId());
        candidatProfileQuery.setName(candidatProfileQueryDto.getName());
        candidatProfileQuery.setDescription(candidatProfileQueryDto.getDescription());
        candidatProfileQuery.setLastModifiedDate(candidatProfileQueryDto.getLastModifiedDate());
        this.profilecandidatQueryRepository.save(candidatProfileQuery);

    }
    @Override
    public void deleteProfileCandidat(CandidatProfileQueryDto candidatProfileQueryDto) {


        CandidatProfileQuery candidatProfileQuery= this.profilecandidatQueryRepository.findById(candidatProfileQueryDto.getId());;
        System.out.println(candidatProfileQuery);

        this.profilecandidatQueryRepository.delete(candidatProfileQuery);
    }


    @KafkaListener(topics = "profileCandidat-event-create")
    public void consumeCreate(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
            CandidatProfileQueryDto candidatProfileQueryDto = OBJECT_MAPPER.readValue(userStr, CandidatProfileQueryDto.class);
            this.createProfilecandidat(candidatProfileQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "profileCandidat-event-update")
    public void consumeUpdate(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
            CandidatProfileQueryDto candidatProfileQueryDto = OBJECT_MAPPER.readValue(userStr, CandidatProfileQueryDto.class);
            this.updateProfileCandidat(candidatProfileQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "profileCandidat-event-delete")
    public void consumeDelete(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
            CandidatProfileQueryDto projectQueryDto = OBJECT_MAPPER.readValue(userStr, CandidatProfileQueryDto.class);
            this.deleteProfileCandidat(projectQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

