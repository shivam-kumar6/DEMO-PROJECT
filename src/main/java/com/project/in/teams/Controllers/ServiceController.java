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
    public List<Services> getServices(){
        return serviceRepository.findAll();
    }

    @PostMapping("/")
    public Services addService(@RequestBody Services s){

        return serviceRepository.save(s);
    }

    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable(name="id") Long id){
        Services service = serviceRepository.getById(id);
        if(service==null){
            throw new UnprocessableEntity("No Such service");
        }
        return service;
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable(name="id") Long id){

        Services service = serviceRepository.getById(id);
        if(service==null){
            throw new UnprocessableEntity("No Such service");
        }
        this.serviceRepository.delete(service);
    }

    @PutMapping("/{id}")
    public Services updateService(@PathVariable(name="id") Long id,@RequestBody Services s){
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
