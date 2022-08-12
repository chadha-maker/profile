package com.example.profile.query.handler;

import com.example.profile.domain.CandidatProfileQuery;
import com.example.profile.domain.RecruteurProfileQuery;
import com.example.profile.query.dto.CandidatProfileQueryDto;
import com.example.profile.query.dto.RecruteurProfileQueryDto;
import com.example.profile.query.repo.CandidatProfileQueryRepository;
import com.example.profile.query.repo.RecruteurProfileQueryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RecruteurProfileEventHandlerImpl implements RecruteurProfileQueryHandler
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private RecruteurProfileQueryRepository recruteurProfileQueryRepository;

    @Override
    public void createProfileRecruteur(RecruteurProfileQueryDto recruteurProfileQueryDto) {

        RecruteurProfileQuery recruteurProfileQuery = new RecruteurProfileQuery();
        recruteurProfileQuery.setId(recruteurProfileQueryDto.getId());
        recruteurProfileQuery.setCreationDate(recruteurProfileQueryDto.getCreationDate());
        recruteurProfileQuery.setName(recruteurProfileQueryDto.getName());
        recruteurProfileQuery.setDescription(recruteurProfileQueryDto.getDescription());
        recruteurProfileQuery.setLastModifiedDate(recruteurProfileQueryDto.getLastModifiedDate());
        System.out.println(recruteurProfileQuery);

        this.recruteurProfileQueryRepository.save(recruteurProfileQuery);


    }

    @Override
    public void updateProfileRecruteur(RecruteurProfileQueryDto recruteurProfileQueryDto) {
        RecruteurProfileQuery recruteurProfileQuery= this.recruteurProfileQueryRepository.findById(recruteurProfileQueryDto.getId());
        recruteurProfileQuery.setName(recruteurProfileQueryDto.getName());
        recruteurProfileQuery.setDescription(recruteurProfileQueryDto.getDescription());
        recruteurProfileQuery.setLastModifiedDate(recruteurProfileQueryDto.getLastModifiedDate());
        this.recruteurProfileQueryRepository.save(recruteurProfileQuery);

    }
    @Override
    public void deleteProfileRecruteur(RecruteurProfileQueryDto recruteurProfileQueryDto) {


        RecruteurProfileQuery recruteurProfileQuery= this.recruteurProfileQueryRepository.findById(recruteurProfileQueryDto.getId());;
        System.out.println(recruteurProfileQuery);

        this.recruteurProfileQueryRepository.delete(recruteurProfileQuery);
    }


    @KafkaListener(topics = "profileRecruteur-event-create")
    public void consumeCreate(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
            RecruteurProfileQueryDto recruteurProfileQueryDto = OBJECT_MAPPER.readValue(userStr, RecruteurProfileQueryDto.class);
            this.createProfileRecruteur(recruteurProfileQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "profileRecruteur-event-update")
    public void consumeUpdate(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
            RecruteurProfileQueryDto recruteurProfileQueryDto = OBJECT_MAPPER.readValue(userStr, RecruteurProfileQueryDto.class);
            this.updateProfileRecruteur(recruteurProfileQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "profileRecruteur-event-delete")
    public void consumeDelete(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
            RecruteurProfileQueryDto recruteurProfileQueryDto = OBJECT_MAPPER.readValue(userStr, RecruteurProfileQueryDto.class);
            this.deleteProfileRecruteur(recruteurProfileQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

