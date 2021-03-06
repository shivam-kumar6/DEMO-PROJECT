package com.project.in.teams.Controllers;
import com.project.in.teams.Entity.Team;
import com.project.in.teams.Repository.TeamRepository;
import com.project.in.teams.exception.*;
import com.project.in.teams.Entity.Services;
import com.project.in.teams.Entity.Users;
import com.project.in.teams.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/")
    public List<Users> getUser(){
        return usersRepository.findAll();
    }

    @GetMapping("/email/{email}")
    public Users findByEmail(@PathVariable(name = "email") String email){
        Users users = usersRepository.findByEmail(email);
        if(users == null ){
            throw new UnprocessableEntity("No such user");
        }
        return users;
    }

    @GetMapping("/{Id}")
    public Users findUserById(@PathVariable(name = "Id") Long Id){
        Users user= usersRepository.getById(Id);
        if(user == null ){
            throw new UnprocessableEntity("No such user");
        }
        return user;
    }


    @PostMapping("/")

    public Users add_user(@RequestBody Users u){
        //validate and throw exception
        try{
            return usersRepository.save(u);
        }
        catch (Exception exception){
            throw new UnprocessableEntity(exception.getMessage());
        }


    }

    @DeleteMapping("/{id}")
    public HashMap<String, Boolean> deleteUser(@PathVariable(name="id") Long id){
        Users user=this.usersRepository.getById(id);
        if(user == null ){
            throw new UnprocessableEntity("No such user");
        }
        HashMap<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        this.usersRepository.delete(user);
        return response;
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable(name="id") Long id,@RequestBody Users u){
        Users user = usersRepository.getById(id);
        if(user == null ){
            throw new UnprocessableEntity("No such user");
        }
        if(u.getFirst_name()!=null){
            user.setFirst_name(u.getFirst_name());
        }
        if(u.getLast_name()!=null){
            user.setLast_name(u.getLast_name());
        }
        if(u.getDesignation()!=null){
            user.setDesignation(u.getDesignation());
        }
        if(u.getEmail()!=null){
            user.setEmail(u.getEmail());
        }
        if(u.getPhone_no()!=null){
            user.setPhone_no(u.getPhone_no());
        }
        if(u.getGender()!=null){
            user.setGender(u.getGender());
        }
        try{
            usersRepository.save(user);
            return user;
        }
        catch (Exception exception){
            throw new UnprocessableEntity(exception.getMessage());
        }

    }


    @GetMapping("/get_team_id/{id}")
    public Long getTeamId(@PathVariable(name="id") Long id){
         return usersRepository.getUt_fkById(id);
    }


    @GetMapping("/get_team_name/{email}")
    public String get_team_email(@PathVariable(name="email") String email){
        Users users = usersRepository.findFirstByEmail(email);
        if(users == null){
            throw new UnprocessableEntity("No such user");
        }
        Long team_id = users.getUt_fk();
        Team team =  teamRepository.findFirstById(team_id);
        return team.getName();
    }

    @GetMapping("/advancedSearch/{keyword}")
    public List<Users> advancedSearch(@PathVariable(name = "keyword") String keyword){
        List<Users> result = usersRepository.advancedSearch(keyword);
        if(result.size()==0){
            throw new UnprocessableEntity("No matching user");
        }
        return result;
    }

}
