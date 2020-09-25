package com.project.in.teams.Controllers;


import com.project.in.teams.Entity.Users;
import com.project.in.teams.Repository.UsersRepository;
import com.project.in.teams.exception.UnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private UsersRepository usersRepository;


    @GetMapping("/{keyword}")
    public List<Users> advancedSearch(@RequestParam(name = "first_name", defaultValue = "0") boolean first_name,
                                      @RequestParam(name = "last_name",defaultValue = "0") boolean last_name,
                                      @RequestParam(name = "gender",defaultValue = "both") String gender,
                                      @RequestParam(name = "designation",defaultValue = "0") boolean designation,
                                      @RequestParam(name = "email",defaultValue = "0") boolean email,
                                      @RequestParam(name = "phone_no",defaultValue = "0") boolean phone_no,
                                      @PathVariable(name = "keyword") String keyword){
        List<Users> result = new ArrayList<>();
        if(keyword.equals("all")){
            //find all users
            result = usersRepository.findAll();
            if(result.size()==0){
                throw new UnprocessableEntity("No User in the  Database");
            }
            if(gender.equals("both")){
                return result;
            }
            else if(gender.equals("Male") || gender.equals("male")){
                result = result.stream().filter((users) -> users.getGender().equals("Male")).collect(Collectors.toList());
            }
            else if(gender.equals("Female") || gender.equals("female")){
                result = result.stream().filter((users) -> users.getGender().equals("Female")).collect(Collectors.toList());
            }
            else{
                throw new UnprocessableEntity("Choose either Male or Female as gender");
            }
            if(result.size()==0){
                throw new UnprocessableEntity("No Matching User");
            }
            return result;
        }
        if(!(first_name || last_name || designation  || email || phone_no ) ){
            result = usersRepository.advancedSearch(keyword);
            if(!gender.equals("both")){
                if(gender.equals("Male") || gender.equals("male")){

                    result = result.stream().filter((users) -> users.getGender().equals("Male")).collect(Collectors.toList());
                }
                else if(gender.equals("Female") || gender.equals("female")){
                    result = result.stream().filter((users) -> users.getGender().equals("Female")).collect(Collectors.toList());
                }
                else{
                    throw new UnprocessableEntity("Choose either Male or Female as gender");
                }
            }
            if(result.size()==0){
                throw new UnprocessableEntity("No matching user");
            }
            return result;
        }
        if(first_name){
            result  = usersRepository.searchFirstName(keyword);
        }
        if(last_name) {
            result.addAll(usersRepository.searchLastName(keyword));
        }
        if(designation){
            result.addAll(usersRepository.searchDesignation(keyword));
        }
        if(email){
            result.addAll(usersRepository.searchEmail(keyword));
        }
        if(phone_no){
            result.addAll(usersRepository.searchPhone(keyword));
        }
        if(!gender.equals("both")){
            if(gender.equals("Male") || gender.equals("male")){

                result = result.stream().filter((users) -> users.getGender().equals("Male")).collect(Collectors.toList());
            }
            else if(gender.equals("Female") || gender.equals("female")){
                result = result.stream().filter((users) -> users.getGender().equals("Female")).collect(Collectors.toList());
            }
            else{
                throw new UnprocessableEntity("Choose either Male or Female as gender");
            }
        }
        if(result.size()==0){
            throw new UnprocessableEntity("No matching user");
        }
        return result;
    }
}
