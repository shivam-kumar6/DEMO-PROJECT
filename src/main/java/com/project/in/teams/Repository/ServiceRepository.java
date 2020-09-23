package com.project.in.teams.Repository;

import com.project.in.teams.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Long> {

    public Services getById(Long id);

    public void deleteById(Long id);

}
