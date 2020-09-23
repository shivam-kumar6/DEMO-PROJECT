package com.project.in.teams.Controllers;

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

    @GetMapping("/{email}")
    public List<Users> find_by_email(@PathVariable(name = "email") String email){
        List<Users> users = usersRepository.findByEmail(email);
        return users;
    }

    @GetMapping("/Id:{Id}")
    public Optional<Users> find_by_id(@PathVariable(name = "Id") Long Id){
        Optional<Users> user= usersRepository.findById(Id);
        return user;
    }

}
