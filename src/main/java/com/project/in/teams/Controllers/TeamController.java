package com.project.in.teams.Controllers;

import com.project.in.teams.Entity.Services;
import com.project.in.teams.Entity.Team;
import com.project.in.teams.Entity.Users;
import com.project.in.teams.Repository.ServiceRepository;
import com.project.in.teams.Repository.TeamRepository;
import com.project.in.teams.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<Team> get_all(){
        return teamRepository.findAll();
    }

    @PostMapping("/")
    public Team save_team(@RequestBody Team t){
        return teamRepository.save(t);
    }

    @GetMapping("/{name}")
    public List<Team> get_team_by_name(@PathVariable(name="name") String name){
        List<Team> team = teamRepository.findAllByName(name);
        if(team==null){
            return null;
        }
        return team;
    }
    @GetMapping("/id:{id}")
    public Team get_team_by_id(@PathVariable(name="id") Long id){
        Team team = teamRepository.getAllById(id);
        return team;
    }


    public List<Users> get_user_list_by_id(@PathVariable(name="id") Long id){
        List<Users> users = teamRepository.getAllById(id).getUsersList();
        return users;
    }

//    @GetMapping("/serviceList/{name}")
//    public List<Services> get_service_list_by_user(@PathVariable(name="name") String name){
//        List<Services> services = teamRepository.findAllByName(name).getServicesList();
//        return services;
//    }
// select * form team where team.id=(select team.id form team, users where ut_fk==user.id)
}
