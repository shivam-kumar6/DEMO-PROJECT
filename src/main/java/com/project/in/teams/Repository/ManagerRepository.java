package com.project.in.teams.Repository;


import com.project.in.teams.Entity.Manager;
import com.project.in.teams.Entity.Team;
import com.project.in.teams.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    public Manager findFirstByTeamName(String name);

    public Manager findFirstByUsersEmail(String email);

}
