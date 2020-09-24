package com.project.in.teams.Controllers;
import com.project.in.teams.exception.*;
import com.project.in.teams.Entity.Services;
import com.project.in.teams.Entity.Users;
import com.project.in.teams.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/")
    public List<Users> get_user(){
        return usersRepository.findAll();
    }

    @GetMapping("/email/{email}")
    public Users find_by_email(@PathVariable(name = "email") String email){
        Users users = usersRepository.findByEmail(email);
        if(users == null ){
            throw new UnprocessableEntity("No such user");
        }
        return users;
    }

    @GetMapping("/{Id}")
    public Users find_by_id(@PathVariable(name = "Id") Long Id){
        Users user= usersRepository.getById(Id);
        if(user == null ){
            throw new UnprocessableEntity("No such user");
        }
        return user;
    }


    @PostMapping("/")
    public Users add_user(@RequestBody Users u){
        //validate and throw exception
        return usersRepository.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete_user(@PathVariable(name="id") Long id){
        Users user=this.usersRepository.getById(id);
        if(user == null ){
            throw new UnprocessableEntity("No such user");
        }
        this.usersRepository.delete(user);
    }

    @PutMapping("/{id}")
    public Users update_user(@PathVariable(name="id") Long id,@RequestBody Users u){
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
        usersRepository.save(user);
        return user;
    }


    @GetMapping("/get_team_id/{id}")
    public Long get_team_id(@PathVariable(name="id") Long id){
         return usersRepository.getUt_fkById(id);
    }

}
