package com.project.in.teams.Repository;

import com.project.in.teams.Entity.Team;
import com.project.in.teams.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    public List<Team> findAllByName(String name);

    public Team findFirstByName(String name);

    public Team getAllById(Long id);

    public List<Users> findUserListById(Long id);

}
