package com.example.profile.controller;
import com.example.profile.command.dto.CandidatProfileCommandDto;
import com.example.profile.command.service.CandidatProfileCommandServiceImpl;
import com.example.profile.domain.CandidatProfileCommand;
import com.example.profile.domain.SkillsCandidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/profileCandidat-command")
@CrossOrigin(origins = "*")
public class CandidatProfileCommandController {


    @Autowired
    CandidatProfileCommandServiceImpl candidatprofileCommandService;


    @PostMapping("/createCandidat")
    public ResponseEntity<?> createUserCandidat(@Valid @RequestBody CandidatProfileCommandDto dto, @Valid @RequestBody List<SkillsCandidat> skills){

        if(this.candidatprofileCommandService.createProfileCandidat(dto,skills)==1){
            return new ResponseEntity<>(HttpStatus.CREATED);
        };
        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("allCandidats")
    public List<CandidatProfileCommand> getAll(){
        return this.candidatprofileCommandService.getAll();
    }

    @DeleteMapping("/deleteCandidat/{id}")
    public ResponseEntity<?> deleteProfileCandidat(@PathVariable long id){

        try{
            this.candidatprofileCommandService.deleteProfileCandidat(id);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.CONFLICT);

        }


    }


    @PutMapping("/updateCandidat")
    public ResponseEntity<?> updateCandidat(@RequestBody CandidatProfileCommandDto dto){

        if(this.candidatprofileCommandService.updateProfileCandidat(dto)==1){
            return new ResponseEntity<>(HttpStatus.CREATED);
        };
        return  new ResponseEntity<>(HttpStatus.CONFLICT);

    }
}


