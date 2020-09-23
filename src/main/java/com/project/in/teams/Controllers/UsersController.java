package com.project.in.teams.Controllers;

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
    public List<Users> find_by_email(@PathVariable(name = "email") String email){
        List<Users> users = usersRepository.findByEmail(email);
        return users;
    }

    @GetMapping("/{Id}")
    public Optional<Users> find_by_id(@PathVariable(name = "Id") Long Id){
        Optional<Users> user= usersRepository.findById(Id);
        return user;
    }


    @PostMapping("/")
    public Users add_user(@RequestBody Users u){
        return usersRepository.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete_user(@PathVariable(name="id") Long id){
        this.usersRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Users update_user(@PathVariable(name="id") Long id,@RequestBody Users u){
        Users user = usersRepository.getById(id);
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

}
