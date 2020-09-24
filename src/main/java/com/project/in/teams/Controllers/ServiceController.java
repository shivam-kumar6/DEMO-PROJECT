package com.project.in.teams.Controllers;

import com.project.in.teams.exception.*;
import com.project.in.teams.Entity.Services;
import com.project.in.teams.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/")
    public List<Services> get_services(){
        return serviceRepository.findAll();
    }

    @PostMapping("/")
    public Services add_service(@RequestBody Services s){

        return serviceRepository.save(s);
    }

    @DeleteMapping("/{id}")
    public void delete_service(@PathVariable(name="id") Long id){

        Services service = serviceRepository.getById(id);
        if(service==null){
            throw new UnprocessableEntity("No Such service");
        }
        this.serviceRepository.delete(service);
    }

    @PutMapping("/{id}")
    public Services update_service(@PathVariable(name="id") Long id,@RequestBody Services s){
        Services service = serviceRepository.getById(id);
        if(service == null ){
            throw new UnprocessableEntity("No such service");
        }
        if(s.getName()!=null)
        service.setName(s.getName());
        if(s.getService_description()!=null)
        service.setService_description(s.getService_description());
        serviceRepository.save(service);
        return service;
    }

}
