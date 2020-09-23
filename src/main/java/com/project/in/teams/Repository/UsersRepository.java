package com.project.in.teams.Repository;

import com.project.in.teams.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
   public List<Users> findByEmail(String name);

  public Users getById(Long id);

   public void deleteById(Long Id);

   public Users findFirstByEmail(String email);
}
