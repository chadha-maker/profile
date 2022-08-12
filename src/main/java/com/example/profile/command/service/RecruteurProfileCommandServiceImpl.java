package com.example.profile.command.service;

import com.example.profile.command.dto.RecruteurProfileCommandDto;
import com.example.profile.command.repo.RecruteurProfileCommandRepository;
import com.example.profile.domain.RecruteurProfileCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;



@Service
@Component
public class RecruteurProfileCommandServiceImpl implements RecruteurProfileCommandService {

    @Value("http://23.99.67.62:3000")
    private  String url;

    @Autowired
    RecruteurProfileCommandRepository recruteurProfileCommandRepository;

    @Autowired
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @Override
    public int createProfile(RecruteurProfileCommandDto recruteurProfileCommandDto) {

        RecruteurProfileCommand recruteurProfileCommand = new RecruteurProfileCommand();
        recruteurProfileCommand.setCreationDate(new Date(System.currentTimeMillis()));
        recruteurProfileCommand.setName(recruteurProfileCommandDto.getName());
        recruteurProfileCommand.setExperience(recruteurProfileCommandDto.getExperience());
        recruteurProfileCommand.setLastModifiedDate(new Date(System.currentTimeMillis()));
        recruteurProfileCommand.setPoste(recruteurProfileCommandDto.getPoste());
        recruteurProfileCommand.setPrenom(recruteurProfileCommandDto.getPrenom());
        recruteurProfileCommand.setSpecialite(recruteurProfileCommandDto.getSpecialite());
        recruteurProfileCommand.setDescription(recruteurProfileCommandDto.getDescription());
        System.out.println(recruteurProfileCommand);

        this.recruteurProfileCommandRepository.save(recruteurProfileCommand);
        System.out.println("id:");


        this.raiseEventToQueryProfile(recruteurProfileCommand,"profileRecruteur-event-create");


            Map<String,String> body = new HashMap<>();
            body.put("appname", recruteurProfileCommandDto.getName());
            body.put("description", recruteurProfileCommandDto.getDescription());
            this.createNodeRedProfile(body);


        return 1;


    }

    @Override
    public int updateProfile(RecruteurProfileCommandDto recruteurProfileCommandDto) {
        this.recruteurProfileCommandRepository.findById(recruteurProfileCommandDto.getId()).ifPresent(recruteurProfileCommand -> {
            recruteurProfileCommand.setLastModifiedDate(new Date(System.currentTimeMillis()));
            recruteurProfileCommand.setName(recruteurProfileCommandDto.getName());
            recruteurProfileCommand.setDescription((recruteurProfileCommandDto.getDescription()));
            recruteurProfileCommand.setExperience(recruteurProfileCommandDto.getExperience());
            recruteurProfileCommand.setPoste(recruteurProfileCommandDto.getPoste());
            recruteurProfileCommand.setPrenom(recruteurProfileCommandDto.getPrenom());
            recruteurProfileCommand.setSpecialite(recruteurProfileCommandDto.getSpecialite());

            this.raiseEventToQueryProfile(recruteurProfileCommand,"profileRecruteur-event-update");

        });
        return 1;



    }

    @Override
    public void deleteProfile(Long id) {
        Optional<RecruteurProfileCommand> profileCommand = this.recruteurProfileCommandRepository.findById(id);
        if(!profileCommand.isEmpty()){
            this.recruteurProfileCommandRepository.deleteById(id);
            profileCommand.get().setId(id);
            this.raiseEventToQueryProfile(profileCommand.get(),"profileRecruteur-event-delete");
        }else {
            RecruteurProfileCommand recruteurProfileCommand1 = new RecruteurProfileCommand();
            recruteurProfileCommand1.setId(id);
            this.raiseEventToQueryProfile(recruteurProfileCommand1,"profileRecruteur-event-delete");


        }

    }

    @Override
   public List<RecruteurProfileCommand> getAll() {
       return this.recruteurProfileCommandRepository.findAll();
    }

    private void raiseEventToQueryProfile(RecruteurProfileCommand dto, String topic){
        try{
            String value = OBJECT_MAPPER.writeValueAsString(dto);
            this.kafkaTemplate.send(topic,value);
            System.out.println("sended");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void createNodeRedProfile(Map<String,String> body){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Map> entity = new HttpEntity<>(body,headers);
        Object res=  restTemplate.postForEntity( url+"/instance", entity , Map.class ).getBody();
    }

    void deleteNodeRedProfile(String username, String appname){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Map<String,Object> res=  restTemplate.getForEntity( url+"/myinstances/"+username, Map.class ).getBody();
        ObjectMapper oMapper = new ObjectMapper();
        List<Map<String,String>> instances = oMapper.convertValue(res.get("instances"), List.class);


        for(int i = 0; i<instances.size(); i++){
            System.out.println(instances.get(i).get("appname"));
            if(instances.get(i).get("appname").equals(appname)){
                Map<String,String> body = new HashMap<>();
                body.put("appname",appname);
                body.put("command","stop");
                HttpEntity<Map> entity = new HttpEntity<>(body,headers);
                Object res1Stop=  restTemplate.postForEntity( url+"/instance/"+instances.get(i).get("container_id"), entity , Map.class ).getBody();
                System.out.println(instances.get(i).get("container_id"));
                body.put("command","remove");
                Object res1Remove=  restTemplate.postForEntity( url+"/instance/"+instances.get(i).get("container_id"), entity , Map.class ).getBody();
            }
        }



    }

}

