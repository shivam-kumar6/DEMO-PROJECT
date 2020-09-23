package com.project.in.teams.Controllers;

import com.project.in.teams.Repository.ServiceRepository;
import com.project.in.teams.Repository.TeamRepository;
import com.project.in.teams.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class Controller {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UsersRepository usersRepository;

//    @PostMapping(value = "team/")
//    public Team save(@RequestBody Team t){
//        return teamRepository.save(t);
//    }

//    @GetMapping("")
}
