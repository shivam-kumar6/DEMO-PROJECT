package com.project.in.teams.Controllers;

import com.project.in.teams.exception.*;
import com.project.in.teams.Entity.Services;
import com.project.in.teams.Entity.Team;
import com.project.in.teams.Entity.Users;
import com.project.in.teams.Repository.ManagerRepository;
import com.project.in.teams.Repository.ServiceRepository;
import com.project.in.teams.Repository.TeamRepository;
import com.project.in.teams.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ManagerRepository managerRepository;

    //saving a team
    @PostMapping("/")
    public Team saveTeam(@RequestBody Team team){
        //validate
        try{
            return teamRepository.save(team);
        }
        catch (Exception exception){
            throw new UnprocessableEntity(exception.getMessage());
        }
    }

    //Get team by name
    @GetMapping("/{name}")
    public Team getTeamByName(@PathVariable(name="name") String name){
        Team team = teamRepository.findFirstByName(name);
        if(team==null){
            throw new UnprocessableEntity("No Such team");
        }
        return team;
    }

    //get all teams
    @GetMapping("/")
    public List<Team> getAll(){
        return teamRepository.findAll();
    }

    //Update team info
    @PostMapping("/{name}")
    public Team updateTeam(@PathVariable(name = "name") String name,
                            @RequestParam(name = "description", defaultValue = "null") String description,
                            @RequestParam(name = "password", defaultValue = "null") String password){
        Team team = teamRepository.findFirstByName(name);
        if(team ==  null){
            throw new UnprocessableEntity("No Such team");
        }
        if(!description.equals("null")) {
            team.setDescription(description);
        }
        if(!password.equals("null")){
            team.setPassword(password);
        }
        try{
            return teamRepository.save(team);
        }
        catch (Exception exception){
            throw new UnprocessableEntity(exception.getMessage());
        }
    }

    // Assign user to team
    @PostMapping(value = "/{name}/addUser")
    public Team addUser(@PathVariable(name = "name") String name,@RequestBody Users users){
        Team team = teamRepository.findFirstByName(name);
        if(team ==  null){
            throw new UnprocessableEntity("No Such team");
        }
        List<Users> list = team.getUsersList();
        list.add(users);
        team.setUsersList(list);
        try{
            return teamRepository.save(team);
        }
        catch (Exception exception){
            throw new UnprocessableEntity(exception.getMessage());
        }
    }

    //add service to team
    @PostMapping(value = "/{name}/addservice")
    public Team addService(@PathVariable(name = "name") String name,@RequestBody Services services){
        Team team = teamRepository.findFirstByName(name);
        if(team ==  null){
            throw new UnprocessableEntity("No Such team");
        }
        List<Services> list = team.getServicesList();
        list.add(services);
        team.setServicesList(list);

        try{
            return teamRepository.save(team);
        }
        catch (Exception exception){
            throw new UnprocessableEntity(exception.getMessage());
        }
    }

    //Get users of a team
    @GetMapping(value = "/{name}/users")
    public List<Users> getUsers(@PathVariable(name = "name") String name){
        Team team = teamRepository.findFirstByName(name);
        if(team ==  null){
            throw new UnprocessableEntity("No Such team");
        }
        return team.getUsersList();
    }

    //Get services of a team
    @GetMapping(value = "/{name}/services")
    public List<Services> getServices(@PathVariable(name = "name") String name){
        Team team = teamRepository.findFirstByName(name);
        if(team ==  null){
            throw new UnprocessableEntity("No Such team");
        }
        return team.getServicesList();
    }
//

//

//    @GetMapping("/id:{id}")
//    public Team get_team_by_id(@PathVariable(name="id") Long id){
//        Team team = teamRepository.getAllById(id);
//        return team;
//    }
//

//    public List<User> get_user_list_by_id(@PathVariable(name="id") Long id){
//        Team team= teamRepository.getAllById(id);
//        List<User> users =  team.get
//        return users;
//    }

//    @GetMapping("/serviceList/{name}")
//    public List<Services> get_service_list_by_user(@PathVariable(name="name") String name){
//        List<Services> services = teamRepository.findAllByName(name).getServicesList();
//        return services;
//    }
// select * form team where team.id=(select team.id form team, users where ut_fk==user.id)
}
