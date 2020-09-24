package com.project.in.teams.Controllers;

import com.project.in.teams.Entity.Manager;
import com.project.in.teams.exception.*;
import com.project.in.teams.Entity.Team;
import com.project.in.teams.Entity.Users;
import com.project.in.teams.Repository.ManagerRepository;
import com.project.in.teams.Repository.TeamRepository;
import com.project.in.teams.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UsersRepository usersRepository;

    //Get all managers of all team
    @GetMapping("/")
    public List<Manager> getManager(){
        return managerRepository.findAll();
    }

    //get by team
    @GetMapping("/team")
    public Manager getManagerByTeam(@RequestParam(name = "team-name") String name){
       Team team= teamRepository.findFirstByName(name);
       if(team == null){
           throw new UnprocessableEntity("No Such team");
       }
       Manager manager = managerRepository.findFirstByTeamName(name);
       if(manager == null){
           throw new UnprocessableEntity("No Manager Assigned");
       }
       return manager;
    }

    //get by user
    @GetMapping("/users")
    public Manager getManagerByUser(@RequestParam(name = "user-email") String email){
        Users users = usersRepository.findFirstByEmail(email);
        if(users == null){
            throw new UnprocessableEntity("No Such User");
        }
        Manager manager = managerRepository.findFirstByUsersEmail(email);
        if(manager == null){
            throw new UnprocessableEntity("Not a manager");
        }
        return manager;
    }

    //assign team manager
    @PostMapping("/setManager/{name}")
    public Manager setManager(@PathVariable(name = "name") String name,
                              @RequestParam(name = "email") String email){
        Users users = usersRepository.findFirstByEmail(email);
        if(users==null){
            throw new UnprocessableEntity("No Such user");
        }
        Team team = teamRepository.findFirstByName(name);
        if(team==null){
            throw new UnprocessableEntity("No Such Team");
        }
        if(!team.getUsersList().contains(users)){
            throw new UnprocessableEntity("First add user to the team");
        }
        Manager manager = new Manager();
        manager.setTeam(team);
        manager.setUsers(users);
        return managerRepository.save(manager);
    }
}
