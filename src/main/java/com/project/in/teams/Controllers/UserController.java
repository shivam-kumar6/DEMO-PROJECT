package com.project.in.teams.Controllers;

import com.project.in.teams.Entity.Users;
import com.project.in.teams.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<Users> get_user(){
        return userRepository.findAll();
    }

    @GetMapping("/{email}")
    public List<Users> find_by_email(@PathVariable(name = "email") String email){
        List<Users> user=userRepository.findByEmail(email);
        return user;
    }

    @GetMapping("/Id:{Id}")
    public Optional<Users> find_by_id(@PathVariable(name = "Id") Long Id){
        Optional<Users> user=userRepository.findById(Id);
        return user;
    }

}
