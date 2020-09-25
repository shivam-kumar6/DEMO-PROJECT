package com.project.in.teams.Repository;

import com.project.in.teams.Entity.Team;
import com.project.in.teams.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    public Team findFirstByName(String name);

    public Team findFirstById(Long id);
}
