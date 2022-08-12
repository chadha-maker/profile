package com.example.profile.controller;

import com.example.profile.command.dto.RecruteurProfileCommandDto;
import com.example.profile.command.service.RecruteurProfileCommandServiceImpl;
import com.example.profile.domain.RecruteurProfileCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/profileRecruteur-command")
@CrossOrigin(origins = "*")
public class RecruteurProfileCommandController {


        @Autowired
        RecruteurProfileCommandServiceImpl profileCommandService;


        @PostMapping("/create")
        public ResponseEntity<?> createUser(@Valid @RequestBody RecruteurProfileCommandDto dto){

            if(this.profileCommandService.createProfile(dto)==1){
                return new ResponseEntity<>(HttpStatus.CREATED);
            };
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        @GetMapping("all")
        public List<RecruteurProfileCommand> getAll(){
            return this.profileCommandService.getAll();
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteProfile(@PathVariable long id){

            try{
                this.profileCommandService.deleteProfile(id);
                return new ResponseEntity<>(HttpStatus.CREATED);

            }catch (Exception e) {
                return  new ResponseEntity<>(HttpStatus.CONFLICT);

            }


        }


        @PutMapping("/update")
        public ResponseEntity<?> update(@RequestBody RecruteurProfileCommandDto dto){

            if(this.profileCommandService.updateProfile(dto)==1){
                return new ResponseEntity<>(HttpStatus.CREATED);
            };
            return  new ResponseEntity<>(HttpStatus.CONFLICT);

        }
    }


